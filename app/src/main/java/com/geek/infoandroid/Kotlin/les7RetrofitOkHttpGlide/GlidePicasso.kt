package com.geek.infoandroid.Kotlin.les7RetrofitOkHttpGlide

import androidx.fragment.app.Fragment
import android.widget.ImageView
import coil.ImageLoader
import coil.request.LoadRequest
import coil.transform.CircleCropTransformation
import com.bumptech.glide.Glide
import com.geek.infoandroid.R
import com.geek.infoandroid.databinding.Fragment1Binding
import com.squareup.picasso.Picasso

class GlidePicasso: Fragment() {
    val viewBinding: Fragment1Binding? = null
    fun sss() {
        viewBinding?.img?.let {
  /////////////////      //////////////////////////////    ///
            Glide.with(it)
                .load("http://img.lenagold.ru/k/kot/kosh518.png")
                .into(viewBinding.img)
  ///////////////////     /////////////////////////     ///
            Picasso.get()
                .load("http://img.lenagold.ru/k/kot/kosh518.png")
                .into(viewBinding.img)

            Picasso.get()
                .load("http://pngimg.com/uploads/sun/sun_PNG13451.png") //можем через Uri ,ссылка на картинку,загружаем
                //.transform()//делаем формку картинки через канвас,разобраться потом
                .rotate(90f) //развернуть картинку
                .centerCrop() //обрезать по центру
                .placeholder(R.drawable.googleg_standard_color_18) //отображать картинку пока не загружено нужное изображение
                .into(viewBinding.img) //в наш имедж вью
///////////////////////////////////////////////
            //Coil
            val request = LoadRequest.Builder(requireContext())//создаем запрос для загрузки картинки
                .data("http://img.lenagold.ru/k/kot/kosh518.png")
                .placeholder(R.drawable.googleg_standard_color_18)//когда грузится
                .error(R.drawable.googleg_standard_color_18)//когда ошибку получили
                    //или в таргете
                .target(
                    onStart = {},//начало загрузки
                    onError = {},//картинки нет
                    onSuccess = { viewBinding.img.setImageDrawable(it)}//картинка загрузилась
                )
                .transformations(CircleCropTransformation())//указываем что картинка будет круглой
                .build()//билдим запрос

            ImageLoader(requireContext()).execute(request)//загружаем картинку

//трансформ картинки урок 7 время 1:15 +-

        }
    }
}