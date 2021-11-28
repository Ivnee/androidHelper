package com.example.testroom.entities

import android.os.Build
import androidx.room.TypeConverter
import java.util.stream.Collector
import java.util.stream.Collectors

class HobbiesConvertor {
    @TypeConverter
    fun fromHobbies(hobbies:List<String>):String{
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            hobbies.stream().collect(Collectors.joining(","))
        } else {
            TODO("VERSION.SDK_INT < N")
        }
    }
    @TypeConverter
    fun toHobbies(data:String):List<String>{
        return data.split(",")
    }
}