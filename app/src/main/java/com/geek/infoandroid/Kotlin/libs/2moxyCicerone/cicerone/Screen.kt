package com.example.gitt.cicerone

import android.content.Intent
import android.net.Uri
import com.github.terrakok.cicerone.androidx.ActivityScreen
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screen {
    fun Main(id:String) = FragmentScreen { SomeFragment.newInstance(id) }//переходим на фрагмент
    fun Browser(url: String) = ActivityScreen { Intent(Intent.ACTION_VIEW, Uri.parse(url))  }//переходим по ури
    fun openActivity() = ActivityScreen { Intent(it,Activity::class.java )  }
}