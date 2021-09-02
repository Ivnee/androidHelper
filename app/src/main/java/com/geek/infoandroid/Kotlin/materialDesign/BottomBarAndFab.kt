package com.geek.infoandroid.Kotlin.materialDesign

import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.content.ContextCompat
import com.geek.infoandroid.MainActivity
import com.geek.infoandroid.R
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton

class BottomBarAndFab: Fragment(R.layout.fragment1) {

    //поведение фаба (смена положения центр и энд и смена иконки)
    private fun setBottomAppBar(view: View) {
        val bottomAppBar = view.findViewById<BottomAppBar>(R.id.bottom_app_bar)
        val context = context as MainActivity
        context.setSupportActionBar(view.findViewById(R.id.bottom_app_bar))//устанавливаем боттом апп бар в сапорт экшн бар(видимо чтоб все функции меню сетились сюда)
        setHasOptionsMenu(true)//устанавливаем функции опшионс меню сюда?? или делает возможным пользоваться OptionsMenu в фрагменте
        view.findViewById<FloatingActionButton>(R.id.fab).setOnClickListener{
            if(onCentre) {
                onCentre = true
                bottomAppBar.navigationIcon = null//убрать иконку гамбургер
                bottomAppBar.fabAlignmentMode =
                    BottomAppBar.FAB_ALIGNMENT_MODE_END//задать положение фаба справа
                bottomAppBar.replaceMenu(R.menu.menu2)//поменяьб меню,которое вызывается в аппбаре
            }else{
                onCentre = false
                bottomAppBar.navigationIcon = ContextCompat.getDrawable(requireContext(),R.drawable.ic_bp)//установить гамбургер
                bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER//задать положение фаба по центру
                bottomAppBar.replaceMenu(R.menu.menu1)//поменяьб меню,которое вызывается в аппбаре

            }
        }
    }
    /*

//ОБЯЗАТЕЛЬНО КООРДИНАТОР ЛЭЙАУТ
<androidx.coordinatorlayout.widget.CoordinatorLayout
xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <com.google.android.material.bottomappbar.BottomAppBar
        android:id="@+id/bottom_app_bar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_gravity="bottom"
        app:backgroundTint="@color/colorPrimary"//цвет боттом бара
        app:fabAlignmentMode="center"//местоположение фаба внутри аппбара(end,center)
        app:fabCradleMargin="10dp"// размер выемки для фаба
        app:fabAnimationMode="slide" //анимация при изменении положения фаба(центр или енд)
        app:fabCradleRoundedCornerRadius="10dp" //закругление углов у боттом бара
        app:hideOnScroll="true"//при скроле "неста скролл вью" аппбар будет уезжать вниз(для этого обязательно использование
неста скролл вью над боттом баром)(чтоб такое реализовать с ресайкл вью надо запихнуть его в нест скрл вью и у ресайклер вью указать
nesta_scroll_enable="false)
        app:layout_scrollFlags="scroll|enterAlways"//поведение при скроле
        app:navigationIcon="@drawable/ic_hamburger_menu_bottom_bar" />//иконка,которая гамбургер,можно ставить любую

    <com.google.android.material.floatingactionbutton.FloatingActionButton
        android:id="@+id/fab"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:backgroundTint="@color/colorAccent"/цвет фона
        app:layout_anchor="@id/bottom_app_bar"//указываем куда крепится наш фаб(к боттом бару или коллапсинг тул бару)
        app:srcCompat="@drawable/ic_plus_fab"//картинкаа
        app:tint="#FFFFFF" />//цвет картинки
</androidx.coordinatorlayout.widget.CoordinatorLayout>

     */
}