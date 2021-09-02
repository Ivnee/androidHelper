package com.geek.infoandroid.Internet8;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.geek.infoandroid.R;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class GSONMainActivity extends AppCompatActivity {
   // implementation 'com.google.code.gson:gson:2.8.5'

    private static final String TAG = "WEATHER";
    private static final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather?lat=55.75&lon=37.62&units=metric&appid=";

    private EditText city;
    private EditText temperature;
    private EditText pressure;
    private EditText humidity;
    private EditText windSpeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_main);
        init();
    }

    private void init() {/*
        city = findViewById(R.id.textCity);
        temperature = findViewById(R.id.textTemprature);
        pressure = findViewById(R.id.textPressure);
        humidity = findViewById(R.id.textHumidity);
        windSpeed = findViewById(R.id.textWindspeed);
        Button refresh = findViewById(R.id.refresh);
        refresh.setOnClickListener(clickListener);*/ //
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                final URL uri = new URL(WEATHER_URL + "762ee61f52313fbd10a4eb54ae4d4de2");
                final Handler handler = new Handler(); // Запоминаем основной поток
                new Thread(new Runnable() {
                    public void run() {
                        HttpsURLConnection urlConnection = null;
                        try {
                            urlConnection = (HttpsURLConnection) uri.openConnection();//создаем коннект по нашей ссылке которую пихнули в uri
                            urlConnection.setRequestMethod("GET"); // установка метода получения данных -GET
                            urlConnection.setReadTimeout(10000); // установка таймаута - 10 000 миллисекунд
                            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream())); // читаем  данные в поток
                            String result = getLines(in);//получаем все данные через буфферед реадер из ссылки коннекта
                            // преобразование данных запроса в модель
                            Gson gson = new Gson();//создаем гсон
                            final GSONmodel gsoNmodel = gson.fromJson(result, GSONmodel.class);//сетим резалт в нашу модельку  ,название объектов модели должны совпадать с JSON
                            // Возвращаемся к основному потоку
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    displayWeather(gsoNmodel);
                                }
                            });
                        } catch (Exception e) {
                            Log.e(TAG, "Fail connection", e);
                            e.printStackTrace();
                        } finally {
                            if (null != urlConnection) {
                                urlConnection.disconnect();//закрываем соединение
                            }
                        }
                    }
                }).start();
            } catch (MalformedURLException e) {
                Log.e(TAG, "Fail URI", e);
                e.printStackTrace();
            }
        }

        private String getLines(BufferedReader in) {/*
            return in.lines().collect(Collectors.joining("\n"));*/
            return null;
        }

        private void displayWeather(GSONmodel gsoNmodel) {
            /*city.setText(gsoNmodel.getName());             //береем модель , берем из нее имя города и сетим в текст вью
            String temperatureValue = String.format(Locale.getDefault(), "%.2f", gsoNmodel.getMain().getTemp());// берем температуру(с двумя символами после зарятой с помощью стринг формата )
            temperature.setText(temperatureValue); //сетим во вью

            String pressureText = String.format(Locale.getDefault(),"%d", gsoNmodel.getMain().getPressure());
            pressure.setText(pressureText);
        */
        }
    };
}
