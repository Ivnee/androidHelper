package com.geek.infoandroid.Kotlin.StartAndroid.coroutines.practic

import android.app.Application
import androidx.lifecycle.*
import androidx.room.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RoomCoroutine(application: Application) :ViewModel(){
    val db = Room.databaseBuilder(application,CommentsDataBase::class.java,"CommentsDB").build()
    val comments = MutableLiveData<List<Comment>>()



    //По нажатию кнопки Send вызывается метод onSendClick в котором мы в корутине вызываем метод insert, чтобы добавить
    // новый комментарий в БД.
    //comments - это LiveData с данными из БД. Как только мы изменим данные методом insert, Flow получит обновленный
    // список и поместит их в comments. А оттуда они уже пойдут на экран. Нам ничего не надо вручную запрашивать.
    val commentsActualFlow = db.getCommentDao().getAllFlow().asLiveData()//получаем актуальный список комментов
    fun onSendClick() {
        viewModelScope.launch {
            db.getCommentDao().insert(Comment(0,"123123123"))
        }
    }


    //получаем данные из дб с помощью флоу(эту корутину надо запустить 1 раз при создании вью модели,можно в ините создать)
    val commentsFlow = db.getCommentDao().getAllFlow()//получаем Флоу который подписан на изменение коментариев в дб
    init {
        viewModelScope.launch {
            commentsFlow.collect {//получаем данные из дб получаем сразу,как там что-то меняется
                comments.value = it//отправляем наши данные в вью ,с помощью лайф даты(или флоу)
            }
        }
    }
    //или создать корутину из лайфдаты сразу
    fun roomFlow(){
        val commentsFlow = db.getCommentDao().getAllFlow()//достаем флоу из дб
        val comments = liveData {//создаем лафдату
            commentsFlow.collect {
                emit(it)
            }
        }
        //или так
        val comments2 = db.getCommentDao().getAllFlow().asLiveData()//аналог того что выше,всего одна строка и содержит актуальные данные бд
    }





    //вызываем с suspend в руме
    fun getFromRoomWithSuspend(){
        viewModelScope.launch {
            val commentsData = db.getCommentDao().getAll()//получаем данные
            comments.value = commentsData//и сразу вставляем в лайф дату
        }
    }




    //вызываем без suspend в room
    fun getFromRoomWithoutSuspendRoom(){
        //обычный вызов с перепрыгиваниями по потокам
        viewModelScope.launch{//начинаем выполнять в мэйн потоке
            val comments = getComments()//метод который запрашивает данные в другом потоке
            print(comments)//и опять в мэйн потоке отображаем наши данные
        }
    }
    suspend fun getComments():List<Comment>{//получаем данные из дб в потоке IO
        return withContext(Dispatchers.IO){//переходим в IO поток
            db.getCommentDao().getAll()//получаем данные из дб
        }
    }

}


//дб просто для отмены ошибок
@Database(entities = [Comment::class],version = 1)
abstract class CommentsDataBase():RoomDatabase(){
    abstract fun getCommentDao():CommentDao

}
@Dao
interface CommentDao{
    @Query("SELECT * FROM comment")
    suspend fun getAll():List<Comment>

    @Query("SELECT * FROM comment")
    fun getAllFlow(): Flow<List<Comment>>//c флоу слово суспенд уже не нужно


    @Insert
    suspend fun insert(comment: Comment)

}
@Entity(tableName = "comment")
data class Comment(
    @PrimaryKey(autoGenerate = true)
    val id:Long,
    val comment:String
)