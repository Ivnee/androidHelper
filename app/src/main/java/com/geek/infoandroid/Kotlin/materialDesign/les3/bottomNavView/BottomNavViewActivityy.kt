package com.geek.infoandroid.Kotlin.materialDesign.les3.bottomNavView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.geek.infoandroid.R
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavViewActivityy : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_nav_view_activityy)
        val navigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
/*        navigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {//слушатели на все иконки боттом навигейшн вью
                R.id.fragment1 -> {
                    true
                }
                R.id.fragment2 -> {
                    true
                }
                else -> {
                    true
                }
            }
        }*/
        //navigationView.selectedItemId = R.id.fragment1//указываем какой итем будет выбран
        //navigationView.setOnItemReselectedListener {  }//срабатывает когда нажимаешь на итем ,который уже выбран(типа чтоб вернуться на стартовую страницу из вложенности)
        //Создание бейджей
        //navigationView.getOrCreateBadge(R.id.fragment1)//где будем создавать бейджи(меню айтем)
        //val badge = navigationView.getBadge(R.id.fragment1)//получаем бэйдж нашего меню айтема
        //badge?.maxCharacterCount =3 //максимальное количество чисел(99+)если 2 то (9+)
        //badge?.number = 25//число ,которое будет отображаться
        //badge?.badgeGravity = BadgeDrawable.BOTTOM_END//где находится бэйдж относительно айтема
        //badge?.clearNumber() // убрать число с бэйджа
        //navigationView.removeBadge(R.id.fragment1)//полностью убрать бэйдж
    }

}
//XML
/*
<androidx.constraintlayout.widget.ConstraintLayout// можно в любом лэйауте
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".ui.main.BottomNavView.BottomNavViewActivity">

<androidx.fragment.app.FragmentContainerView
        android:id="@+id/fragment_container"/>

<com.google.android.material.bottomnavigation.BottomNavigationView
        android:id="@+id/bottom_navigation_view"
        app:menu="@menu/bottom_navigation_menu"// какое меню мы устанавливаем
        app:itemIconTint="@color/white //цвет иконки
        app:itemBackground="@color/teal_200" //цвет самого фона
        app:itemTextColor="@color/white"//цвет текста итема
        app:labelVisibilityMode="selected" //selected(показывает текст когда выбрано),unlabeled(без текста),auto(если помещаются лейблы,то показывает)" />

</androidx.constraintlayout.widget.ConstraintLayout>*/