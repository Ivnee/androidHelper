package com.geek.infoandroid.Level2.les1.Menus.dop;

public class CommentAboutMenu {
/*
 КОНТЕКСТНОЕ МЕНЮ (context menu) - вызывается при долгом нажатии на элемент меню( появляется по середине экрана)
     1:Меню
        1)аналогично верхнему меню
     2:в активити
     1)переопределяем onCreateContextMenu
     2)в нем раздуваем наше меню,которое делали до этого getMenuInflater().inflate(R.menu.top_menu,menu);
     3)переопределяем onContextItemSelected(item)
     4)в нем находим айди нажатого итема на менюшке int id = item.getItemId()
     5)далее обрабатываем кайдый итем через свич
                                                    а)switch (id){
                                                    б)case R.id.menu_add:{
                                                    в)делаем какой-то код
                                                    г)break;}
     6)регистрируем контект меню на нужные нам вьюхи через активити activity.registerForContextMenu(holder.textView) , если в активити вызываем то можно без activity.
     7)снять регистрацию с вьбхи unregisterForContextMenu(holdet.textView);



 ВСПЛЫВАЮЩЕЕ МЕНЮ (popup menu) - появляется по короткому тапу над твой вьюхой которую вы нажали
    1:Активити
        1)создаем у вьюхи ,с которой будет вызываться попап меню клик листенер
        2)в нем создаем PopupMenu popupMenu = new PopupMenu(MenuActivity.this,view); и в качестве контекста даем именно нашу активити, иначе не работает
        3)getMenuInflater().inflate(R.menu.top_menu,popupMenu.getMenu()); раздуваем то меню,которое хотим вызывать
        4)создаем клик листенер  popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
        5)в этом клик листеноре получаем menuItem в виде аргумента и берем его айди menuItem.getItemId;
        6)потом через свич так же как и до этого определяем для каждого итема из меню свой код и возвращаем тру или фолс
        6)после этого листенера вызываем popupMenu.show(); чтоб показать наше меню



 ВЕРХНЕЕ МЕНЮ - показывается либо с помощью трех точек на тулбаре либо иконками на тулбаре
     1: Меню
         1)создаем меню <menu>...<menu>
         2)в нем создаем итемы<item...><> в которых указываем следующие параметры
            а)id="@+id/menu_add" ID
            б)icon - обозначаем иконку
            в)orderInCategory="10" //приоретет итема,чем больше тем он ниже расположен
            г)showAsAction=""  как будет показываться 1]ifRoom - если есть место
                                                      2]always- всегда (лучше не применять,может испортить интерфейс)
                                                      3]collapseActionView -используют вместе с координатор лейаутом когда нам нужно красиво прятать наши элементы
                                                      4]never - никтогда не показывать(БЕЗПОЛЕЗНАЯ ВЕЩЬ)
                                                      5]withText - показывается иконочка с текстом
            д)android:title="add" />
         2:в активити
         1)Переопределяем onCreateOptionsMenu для подключения меню и в нем   getMenuInflater().inflate(R.menu.top_menu,menu);//отображаем наше меню,регаем макет меню и возвращаем return true;
         2)переопределяем onOptionsItemSelected для обработки нажатий на меню
         3)в нем находим id нажатого элемента int id = item.getItemId(); item приходит на вход
         4)далее выполняем обработку через свич
                                                        а)switch (id){
                                                        б)case R.id.menu_add:{
                                                        в)делаем какой-то код
                                                        г)break;}





 БОКОВОЕ МЕНЮ - меню которое выдвигается сбоку
 1: Макет мейн активити

         1)Создаем дровер ,это главный лейаут активити(DrawerLayout)
            a)tools:openDrawer="start"//откуда будет выдвигаться окно навигации(говорит о том что в макете должно быть выдвинуто это меню)
            б)г)android:fitsSystemWindows="true"//может заезжать под статус бар


         2)в него засовываем AppBarLayout
         3)в него Toolbar
         4)в дровер создаем fragment у которого
                а)name="androidx.navigation.fragment.NavHostFragment"    пометка что фрагмент для навигации
                б)navGraph="@navigation/вставляем навигацию с фрагментами"/>        какие фрагменты там будут
         5)в дровер создаем NavigationView(обязательно в самом низу дровер лейаута создавать)
                а)headerLayout="@layout/любой лейаут" создает шапку сайдменю
                б)menu="@menu/side_menu" />// создает сами итемы
                в)android:layout_gravity="start" //указываем обязательно,откуда будет выезжать наш дровер
                г)android:fitsSystemWindows="true"//может заезжать под статус бар
                д)app:itemIconTint="@color/colorAccent"//цвет иконок в боковом меню
                е) app:itemTextColor="@color/colorPrimaryDark"/> //цвет текста в боковом меню



     2:Меню

         1)создаем меню
         2)в нем создаем  <group android:checkableBehavior="single"> ..  <group>(в этой группе будет выделяться только 1 элемент при нажатии(single отвечачет за это,можно выбрать все))
         3)в нем создаем итемы
         4)в итемах ставим id , icon , title ( id должен совпадать с id в navigation)

     3:Навигация navigation
         1) создаем <navigation app:startDestination="@id/home1">... <navigation> ,startDestination - стартовый фрагмент,сетится по айди
         2) в нем создаем <fragment> в котором вставляем следующие свойства
                а)id должен быть как у меню
                б)name, название фрагмента который будет отображаться
                в)label, имя строчки
                г)tools:layout="layout:fragment" //просто чтоб отображался фрагмент в редакции макета

     4:Активити
         1)находим вьюхи через findViewById()
                а)Toolbar
                b)DrawerLayout основной лейаут в макете активити
                c)NavigationView , вью в которой устанавливаем шапку и итемы меню
         2) setSupportActionBar(toolbar); подвязываем тулбар к экшнбару (можно скрыть титул в тулбаре getSupportActionBar().setDisplayShowTitleEnabled(false);)
         3)создаем AppBarConfiguration a = new AppBarConfiguration.Builder(R.id.home1,R.id.home2)и сетим в него все фрагменты из navigation папки
                a).setDrawerLayout(drawerLayout) так же устанавливаем дровер лейаут ,который находили по айди
                б).build(); создаем аппбар
         4)NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment); //создаем контролер в котором подвязываем фрагмент для отоброжения нав фрагментов к данной активити
         5)NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration); //устанавливаем контроллер(с нав контейнер фрагментом) и апп бар конфигур(с списком фрагментов из navigation)
         6)NavigationUI.setupWithNavController(navigationView,navController);//к контроллеру (с контейнер фрагментом) устанавливаем навигайшон вью(само сайд меню)

         7)переопределяем 2 метода ,
                a)onCreateOptionsMenu// помоему работало без этого
                   а.1) getMenuInflater().inflate(R.menu.main, menu); //поидее добавляем сендвич(можно попробовать без нее
                                return true;
                b)onSupportNavigateUp //метод для подключения навигации(разобраться что внутри)
                    б.1)   NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
                            return NavigationUI.navigateUp(navController, appBarConfiguration)|| super.onSupportNavigateUp();

 */
}
