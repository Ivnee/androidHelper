package com.example.testrxjava

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.functions.Consumer

class `12ViewClicks`: Fragment(R.layout.fragment1) {
    @SuppressLint("CheckResult")
    fun work(){
        val tv = requireView().findViewById<TextView>(R.id.textView)
        RxView.clicks(tv).subscribe(object : Consumer<Boolean> {
            override fun accept(t: Boolean?) {
            }
        })
    }
}