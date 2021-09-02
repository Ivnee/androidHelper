package com.geek.infoandroid.Kotlin.coroutines.practic.roomFull.usecases

import com.geek.infoandroid.Kotlin.coroutines.practic.roomFull.Repository

class DeleteAllUsersUseCase(private val repository: Repository) {
    suspend fun execute(){
        repository.deleteAllUsers()
    }
}