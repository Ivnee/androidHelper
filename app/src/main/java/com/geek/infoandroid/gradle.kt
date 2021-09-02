package com.geek.infoandroid

class gradle {
    //добавляем пропертис
   /* buildTypes.each{
        Properties properties = new Properties()// оздаем пропертис
        properties.load(project.rootProject.file("tmdb.properties").newDataInputStream())//загружаем в него ранее созданный файл tmdb.properties, в котором указали ключ
        def apiKey = properties.getProperty("tmdb_key","")//получаем ключ по названию поля
        it.buildConfigField "String", "TMDB_KEY",apiKey//указываем тип ,название и само значение(которое мы определили выше)

        /////// чтобы достать этот ключ в код
        BuildConfig.TMDB_KEY
    }*/
}