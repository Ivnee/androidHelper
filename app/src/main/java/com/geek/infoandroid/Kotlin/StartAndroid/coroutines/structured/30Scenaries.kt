package com.example.testcoroutine

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

class `30Scenaries`(val apiService: ApiService, val cache: MyCache) {
    //Проверка логина и пароля на валидность
    class MainViewModel() : ViewModel() {
        private val _name = MutableStateFlow("")//Flow для имени и возраста
        private val _age = MutableStateFlow("")

        val dataIsValid: LiveData<Boolean> =
            combine(_name, _age) { name, age ->//объединяет наши флоу в 1 лайфдату
                isNameValid(name) && isAgeValid(age)//если имя и возраст валидны то вернет тру, иначу фолс
            }.asLiveData()//трасформируем в лайфдату и получем лайфдату с булином(ее использовать для видимости кнопки или иной логики в валидности данных)

        //Функции затычки
        private fun isAgeValid(age: String): Boolean = true
        private fun isNameValid(name: String): Boolean = true

        fun setName(name: String) {
            _name.value = name
        }

        fun setAge(age: String) {
            _age.value = age
        }
    }

    class MainActivity : AppCompatActivity() {
        //отправляем данные из полей ввода в наши флоу
        //editTextName.addTextChangedListener { model.setName(it.toString()) }
        //editTextAge.addTextChangedListener { model.setAge(it.toString()) }
    }
//сценарий фильтрация данных из бд....

    //сценарий сначала получаем данные из кэша,а потом уже из апи
    fun fetchData(id: String): Flow<Any> = flow {
        val cachedData = cache.getData(id)

        if (cachedData != null) {
            emit(cachedData)
        }

        val apiData = apiService.fetchData(id)
        cache.putData(id, apiData)
        emit(apiData)
    }

//сценарий , получение данных с сервера с определенным интервалом
    fun fetchDataWithPeriod(): Flow<Any> = flow {
        while (true) {//Если надо чтобы флоу работал на несколько получателей, то используйте shareIn или stateIn.
            val data = apiService.fetchData("some id")
            emit(data)
            delay(10_000)
        }
    }
//сценарий - в зависимоти от чекбокса либо обновляем данные ,как только они меняются ,либо 1 раз запросили и больше не обновляем
    @Dao
    interface MyDao{
    @Query("SELECT * FROM user")
    suspend fun getAll(): List<String>//один раз отправляет данные

    @Query("SELECT * FROM user")
    fun getAllFlow(): Flow<List<String>>//отправляет данные при каждом изменении
    }
    fun getData(dao:MyDao): Flow<List<String>> = flow{
        if(true){//если чекбокс включен,то всегда обновляем данные
            emitAll(dao.getAllFlow())//флоу с актуальными данными
        }else{
            emit(dao.getAll())//1 раз получаем данные
        }
    }

//реализация абстракт классов для UseCase(базовые)
}


/////////////////////////////////
class MyCache {
    fun getData(id: String): Any {
        return Any()
    }

    fun putData(id: String, apiData: Any) {
    }

}

class ApiService() {
    fun fetchData(str:String): Any {
        return Any()
    }
}