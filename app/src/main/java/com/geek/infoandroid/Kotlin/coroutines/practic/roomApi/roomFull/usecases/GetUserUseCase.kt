package com.geek.infoandroid.Kotlin.coroutines.practic.roomFull.usecases

import com.geek.infoandroid.Kotlin.coroutines.practic.roomFull.Repository
import com.geek.infoandroid.Kotlin.coroutines.practic.roomFull.User
import kotlinx.coroutines.flow.Flow

class GetUserUseCase(private val repository: Repository) {
    fun execute(): Flow<List<User>>{
        return repository.getUsers()
    }
}