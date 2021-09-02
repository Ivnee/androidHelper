package com.geek.infoandroid.Kotlin.coroutines.practic.apiRoomFull.usecase

import com.geek.infoandroid.Kotlin.coroutines.practic.apiRoomFull.UserRepository
import com.geek.infoandroid.Kotlin.coroutines.practic.roomFull.User

class FetchOrGetUsersUseCase(
    private val userRepository: UserRepository,
    //private val networkHelper:NetworkHelper,//проверяет подключен ли вайфай
    //private val preferences:Preferences//здесь можно фиксировать время обновления данных в бд(который делает метод fetchUsers()в репозитории)
) {
    suspend fun execute(): List<User> {
        //if (networkHelper.isWiFi()) {//если ваайфай подключен,то
            try {
                userRepository.fetchUsers()//запрашиваем юзеров из нашего репозитория
            } catch (e: Exception) {
            }
            //preferences.lastUpdated()//фиксинуем время последнего обновления базы данных
       // }
        return userRepository.getUsers()//если в апи будет ошибка,то делаем запрос из базы данных
    }

//Этот юзкейс можно разбить на 2, 1 c помощью флоу получает данные с дб и возвращает его
// а второй только запрашивает данные с апи которые сетит в дб
}