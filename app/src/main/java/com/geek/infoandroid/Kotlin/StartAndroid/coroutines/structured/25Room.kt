package com.example.testcoroutine

import androidx.lifecycle.*
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class `25Room` {
    @Dao
    interface CommentDao {
        @Query("SELECT * FROM Comment123")
        suspend fun getAll(): List<Comment>

        @Query("SELECT * FROM Comment123")//suspend с flow ненужно
        fun getFlowAll(): Flow<List<Comment>>
        @Insert
        suspend fun insert(comment: Comment123)
    }


    class MainViewModel2(val db: CommentDao) : ViewModel() {
        val commentsLiveData = MutableLiveData<List<Comment>>()
        fun fetchData() {
            viewModelScope.launch {
                val data = db.getAll()//получаем данные из дб
                commentsLiveData.value = data//отправляем в лафйдату получателю
            }
            //Flow вариант 1
            val flow = db.getFlowAll()//получаем флоу из дб
            viewModelScope.launch {//создаем корутину(работа флоу начинается в collect)
                flow.collect {//данные будут приходить в этот флой каждый раз как в дб что-то мееняется
                    commentsLiveData.value = it//отправляем данные в лайф дату, получателю
                }
            }
            //Flow вариант 2
            val comments = liveData {//создаем лайфдату с корутиной(это билдер такой со встроенной корутиной)
                flow.collect {//флоу который пришел из рума
                    emit(it)//данные которые отправляем подписчику лайфдаты(emit в данном случаем метод из scopeLiveData)
                }
            }
            //Flow вариант 3\
            val liveDataFromFlow = db.getFlowAll().asLiveData()//сразу получили лайфдату,которая внутри содержит все данные из базы(просто подписываемся на нее и получаем данные)
            //вставляем элемент
            viewModelScope.launch { db.insert(Comment123("a")) }//отправили изменение в бд и флоу сразу получит обновленные данные
        }
    }
}

@Entity
data class Comment123(val comment: String)