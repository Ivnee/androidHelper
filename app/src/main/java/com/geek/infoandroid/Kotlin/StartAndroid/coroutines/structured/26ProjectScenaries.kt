package com.example.testcoroutine

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import retrofit2.http.GET
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class `26ProjectScenaries` {
//Scene1
    //На экране отображается список пользователей. Мы можем добавлять нового пользователя или удалить сразу всех. Работаем
    // только с локальной БД, на сервер не ходим.

    //1)Dao
    @Dao
    interface UserDao {

        @Query("SELECT * FROM user")
        fun getAll(): Flow<List<UserDb>>//получаем через флоу

        @Insert
        suspend fun insert(user: UserDb)//вставляем и удаляем через suspend

        @Query("DELETE FROM user")
        suspend fun deleteAll()
    }

    //2)Repository
    class UserRepository(
        private val userDao: UserDao,
        private val userMapperDbToUi: UserMapperDbToUi,
        private val userMapperUiToDb: UserMapperUiToDb
    ) {

        fun getUsers(): Flow<List<User>> {
            return userDao.getAll().map { userMapperDbToUi.transformList(it) }
        }

        suspend fun addUser(user: User) {
            userDao.insert(userMapperUiToDb.transform(user))
        }

        suspend fun deleteAllUsers() {
            userDao.deleteAll()
        }
    }

    //3)Usecases
    class GetUsersUseCase(private val userRepository: UserRepository) {
        fun execute(): Flow<List<User>> {
            return userRepository.getUsers()
        }
    }

    class DeleteAllUsersUseCase(private val userRepository: UserRepository) {
        suspend fun execute() {
            userRepository.deleteAllUsers()
        }
    }

    class AddUserUseCase(private val userRepository: UserRepository) {
        suspend fun execute(user: User) {
            userRepository.addUser(user)
            //типа с логикой валидации
            /* if (!userValidator.isValid(user)) {
                 return Error(IllegalArgumentException("User is not valid"))
             }
             try {
                 userRepository.addUser(user)
             } catch (e: Exception) {
                 return Error(e)
             }
             return Success(Unit)*/
        }
    }

    //ViewModel
    class MainViewModel(
        private val getUsersUseCase: GetUsersUseCase,
        private val deleteAllUsersUseCase: DeleteAllUsersUseCase,
        private val addUserUseCase: AddUserUseCase
    ) : ViewModel() { //добавление и удаление из дб будут автоматом тригерить флоу
        val users = getUsersUseCase.execute()
            .asLiveData()//получить ,для получения просто подписываемсяя на эту лайфдату

        fun addOnClick(user: User) {//добавить
            viewModelScope.launch {
                val result = addUserUseCase.execute(user)
            }
        }

        fun onClearClick() {//удалить всех юзеров
            viewModelScope.launch {
                deleteAllUsersUseCase.execute()
            }
        }
    }

}

class Scene2() {
    //Scene2
    //Во втором сценарии рассмотрим получение данных с сервера и сохранение их в БД.
//Retrofit api
    interface UserApiService {
        @GET("users")
        suspend fun fetchUsers(): List<UserApi>
    }

    //room
    @Dao
    interface UserDao {
        @Query("SELECT * FROM user")
        suspend fun getAll(): List<UserDb>

        @Insert
        suspend fun insertAll(users: List<UserDb>)

        @Query("DELETE FROM user")
        suspend fun deleteAll()
    }

    //repository
    class UserRepository(
        private val userDao: UserDao,
        private val userApiService: UserApiService,
        private val userMapperDbToUi: UserMapperDbToUi,
        private val userMapperApiToDb: UserMapperApiToDb
    ) {

        suspend fun getUsers(): List<User> {
            return userMapperDbToUi.transformList(userDao.getAll())
        }

        suspend fun fetchUsers() {
            val usersApi = userApiService.fetchUsers()
            val usersDb = userMapperApiToDb.transformList(usersApi)
            userDao.deleteAll()
            userDao.insertAll(usersDb)
        }
    }

    //UseCases
    class FetchOrGetUsersUseCase(
        private val userRepository: UserRepository,
        private val preferences: Preferences1
    ) {

        suspend fun execute(): List<User> {
            try {
                userRepository.fetchUsers()
                preferences.lastUpdated()
            } catch (e: Exception) {
            }
            return userRepository.getUsers()
        }
    }

    //ViewModel
    class MainViewModel(private val fetchOrGetUsersUseCase: FetchOrGetUsersUseCase) : ViewModel() {
        var getDataJob: Job? = null//для предотвращения множественных нажатий
        val users = MutableLiveData<List<User>>()

        fun getData() {
            if (getDataJob?.isActive == true) return//выходим из функци если корутина еще не завершила свою работу

            getDataJob = viewModelScope.launch {//получаем джоб корутины
                val usersData = fetchOrGetUsersUseCase.execute()
                users.value = usersData
            }
        }
    }

}

//мапперы
class UserMapperUiToDb {
    //из мэйн в бд
    fun transform(user: User): UserDb {
        return UserDb("name")
    }
}

class UserMapperApiToDb() {
    //из ретрофита в дб
    fun transformList(usersApi: List<UserApi>): List<UserDb> {
        return listOf(UserDb("name"))
    }
}

class UserMapperDbToUi {
    //из дб в мэйн
    fun transformList(it: Any): List<User> {
        return listOf(User("name"))
    }
}

//дата классы для апи дб и юай
@Entity(tableName = "user")
data class UserDb(val name: String)
data class UserApi(val name: String)
data class User(val name: String)


class Preferences1(context: Context) {
    val sharedPreferences = context.getSharedPreferences("name", AppCompatActivity.MODE_PRIVATE)
    fun lastUpdated() {
        val dateFormat: DateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
        sharedPreferences
            .edit()
            .putString("current date", dateFormat.format(Date(System.currentTimeMillis())))
            .apply()
    }

    fun getLastUpdated(): String {
        return sharedPreferences.getString("current date", "0").toString()
    }
}
