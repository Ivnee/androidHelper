package com.geek.infoandroid.Internet8;

public class InternetInfoXMLandComment {
    //виды запросов
    /*
    POST - запрос который отправляет информацию на сервер ,в это запросе чаще всего встречается body
    GET - запрос на получение данных или на получение новой страницы и тд
    PUT - запрос на обновление данных
    PATCH - запрос на обновление 1 единицы данных,например 1 циферку и тд
    DELETE - запрос на удаление данных

    JSON -тима XML ,данные отправляются в виде массивов с вложенностью
        cod = 200 //запрос обработан правильно и вам возвращается ответ(ответ дается с POST)
        cod = 204 //запрос обработан правильно,но ничего не вернулось
        сod = 400... // запрос обработан неправильно
        cod = 500...//серьезные ошибки
    URL - это адрес запроса страницы(https://google.com) у запросов могут быть параментры,они отделяются знаком ?

        //сам jsonObject принимаем от класса который собирает джейсон объект
     JSONObject details = jsonObject.getJSONArray("weather").getJSONObject(0);//создаем джава класс,в котором получаем массив везер и берем первый элемент этого массива
     JSONObject main = jsonObject.getJSONObject("main");//создаем дажва класс джейсон и берем из него объект меин

      String cityText = jsonObject.getString("name").toUpperCase() + ", "   //берем из него строку по ключу нейм
                + jsonObject.getJSONObject("sys").getString("country");      //берем объект по ключу сис и у него берем строку по ключу кантри
     //подробно
      jsonObject.getString("name")//получить строку по ключе нэйм
      .toUpperCase()//все буквы большие
      jsonObject.getJSONObject("sys")//получаем объект по ключу сис
    */
    /*
        <WebView/>//типа браузера у нас в макете,можно передать в нее урл и он его откроет

            <ProgressBar//прогрессбар загрузка
        android:indeterminateOnly="true"
        android:layout_centerInParent="true"расположить по центру
        android:visibility="gone"/>//по умолчанию он невидимый
*/}
