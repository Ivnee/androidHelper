package com.geek.infoandroid.Level2.les7.FindTekusheeLoc;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.geek.infoandroid.R;

import java.io.IOException;
import java.util.List;

public class LocGeocoder extends AppCompatActivity {
    /*нуэные разрешения
     <uses-permission android:name="android.permission.INTERNET"/>интернет
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>доступ к местоположению
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>...*/
    private Button button1;
    private Button button2;
    private Button button3;
    private LocationManager mLocManager = null;//нужен чтоб получить нашу локацию,getLastKnownLocation()
    private LocListener locListener;//внутренний класс листенер ,в котором мы обрабатываем смену локации(наследуем его от LocationListener)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        mLocManager = (LocationManager) getSystemService(LOCATION_SERVICE);//находим наш локейшн менеджер через системный сервис
        Location loc;//какая-то локация(которую можно получить через локейшн менеджер
        if (ActivityCompat.checkSelfPermission
                (this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;//про обработку пермишенов в папке Permissions(
            //если пермишенов нет,то запрашиваем здесь, если есть, то назодим локацию и делаем работу
        } else {
            loc = mLocManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);//определяет местоположение четко по GPS
            //LocationManager.NETWORK_PROVIDER;//определяет местоположение в радиусе вышки
            //LocationManager.PASSIVE_PROVIDER;//выводит последний скаченный результат локации

            if (loc != null)
                button1.setText(locToString(loc));//лок ту стринг ненужный метод по сути
            button2.setText(getAdressByLoc(loc));//получаем адрес по полученной Location
        }
    }

    private String getAdressByLoc(Location loc) {
        final Geocoder geo = new Geocoder(this);//переводит координаты в адресс(принимает контекст)
        List<Address> list;
        try {
            list = geo.getFromLocation(loc.getLatitude(),loc.getLongitude(),1);//получить близжайший адресс(максимальное количество близжайших адресов = 1 (можно поставить больше))
        } catch (IOException e) {
            e.printStackTrace();
            return e.getLocalizedMessage();
        }
        if (list.isEmpty()){return  "нет адресов";}
        Address a = list.get(0);//вытаскиваем наш первый (и единственный )полученный адресс

        String normalAdress = a.getCountryName() + ","//название страны
                + a.getAdminArea() +" ," //название области
                + a.getThoroughfare() + " ,"//название улица
                + a.getThoroughfare() +//дом
                a.getAddressLine(a.getMaxAddressLineIndex())+//получить полный адресс ,с индексом и тд
                a.getLocality();//вроде это город
        return normalAdress;
    }

    private void initViews() {
        button1 = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
    }
//ненужный метод
    private String locToString(Location loc) {
        if (loc == null){
            return "не найдено";
        }
        return "наша долгота = " + loc.getLongitude()+ "наша широта = " + loc.getLatitude();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(locListener == null){locListener = new LocListener();}
        //требует чек пермишн,лок менеджер устанавливает слушатель на изменение локаций ,если локация поменяется то выполнится то,что в лок листенере мы написали
        //mLocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,3000L,10.0F,locListener);//(1)как получаем данные2)время ожидания запроса3)дистанция поиска адреса,4)сам слушатель,в котором все делается(слушатель создаем сами и наследуем от LocationManager))
    }

    //КЛАСС СЛУШАТЕЛЬ ЛОКАЦИЙ
    private final class LocListener implements LocationListener{//отслеживание местоположения
        @Override
        public void onLocationChanged(@NonNull Location location) {//срабатывает при мене местоположения
            button1.setText(getAdressByLoc(location));//вставляем локацию когда изменяется наше местоположение
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(@NonNull String provider) {

        }

        @Override
        public void onProviderDisabled(@NonNull String provider) {

        }
    }
}