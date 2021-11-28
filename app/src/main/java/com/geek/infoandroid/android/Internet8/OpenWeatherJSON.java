package com.geek.infoandroid.android.Internet8;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class OpenWeatherJSON {
    private static final String OPEN_WEATHER_API_KEY = "762ee61f52313fbd10a4eb54ae4d4de2";
    private static final String OPEN_WEATHER_API_URL = "https://api.openweathermap.org/data/2.5/weather?q=%s&units=metric";
    private static final String KEY = "x-api-key";

    static JSONObject getJSONData(String city) {//обьявляем статический метод получения джейсон объекта
        try {
            URL url = new URL(String.format(OPEN_WEATHER_API_URL, city));//в стринг формат заменяем %s в ссылке на кород который передаем через запятую(%n- заменяет на числа и еще есть)
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();//открываем (коннекшн) соединение по данной ссылке
            connection.addRequestProperty(KEY, OPEN_WEATHER_API_KEY);//для коннекшн вводим ключи для получения доступа к сбору данных с сайта опен везер мап

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));//открываем поток на получение данных у коннекшн
            StringBuilder strignBuilderData = new StringBuilder(1024);//создаем стрингбилдер для сбора всех данных
            String line;

            while ((line = reader.readLine()) != null) {//читаем данные с ссылки по которой открыли коннекшн
                strignBuilderData.append(line).append("\n");//добавляем данные в сб
            }

            reader.close();//после того как все прочли закрываем поток

            JSONObject jsonObject = new JSONObject(strignBuilderData.toString());//все что получили переводим в формат джейсона(это джававский класс)
            if(jsonObject.getInt("cod") != 200) {//берем поле код у полученного джейсон объекта ,если не 200 ,то какой-то косяк
                return null;
            } else {
                return jsonObject;//если 200 то возвращаем наш объект
            }
        } catch (Exception exc) {
            exc.printStackTrace();
            return null;
        }
    }
}
