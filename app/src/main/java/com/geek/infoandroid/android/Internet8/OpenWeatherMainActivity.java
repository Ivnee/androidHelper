package com.geek.infoandroid.android.Internet8;

import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;

import android.os.Handler;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.geek.infoandroid.R;

import org.json.JSONException;
import org.json.JSONObject;

public class OpenWeatherMainActivity extends AppCompatActivity {
    private final Handler handler = new Handler();//хендлер для указания на текущий поток UI
    private final static String LOG_TAG = OpenWeatherMainActivity.class.getSimpleName();//лог для вывода логов

    Typeface weatherFont;//для создания шрифтов
    private TextView cityTextView;
    private TextView updatedTextView;
    private TextView detailsTextView;
    private TextView currentTemperatureTextView;
    private TextView weatherIconTextView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_main);
        initViews();
        initFonts();
    }

    private void initViews() {}

    private void initFonts() {
        weatherFont = Typeface.createFromAsset(getAssets(), "fonts/weather.ttf");//добавляем наши шрифты getAssets вернет папку ассетс а потом указываем в ней путь
        weatherIconTextView.setTypeface(weatherFont);//указываем шрифт который будет в нашей текстовой вьюхе
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//создаем меню в котором мы вызываем окно для написания нужного города
        getMenuInflater().inflate(R.menu.navigation_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.item1){}//как обрабатываются нажатия в меню
        showInputDialog();//показываем наше окно в которое будем вписывать текст
        return true;
    }

    private void showInputDialog() {//диалоги будем разбирать в других уроках подробнее
        AlertDialog.Builder builder = new AlertDialog.Builder(this);//создаем алерт диалог через билдер
        builder.setTitle("change City");//оглавление у этого диалога ставим чендж сити

        final EditText input = new EditText(this);//создаем едит текст куда будем вводить название города
        input.setInputType(InputType.TYPE_CLASS_TEXT);//тип вводимого текста,поставили самый обычный текст(можно поставить PASSWORD, тогда ббудет все точечьками)
        builder.setView(input);//вставляем созданную вьюху в наш диалог
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {//устанавливаем кнопку ОК по нажатии на которую будет вызываться метод создающий джейсон
                updateWeatherData(input.getText().toString());
            }
        });
        builder.show();//не забываем в конце показать
    }

    private void updateWeatherData(final String city) {
        progressBar.setVisibility(View.VISIBLE);//пока выполняется крутим прогресс бар
        new Thread() {
            @Override
            public void run() {
                final JSONObject jsonObject = OpenWeatherJSON.getJSONData(city);//забираем джейсон данные по переданному городуд
                if(jsonObject == null) {//если ничего не получили
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "place not found",
                                    Toast.LENGTH_LONG).show();//кидаем тост что не найден такой город
                        }
                    });
                } else {//если что-то получили
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setVisibility(View.GONE);//убираем загрузку
                            renderWeather(jsonObject);//сетим все данные из джейсона(здесь идет отрисовка всех наших вьюх
                        }
                    });
                }
            }
        }.start();
    }

    private void renderWeather(JSONObject jsonObject) {
        Log.d(LOG_TAG, "json: " + jsonObject.toString());
        try {
            JSONObject details = jsonObject.getJSONArray("weather").getJSONObject(0);//создаем джава класс,в котором получаем массив везер и берем первый элемент этого массива
            JSONObject main = jsonObject.getJSONObject("main");//создаем дажва класс джейсон и берем из него объект меин

            setPlaceName(jsonObject);
            //остальные запросы по аналогии,все строится из постмана,лучше туда ссылку закинуть  смотреть как правильно составить запрос
            //можно почитать по тому как все сетить,или пересмотреть урок,там есть какие-то значения которые нужно переводить
            /*
            setDetails(details, main);
            setCurrentTemp(main);
            setUpdatedText(jsonObject);
            setWeatherIcon(details.getInt("id"),
                    jsonObject.getJSONObject("sys").getLong("sunrise") * 1000,
                    jsonObject.getJSONObject("sys").getLong("sunset") * 1000);*/
        } catch (Exception exc) {
            exc.printStackTrace();
            Log.e(LOG_TAG, "One or more fields not found in the JSON data");
        }
    }

    private void setPlaceName(JSONObject jsonObject) throws JSONException {//сетим информацию из джейсон объекта
        String cityText = jsonObject.getString("name").toUpperCase() + ", "   //берем из него строку по ключу нейм
                + jsonObject.getJSONObject("sys").getString("country");      //берем объект по ключу сис и у него берем строку по ключу кантри
        cityTextView.setText(cityText);
    }
    //остальные запросы по аналогии,все строится из постмана,лучше туда ссылку закинуть  смотреть как правильно составить запрос
/*
    private void setDetails(JSONObject details, JSONObject main) throws JSONException {
        String detailsText = details.getString("description").toUpperCase() + "\n"
                + "Humidity: " + main.getString("humidity") + "%" + "\n"
                + "Pressure: " + main.getString("pressure") + "hPa";
        detailsTextView.setText(detailsText);
    }

    private void setCurrentTemp(JSONObject main) throws JSONException {
        String currentTextText = String.format(Locale.getDefault(), "%.2f",
                main.getDouble("temp")) + "\u2103";
        currentTemperatureTextView.setText(currentTextText);
    }

    private void setUpdatedText(JSONObject jsonObject) throws JSONException {
        DateFormat dateFormat = DateFormat.getDateTimeInstance();
        String updateOn = dateFormat.format(new Date(jsonObject.getLong("dt") * 1000));
        String updatedText = "Last update: " + updateOn;
        updatedTextView.setText(updatedText);
    }

    private void setWeatherIcon(int actualId, long sunrise, long sunset) {
        int id = actualId / 100;
        String icon = "";

        if(actualId == 800) {
            long currentTime = new Date().getTime();
            if(currentTime >= sunrise && currentTime < sunset) {
                icon = "\u2600";
                //icon = getString(R.string.weather_sunny);
            } else {
                icon = getString(R.string.weather_clear_night);
            }
        } else {
            switch (id) {
                case 2: {
                    icon = getString(R.string.weather_thunder);
                    break;
                }
                case 3: {
                    icon = getString(R.string.weather_drizzle);
                    break;
                }
                case 5: {
                    icon = getString(R.string.weather_rainy);
                    break;
                }
                case 6: {
                    icon = getString(R.string.weather_snowy);
                    break;
                }
                case 7: {
                    icon = getString(R.string.weather_foggy);
                    break;
                }
                case 8: {
                    icon = "\u2601";
                    // icon = getString(R.string.weather_cloudy);
                    break;
                }
            }
        }
        weatherIconTextView.setText(icon);
    }*/
}
