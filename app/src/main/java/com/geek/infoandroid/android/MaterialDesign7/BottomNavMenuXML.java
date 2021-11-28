package com.geek.infoandroid.android.MaterialDesign7;



public class BottomNavMenuXML {/*
ЧТОБЫ РАБОТАЛИ ЭЛЕМЕНТЫ МАТЕР ДЕЗАЙНА НУЖНА ТЕМА МАТЕРИАЛ ДЕЗАЙНА

    implementation 'androidx.navigation:navigation-fragment:2.2.1'
    implementation 'androidx.navigation:navigation-ui:2.2.1'
//В САМОМ МАКЕТЕ АКТИВИТИ xml

    <com.google.android.material.bottomnavigation.BottomNavigationView //создаем в макете вьюху навигацию
    android:background="?android:attr/windowBackground"
    app:menu="@menu/bottom_nav_menu" />   //сетим меню ,которое мы составили сами

<fragment//фрагмент контейнер для навигации
        android:name="androidx.navigation.fragment.NavHostFragment"//сетим активити по умолчанию
        app:defaultNavHost="true"      //указываем что это нафигация и фрагмент является контейнеом для всех страничек
        app:navGraph="@navigation/какой_то_навигационный_хмл" />//сетится из папки навигейшн в которой составлен хмл со списком наших страниц


//В АКТИВИТИ КОД ДЖАВА
          BottomNavigationView navigationView = findViewById(R.id.nav_view);
        AppBarConfiguration configuration = new AppBarConfiguration.Builder(R.id.menu1,R.id.menu2,R.id.menu3).build();
        NavController controller = Navigation.findNavController(this,R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this,controller);
        NavigationUI.setupWithNavController(navigationView,controller);
//В МЕНЮ СОЗДАЕМ СТРАНИЧКИ
    <menu xmlns:android="http://schemas.android.com/apk/res/android">

    <item//страничка
        android:id="@+id/navigation_home"
        android:icon="@drawable/ic_home_black_24dp"//картинка странички
        android:title="home" />//название странички

</menu>


//В НАВИГЕЙШН СОЗДАЕМ МАКЕТ С ФРАГМЕНТАММИ
<navigation
    app:startDestination="@id/home">//установить стартовую страничку в навигации
            <fragment
        android:id="@+id/navigation_home"//создаем айди фрагмента
        android:label="home"//устанавливаем его имя
        android:name="com.geek.infoandroid.android.Fragments5.Fragment1"/>//устанавливаем какой фрагмент будет он открывтать
</navigation>
    */
}
