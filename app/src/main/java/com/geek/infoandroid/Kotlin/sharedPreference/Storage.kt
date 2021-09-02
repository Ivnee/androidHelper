package com.example.fortestcode

import android.content.Context
import androidx.appcompat.app.AppCompatActivity

class Storage (context:Context){
    val sharedPreferences =
        context.getSharedPreferences("name", AppCompatActivity.MODE_PRIVATE)//создаем шаред преференс по имени

    var isSelected: Boolean
        get() = sharedPreferences.getBoolean("isSelected", false)//получаем значение шп
        set(value) {
            sharedPreferences
                .edit()//создаем шп
                .putBoolean("isSelected",value)//кладем в него значение по ключу из секелтед
                .apply()//коммитим
        }

}