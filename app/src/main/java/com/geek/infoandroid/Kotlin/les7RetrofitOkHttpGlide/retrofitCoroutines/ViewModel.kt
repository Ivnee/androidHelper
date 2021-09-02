package com.geek.infoandroid.Kotlin.les7RetrofitOkHttpGlide.retrofitCoroutines

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.Executors

class ViewModel {

    //MutableStateFlow замена лайфдаты,в ней обязательн оуказываем аргументы
    private val _loading = MutableStateFlow(false)
    private val _error = MutableSharedFlow<String>()//шаред флоу кидает данные 1 раз(сингл уведомление),при повороте и пересоздании повторно не отсылает
    private val _cities = MutableStateFlow<List<City>>(listOf())

    val loading: Flow<Boolean> = _loading
    val error: Flow<String?> = _error
    val cities: Flow<List<City>> = _cities
    fun fetchWeather(city:City) {
        _loading.value = true
        //когда необходимо делать паралельные запросы одновременно несколько(например в базу и в апм)
        viewModelScope.async {
            weatherRepository.getWeatherSuspend(city)
        }
        ///ланч выполняет запросы последовательно
        //в скобочках можно указывать где мы хотим выполнять наш код
        //IO в потоке ,как я понял
        //Main в  основном потоке
        //Default в потоке в котором он запущен
        //по умолчанию дефолтном(в котором начали)
        viewModelScope.launch(Dispatchers.IO) {
            val result = weatherRepository.getWeatherSuspend(city)
            when (result) {
                is Success -> {
                    val weather = result.value
                    //и в виз зконтексте можем поменять,например если нам нужен доступ в мэйн поток
                    withContext(Dispatchers.Main) {
                        _weather.value = WeatherInfo(
                            weather.city.name,
                            weather.temperature.toString(),
                            R.drawable.ic_launcher_background
                        )
                    }
                    _error.emit("null")
                }
                is Error -> {
                    _error.emit("222")
                    _weather.value = null
                }
                else -> {
                }
            }

        }

    }
}