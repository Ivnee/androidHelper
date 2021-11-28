package com.example.gitt.moxy

import android.widget.Toast
import com.example.gitt.R
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(R.layout.activity_main), MainView {
    private val presenter by moxyPresenter { MainPresenter() }

    override fun displayUser(user: String) {
        Toast.makeText(this,user,Toast.LENGTH_SHORT).show()
    }


}