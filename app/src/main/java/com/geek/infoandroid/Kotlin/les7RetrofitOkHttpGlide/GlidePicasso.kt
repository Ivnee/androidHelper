package com.geek.infoandroid.Kotlin.les7RetrofitOkHttpGlide

import com.bumptech.glide.Glide
import com.geek.infoandroid.databinding.Fragment1Binding
import com.squareup.picasso.Picasso

class GlidePicasso {
    val viewBinding: Fragment1Binding? = null
    fun sss() {
        viewBinding?.img?.let {
            ///
            Glide.with(it)
                .load("http://img.lenagold.ru/k/kot/kosh518.png")
                .into(viewBinding.img)
            ///
            Picasso.get()
                .load("http://img.lenagold.ru/k/kot/kosh518.png")
                .into(viewBinding.img)
//трансформ картинки урок 7 время 1:15 +-

        }
    }
}