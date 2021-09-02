package com.geek.infoandroid.Kotlin.materialDesign.les3.tabs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.geek.infoandroid.R
import com.google.android.material.tabs.TabLayout

// для установки индикатора(точки указывающие текущий фрагмент)
// implementation 'com.tbuonomo:dotsindicator:4.2'
class TabActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab)
        //устанавливаем пейджер
        val viewPager = findViewById<ViewPager>(R.id.view_pager)
        viewPager.adapter = ViewPagerAdapter(supportFragmentManager)
//дополнительные установки для пейджера кастомные картинки и тд можно посмотреть в примере третьего урока или в лекциях


        /////устанавливаем иконки для табов
        val tabLayout  = findViewById<TabLayout>(R.id.tab_layout)
        tabLayout.getTabAt(0)?.setIcon(R.drawable.ic_launcher_background)
        tabLayout.getTabAt(1)?.setIcon(R.drawable.ic_launcher_foreground)
        tabLayout.getTabAt(2)?.setIcon(R.drawable.ic_launcher_background)

        //установка дот индикатора
        val dotsIndicator = findViewById<WormDotsIndicator>(R.id.dots_indicator)
        dotsIndicator.setViewPager(viewPager)
    }

}
//XML
/*
<androidx.viewpager.widget.ViewPager//окно ,в котором перелистываются фрагменты
android:id="@+id/view_pager"
android:layout_width="match_parent"
android:layout_height="match_parent">

    <com.google.android.material.tabs.TabLayout//окошко,в котором кнопки для переключения фрагментов
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:tabIndicatorAnimationDuration="200"//как долго будет перелистываться таб
    app:tabMode="auto" //scrolled(скролит кнопки)fixsed(статически размещает кнопки)auto(если не хватает места ,скролит )
    app:tabGravity="fill"расположение таб кнопок(если start то все кнопку к началу своей ячейки прикрепляются)/>
</androidx.viewpager.widget.ViewPager>

//ИНДИКАТОР
// implementation 'com.tbuonomo:dotsindicator:4.2'
<com.tbuonomo.viewpagerdotsindicator.DotsIndicator
        android:id="@+id/dots_indicator"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:dotsColor="@color/black"//цвет точки
        app:dotsCornerRadius="8dp"//Радиус угла точки (по умолчанию половина dotsSize для округлости)
        app:dotsSize="16dp"//размер точек
        app:dotsSpacing="4dp"//расстояние между точками
        app:dotsWidthFactor="2.5"ширина выбранной точки
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:progressMode="true"//показывает загрузку на индикаторе??
        app:selectedDotColor="@color/teal_200"
        dotsStrokeWidth //Ширина обводки точек (по умолчанию 2dp)
/>
*/