package com.geek.infoandroid.android.Level2.les4.RetrofitInfo.RetrofitModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherRequest {
    @SerializedName("weather")//можно без серриалайз нейм,будет обращаться по имени поля,если указали то по указанному имени
    @Expose//указатель на то что нужно с ней работать ретрофиту(уточнить
    public Weather []weather;//это имя по которому будет делать запрос в нашем коде
    @SerializedName("main") @Expose
    public Main main;
    @SerializedName("name") @Expose
    public String name;
    @SerializedName("cod") @Expose
    public int cod;
}

