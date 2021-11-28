package com.example.testcoroutine

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

class `7Scope` : ViewModel() {
    suspend fun work(){
        viewModelScope//корутины этого скоуп будут отменены когда завершится вью модель

        val scope = CoroutineScope(Job())//стандартный скоуп,которому задаем все параметры и закрываем его самостоятельно
        scope.launch {  }
        scope.cancel()
    }

}