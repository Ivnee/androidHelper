package com.geek.infoandroid.Level2.les1.Menus.dop;

import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.geek.infoandroid.R;
import com.google.android.material.navigation.NavigationView;

public class sideMenuVlaskin implements NavigationView.OnNavigarionItemSelectedListener {
//сайд меню если мы хотим назначить какие-то действия на итемы бокового меню(еще есть для переключения между фрагментами в мену активити)
    private void initSideMenu(Toolbar toolbar) {
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);//находим наш основной лейаут
        NavigationView navigationView = findViewById(R.id.nav_view);//нахоим навигейшн вью
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_nav,R.string.close_nav);//это гамбургер,на вход не принимает строки,только айди строк
        drawerLayout.addDrawerListener(toggle);//устанавливаем наш гамбургер в лейаут дровера(устанавливаем чтоб он мог слушать все перемещения )
        toggle.syncState();//синхронизируем состояние??
        navigationView.setNavigationItemSelectedListener(this);//вешаем листенер на наш навигейшн вью(листенер имплементили и обрабатываем все наши итемы в переопределенном методе)
        //////
        navigationView.setCheckedItem(R.id.nav_gallery);//по умолчанию будет установлен итем нав галлери
    }
    @Override//переопределяем листенер
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {//обрабатываем итемы менюхи
        //обрабатываем наши нажатия на итемы менюхи
        int id = item.getItemId();
        switch (id){
            case R.id.nav_gallery:
                Toast.makeText(getBaseContext(),"gallery",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.nav_home:
                Toast.makeText(getBaseContext(),"home",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.nav_send:
                Toast.makeText(getBaseContext(),"send",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.nav_share:
                Toast.makeText(getBaseContext(),"share",Toast.LENGTH_SHORT).show();
                return true;
        }
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);//закрываем наш лейаут после нашажитя
        return true;
    }
}
