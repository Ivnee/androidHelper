package com.geek.infoandroid.Kotlin.StartAndroid.coroutines.practic.roomFull.usecases

import com.geek.infoandroid.Kotlin.StartAndroid.coroutines.practic.roomFull.Repository
import com.geek.infoandroid.Kotlin.StartAndroid.coroutines.practic.roomFull.User
import java.lang.Exception
import java.lang.IllegalArgumentException

class AddUserUseCase(private val repository: Repository) {
    val userIsValid = true
    suspend fun execute(user: User): Result<Unit> {
        if(!userIsValid){//перед добавлением данных мы проверяем,валидны ли данные
            return Error(IllegalArgumentException("юзер не валидный"))//если нет,возвращаем ошибку
        }
        try {
            repository.addUser(user)//добавляем данные
        }catch (e:Exception){
            return Error(e)//в случае ошибки возвращаем еррор
        }
        return Success(Unit)//если все ок,возвращаем пустой сакцесс
    }
}


sealed class Result<T>()
class Success<T>(val value:T): Result<T>()
class Error<T>(val error:Throwable): Result<T>()
