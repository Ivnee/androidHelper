package com.geek.infoandroid.android.Level2.les1.Menus;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.geek.infoandroid.R;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {
    AppBarConfiguration appBarConfiguration;
    Button popUpMenuButton;
    MenuRVadapter adapter = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);//сетим активити макет
        popUpMenuButton = findViewById(R.id.menu_button);
        initList();//создаем ресайкл вью
        createPopUpMenu();
        createSideMenu();
    }



    //popupMENU
    private void createPopUpMenu() {
        popUpMenuButton.setOnClickListener(new View.OnClickListener() {//кликлистенер
            @Override//при нажатии на эту кнопку будет создаваться попапмену
            public void onClick(View view) {
                //создаем попап меню(меню которое вылезает рядом с вьюхой при единичном нажатии
                PopupMenu popupMenu = new PopupMenu(MenuActivity.this,view, Gravity.BOTTOM);//в качестве контекста передаем обязательно нашу активити(гравити указываем в каком месте относительно нашей вьюхи (на которую мы нажали ) вылезет меню)(гравити не обязательный параметр)
                getMenuInflater().inflate(R.menu.top_menu,popupMenu.getMenu());//показываем наш попап меню,передаем в него макет того как наше меню выглядит и получаем меню у смого попапменю потому как  он принимает только меню
                popupMenu.getMenu().add(0,12345,12,"добавить");//добавлить итем в менюшку програмно(1)хз2)id итема3)приоретер того как он расположен 4)титул итема)
                popupMenu.getMenu().findItem(R.id.menu_add).setVisible(false);//нашли наш итем который в меню
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        menuItem.getItemId();//получаем айди нажатого итема в меню
                        return false;
                    }
                });
                popupMenu.show();//показать попап меню
            }
        });
    }
//боковое меню side menu
    private void createSideMenu() {
        //для такого подхода нужны зависимости гредл
        Toolbar toolbar = findViewById(R.id.toolbar);//находим тулбар
        setSupportActionBar(toolbar);//назначаем тулбар выполнять функции экшн бар
        getSupportActionBar().setDisplayShowTitleEnabled(false);//убрать титул в тулбаре

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);//затем находим дровер лейаут(этот лейаут для активити в которой реализованы все dьюхи сайд меню)
        NavigationView navigationView = findViewById(R.id.sidemenu_navigation_view);//создаем нав вьб для навигации по фрагментам(фрагменты для навигации создаем в папке navigation и делаем меню для них с такими же айдишниками у итемов в меня как и у фрагметов  в навигайшон)
        appBarConfiguration = new AppBarConfiguration.Builder(R.id.home1,R.id.home2)//создаем сендвич в котором будет сайд меню, сетим фрагменты из навигации
                .setOpenableLayout( drawerLayout)//сетим наш дровер лейаут (основной лейаут в активити с сайд меню)
                .build();//коммитим все что заполнили

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);//указываем что эта активити навигационное и в какой навигационный фрагмент мы должны сетить фрагменты
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);//устанавливаем в контроллер тулбар с сендвичем
        NavigationUI.setupWithNavController(navigationView,navController);//устанавливаем навигейшн вью
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }


    //верхнее меню
    @Override//создает верхнее меню на тулбаре
    public boolean onCreateOptionsMenu(Menu menu) {//здесь определяется макет из папки меню
        getMenuInflater().inflate(R.menu.top_menu,menu);//отображаем наше меню,регаем макет меню

        //создаем иТем для поиска(для этого надо указать в xml параметр         app:actionViewClass="androidx.appcompat.widget.SearchView"
        //сначала тулбар засетим
       /* Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
       //потом уже создаем
        MenuItem search = menu.findItem(R.id.search);// находим наш серчь вью
        final SearchView searchText = (SearchView) search.getActionView();//определяем его как элемент серч вью (а не как итем меню)
        searchText.setOnQueryTextListener(new SearchView.OnQueryTextListener() {//устанавливаем клик листенер на ввод текста(едит текст появляется автоматом у этого итема)
            @Override
            public boolean onQueryTextSubmit(String query) {//выполняется после того как ввели текст
                Snackbar.make(searchText,query, Snackbar.LENGTH_SHORT).show();//выводит текст,который мы ввели в едит текст нашего серча(
                return true;//чтоб сделать поиск надо вручную обрабатывать этот метод,так эта вьюха дает просто эдит текст и итем
            }

            //этот метод выполняется полсе добавления/удаления каждой буквы
            @Override
            public boolean onQueryTextChange(String newText) {//выполняется вовремя ввода текста
                return true;
            }
        });
        return true;
    }

    @Override//для верхнего меню на тулбаре
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {//метод для обработки нажатий на элементы меню
        int id = item.getItemId();//получаем айди нажатого элемента в меню
        switch (id){
            case R.id.menu_add:{
                //делаем какой-то код
                break;
            }
            case R.id.menu_clear:{
                //делаем какой-то код 2
                break;
            }
            case R.id.menu_edit:{
                //опять делалем какой-то код и тд
                break;
            }
            default:{
                //делаем что-то в конце свича
            }
        }
        return super.onOptionsItemSelected(item);
    }
//контекстное меню
    @Override//для создания контекстного меню(которое появляется посередине и запускается при долгом нажати)
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.top_menu,menu);//раздуваем контекстное меню
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();//получаем айди нажатого элемента
        switch (id){
            case R.id.menu_add:{
                //делаем какой-то код
                break;
            }
            case R.id.menu_clear:{
                //делаем какой-то код 2
                break;
            }
            case R.id.menu_edit:{
                //опять делалем какой-то код и тд
                break;
            }
            default:{
                //делаем что-то в конце свича
            }
        }
        return super.onContextItemSelected(item);
    }

    private void initList() {

        ArrayList<Integer> data = new ArrayList<>();
        data.add(1);
        data.add(2);
        data.add(3);
        data.add(4);
        adapter = new MenuRVadapter(data, this);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());

        RecyclerView recyclerView = findViewById(R.id.recycler_view_menu);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }
}
