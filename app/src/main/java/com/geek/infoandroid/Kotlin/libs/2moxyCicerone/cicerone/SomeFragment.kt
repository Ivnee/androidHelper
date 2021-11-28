package com.example.gitt.cicerone

import android.os.Bundle
import androidx.fragment.app.Fragment

class SomeFragment:Fragment() {
    companion object{
        fun newInstance(string: String):SomeFragment =
            SomeFragment().apply {
                arguments = Bundle().apply {
                    putString("key",string)
                }
            }
    }
}