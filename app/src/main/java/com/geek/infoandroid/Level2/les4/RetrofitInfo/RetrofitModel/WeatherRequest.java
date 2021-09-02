package com.geek.infoandroid.Level2.les4.RetrofitInfo.RetrofitModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherRequest {
    @SerializedName("weather")//(это имя в json)указываем ключ ,по которому будут все данные получаться(если имя такое же как ключ, можно не указывать)
    @Expose//указатель на то что нужно с ней работать ретрофиту(уточнить
    public Weather []weather;//это имя по которому будет делать запрос в нашем коде
    @SerializedName("main") @Expose
    public Main main;
    @SerializedName("name") @Expose
    public String name;
    @SerializedName("cod") @Expose
    public int cod;
}

