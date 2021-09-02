package com.geek.infoandroid.Level2.les1;

public class XMLCommands {
    /*
//тулбар
           <androidx.appcompat.widget.Toolbar//создаем тулбар (ставим тему без тулбара
            android:id="@+id/toolbar_menu_lesson">

            <EditText
                android:maxLines="1"//максимальное количество строк
                android:visibility="gone"/>//видимость ,не видно
        </androidx.appcompat.widget.Toolbar>
        чтобы тулбар не закрывал элементы списков сверху, указываем у списка параметр :
    <androidx.recyclerview.widget.RecyclerView
        app:layout_behavior="@string/appbar_scrolling_view_behavior"/>

//DrawerLayout и side menu  и навигайшон вью
    implementation 'androidx.navigation:navigation-fragment:2.2.1'
    implementation 'androidx.navigation:navigation-ui:2.2.1'

            <androidx.drawerlayout.widget.DrawerLayout//лейаут для хранения и настроек навигейшн вью(для бокового меню)(юзаем в мейн активити)
                android:fitsSystemWindows="true"//растягиваем наш дровер лейаут по всему содержимому окна, не под тулбаром
                tools:openDrawer="start">//откуда выезжает меню(вроде)

                    <com.google.android.material.appbar.AppBarLayout// затем вставляем аппбарлейаут,для тулбара(тему нужно без тулбара , иначе будет 2 тулбара )
                        >

                        <androidx.appcompat.widget.Toolbar//тулбар с сендвичем для открыванию бокового меню
                            android:id="@+id/toolbar"//id
                            android:background="?attr/colorPrimary"//цвет
                            app:navigationIcon="@android:drawable/ic_input_add"//иконка для открывания бок меню
                            app:popupTheme="@style/Theme.AppCompat.Light" />//тема

                        <fragment//фрагмент к которому подключается навигейшн с фрагментами
                            android:id="@+id/nav_host_fragment"//создаем айди
                            android:name="androidx.navigation.fragment.NavHostFragment"
                            app:defaultNavHost="true"
                            app:navGraph="@navigation/mobile_navigation" />//определяем навигационный макет ,в котором наши фрагменты содержатся


                        <com.google.android.material.navigation.NavigationView//навигейшн вью отвечает за переключение страниц
                                android:fitsSystemWindows="true"//устанавливаем ее на весь дровер лейаут
                                app:headerLayout="@layout/sidemenu_head"//устанавливаем шапку для сайдменю
                                app:menu="@menu/side_menu" />   //устанавливаем итемы для переключения фрагментов на сайдменю
    </androidx.drawerlayout.widget.DrawerLayout>

//navigationView
            <com.google.android.material.navigation.NavigationView// сама панель в которой осуществляется навигация и в которой хеад
                android:id="@+id/nav_view"//id
                android:fitsSystemWindows="true"//растянуть полностью ,над тулбаром
                app:headerLayout="@layout/nav_header_main"//указываем лейаут который будет сверху навигейшн вью
                app:menu="@menu/activity_main_drawer" />//меню со списком всех названий страниц
//меню
        <menu >//создать меню
            <item//создать элемент меню
                android:icon="@android:drawable/ic_menu_add"//добавить иконку меню
                android:orderInCategory="10"//приоритет иконки, чем меньше цифра тем выше элемент

                app:showAsAction="ifRoom"//если есть место то показывать на тулбаре в виде иконки
                always// всегда показывает кнопку,даже есть нет места(лучше не применять,может испортить интерфейс)
                collapseActionView //используют вместе с координатор лейаутом когда нам нужно красиво прятать наши элементы
                never//никтогда не показывать на тулбаре
                withText// показывается иконочка с текстом

                android:title="название" />//
                <menu>//раскрывающийся элемент в меню(вложенное меню
                    <item//итем раскрывающегося элемента
                        android:title="какая-то кнопка"/>
                    <item//итем раскрывающегося элемента
                        android:title="какая-то кнопка" />
                </menu>
            </item>
        </menu>*/

//menu для сайд меню
/*В НАВИГАЦИИ И В МЕНЮ ИМЕНА ЭЛЕМЕНТОВ ДОЛЖНЫ БЫТЬ ОДИНАКОВЫМИ


    <group android:checkableBehavior="single">//СОЗДАЕТ ГРУППУ В КОТОРОЙ ВЫБРАННЫЙ ЭЛЕМЕНТ ПОДСВЕЧИВАЕТСЯ(1 ЭЛЕМЕНТ
            <item
            android:id="@+id/nav_home"//ID
            android:icon="@drawable/ic_menu_camera"//КАРТИНКА
            android:title="НАЗВАНИЕ" />//НАЗВАНИЕ ОКНА НА САЙД МЕНЮ
            </group>
*/

//Navigation навигация по фрагментам для сайд меню
/*В НАВИГАЦИИ И В МЕНЮ ИМЕНА ЭЛЕМЕНТОВ ДОЛЖНЫ БЫТЬ ОДИНАКОВЫМИ
    <navigation
        android:id="@+id/navigation_side_menu"// cтавим айди
        app:startDestination="@+id/frag1">//стартовую страницу навигации

        <fragment //1)определяем какие фрагменты будут в навигации
        android:id="@+id/frag1"//определяем айди фрагмента
        android:name="com.geek.infoandroid.Fragments5.Fragment1"//определяем сам фрагмент
        android:label="home"//название странички на боковом меню
        tools:layout="@layout/fragment1"/>//отображать во время работы с макетом сам фрагмент(tools)

        <fragment //2)
        android:id="@+id/frag2"
        android:name="com.geek.infoandroid.Fragments5.Fragment2"
        android:label="home2"
    tools:layout="@layout/fragment2"/>

</navigation>

//отключаем тулбар и отключаем название аппа на тулбаре
    <style name="AppTheme.NoActionBar">
        <item name="windowActionBar">false</item>
        <item name="windowNoTitle">true</item>
    </style>
*/
}
