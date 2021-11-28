package com.example.testcoroutine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext

class `10Context` {

    fun contextt() {
        val context = Job() + Dispatchers.Default
        val scope = CoroutineScope(context)
        //или
        CoroutineScope(
            Job() + Dispatchers.Default +
                    UserData(1, "name")// пропихнули свои данные в корутину,передастся всем дочкам
        )
    }
}

data class UserData(val id: Long, val name: String) : AbstractCoroutineContextElement(UserData) {
    //такой объект можно помещать в контекст корутины
    companion object Key :
        CoroutineContext.Key<UserData>//и данные получаем в любой вложенной корутине этого скоупа или корутины,в которую передали данные
}