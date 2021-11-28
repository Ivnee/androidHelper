package com.geek.infoandroid.android.MaterialDesign7;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.geek.infoandroid.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class SnackBarAndBottomNavMenu extends AppCompatActivity implements BottomSheetClickListener {//реализуем интерфейс колбек
//ЧТОБЫ РАБОТАЛИ ЭЛЕМЕНТЫ МАТЕР ДЕЗАЙНА НУЖНА ТЕМА МАТЕРИАЛ ДЕЗАЙНА
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {//юзаем из монитора материал дезайн
        super.onCreate(savedInstanceState);
        //fab
        FloatingActionButton fab = findViewById(R.id.fab); //определяем флоатинг экшн батон
        fab.setOnClickListener(new View.OnClickListener() {//клик клистерер для FloatinActionButton
            @Override
            public void onClick(View view) {//реализуем в онклик снекбар (требует на вход вьюху,которая его вызывает или вообще общий вью) вызывается только с активити на которой ты находишься
                Snackbar.make(view,"сообщение для снекбара",Snackbar.LENGTH_SHORT).setAction("экшн(текс для нажатия)", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {//клик листенер для нажатия на экшн
                        System.out.println("выполняем код по нажатию на экшн");
                    }
                }).show();//показать снекбар
            }
        });
        //navigation
        BottomNavigationView navView = findViewById(R.id.nav_view);//находим навигейшн вью из макета
//способ 1
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {//можем поставить листерен на навигейшн вью
            @Override           //в нем мы получаем итем навигейшн вью,на который нажали(можно реализовать нажатие на каждый элемент с помощью свич кейс и проверки на айди
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //здесь мы выполняем конкретные действия
                return false;
            }
        });
        //способ 2
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.home).build();//айди в меню и в навигейшн у страниц должно быть одиноковыыми(указываем каке лейауты будут открываться на кнопки)
        //находим фрагмент ,который у нас является навигационным
        NavController navController = Navigation.findNavController(this,R.id.main_navigation_fragment);//находим наш фрагмент навигацию,в который все остальные фрагменты сетятся
        NavigationUI.setupActionBarWithNavController(this,navController,appBarConfiguration);//передаем наш активити,наш навигац фрагмент, и все фрагменты которые в макете навигаций
        NavigationUI.setupWithNavController(navView,navController);//все это прикручивается к BottomNavigationView



        //работает с BottomSheetDialog
        DialogFragment dialogFragment = new DialogFragment();//создаем наш диалог фрагмент
        dialogFragment.callBack = SnackBarAndBottomNavMenu.this;//определ
        dialogFragment.show(getSupportFragmentManager(),"какой-то текст ТЭГ");//вызываем у него метод шоу чтоб он показался
    }


    @Override
    public void onButton1Click() {
        System.out.println(1);//реализуем в активити кнопку 1
    }

    @Override
    public void onButton2Click() {
        System.out.println(2);//реализуем в активити кнопку 2
    }
}
