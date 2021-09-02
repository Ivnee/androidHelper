package com.geek.infoandroid.Kotlin.materialDesign.les7

import androidx.fragment.app.Fragment
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.BackgroundColorSpan
import android.text.style.BulletSpan
import android.text.style.QuoteSpan
import android.text.style.RelativeSizeSpan
import android.widget.TextView
import com.geek.infoandroid.R

class AddFontsAndSpan :Fragment(R.layout.fragment1){


    //1)создаем папку пкм рес, new - folder- assets и  добавляем туда скаченный ттф или создаем его сами
    //чтоб создать в макте на текст вью,там fontFamily в поиске и нажимаем на полоску справа,плюс,галочку на адд ин проджект
    // safeBinding.text.typeface = Typeface.createFromAsset(requireActivity().assets,"the_sonnyfive.ttf")//устанавливаем шрифт на наш текст вью из папки ассетс

    //2)загружаем шрифт Maket-design-fontFamily-левая полоска:плюси-addFontToProject-ok
    // указываем в TextView в макете
    // android:fontFamily="@font/mountains_of_christmas"

    //3)загружаем шрифт Maket-design-fontFamily-левая полоска:плюси-createDownloadableFont-ok
    //добавился тоже в папку фонт и юзается через xml fontFamily как и предыдущий(этот шрифт не хранится в проэкте а загружается)
    //в values Добавится preload_fonts.xml , который необходимо прописать в манифесте(пропишется автоматом при добавлении)
    // <meta-data
    //            android:name="preloaded_fonts"
    //            android:resource="@array/preloaded_fonts" />


    //4)добавляем точку перед строкой текста и вообще любой html text
    // val htmlText = "My text <ul><li>zel one</li><li>zel two</li></ul>"
    //safeBinding.text.text = Html.fromHtml(htmlText)

    //5)span
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        val spannable =
            SpannableString("zel top th \n zel top sb \n zel zel zel")//текст который мы будем изменять
        spannable.setSpan(//метод для добавления фишек
            BulletSpan(//добавляет круг
                30,
                Color.MAGENTA,
                50,
            ),//добавляем круг,1отступы,2цвет,3размер(ставится только вначале строки)
            12,//с какого символа начинает
            25,//до какого символа
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannable.setSpan(
            QuoteSpan(Color.GREEN),//"|"ставится только вначале
            0,//с 0
            6,//до 6
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        //ForegroundColorSpan(Color.RED),//изменить цвет текста
        //.setSpan(UnderlineSpan(), 0, content.length(), 0)//подчеркивание текста
        safeBinding.text.text = spannable


        //делаем спанабл фишки по нажатию
        var bools = true
        safeBinding.text.setText(
            spannable,
            TextView.BufferType.SPANNABLE
        )//задали текст и тип спанабл
        val spanText = safeBinding.text.text as Spannable
        safeBinding.text.setOnClickListener {
            if (bools) {
                bools = false
                spanText.setSpan(
                    BackgroundColorSpan(Color.CYAN),//цвет задника у букв 2-6
                    2, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                spanText.setSpan(
                    RelativeSizeSpan(2f), //размер букв
                    6, 9, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            } else {
                bools = true
                spanText.setSpan(
                    BackgroundColorSpan(Color.YELLOW),
                    2, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                spanText.setSpan(
                    RelativeSizeSpan(0.5f),
                    6, 9, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
        }
    }
}