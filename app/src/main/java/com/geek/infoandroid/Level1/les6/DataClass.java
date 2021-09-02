package com.geek.infoandroid.Level1.les6;

public class DataClass {
    public double temp;
    public String name;

    public DataClass(double temp, String name) {
        this.temp = temp;
        this.name = name;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTemp() {
        return temp;
    }

    public String getName() {
        return name;
    }
}
