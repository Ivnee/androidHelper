package com.geek.infoandroid.Kotlin.les5InternetAndPropertyKey

class KeyPropertyComments {
/*
    1) переходим в состояние Project вместо Android
    2) создаем file allNames.properties в корне проэкта
    3) в него записываем имя и данные - open_weather_map_key = "132fce3d69979894a33cf504082ed717"
    4)затем заходим в гредл модуль и там делаем следующее
    buildTypes.each{//для каждого билд тайпа(для всех видов билда проэкта)
        Properties properties = new Properties()//загружаем проперти
        properties.load(project.rootProject.file("openweathermap.properties").newDataInputStream())//загружаем наш файл ,с которого будем читать
        def apiKey = properties.getProperty("open_weather_map_key","")//загружаем в apiKey наш ключ,по названию поля,дефолтная""
        it.buildConfigField "String", "WEATHER_API_KEY", apiKey//создаем поле  (тип данных,название,сами данные)
    }
     buildTypes.each {
        Properties properties = new Properties()
        properties.load(project.rootProject.file("tmdb.properties").newDataInputStream())
        def apiKey = properties.getProperty("tmdb_key", "")
        it.buildConfigField "String", "TMDB_KEY", apiKey
    }

    5)Затем достаем наши данные в коде BuildConfig.WEATHER_API_KEY
    */

    /////////////////Объявляем свое поле в конфиге
    /*
    * 1)в модуле гредл в defaultConfig{} добавляем следующее
    * 2)buildConfigField "String, "BASE_URL",  "\"https://api.openweathermap.org/\""
    * 3)используем "some string ${BuildConfig.BASE_URL}"
    * */
}