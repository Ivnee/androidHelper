package com.example.fortestcode

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.geek.infoandroid.R

class MainActivity : AppCompatActivity(R.layout.main_activity2) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val storage= Storage(this)
        storage.isSelected.not()
    }
}