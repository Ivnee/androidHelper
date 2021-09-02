package com.geek.infoandroid.Level2.les5.RoomSQLite.room;

import androidx.room.ColumnInfo;

public class TableAddAdress {
    @ColumnInfo(name = "город")//можно вообще не указывать эту аннотацию,по умолчанию будет name = city
    public String city;
    @ColumnInfo(name="страна")
    public String country;
}
