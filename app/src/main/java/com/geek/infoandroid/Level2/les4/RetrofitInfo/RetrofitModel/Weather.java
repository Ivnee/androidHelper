package com.geek.infoandroid.Level2.les4.RetrofitInfo.RetrofitModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Weather {
    @SerializedName("id")  @Expose
    public int id;
    @SerializedName("main") @Expose
    public String main;

}
