package com.geek.infoandroid.MaterialDesign7;

public class MaterialDesignXML {
/*
ЧТОБЫ РАБОТАЛИ ЭЛЕМЕНТЫ МАТЕР ДЕЗАЙНА НУЖНА ТЕМА МАТЕРИАЛ ДЕЗАЙНА
//Material Design
//лейаут для эдит текст и эдит текст
    <com.google.android.material.textfield.TextInputLayout          //лейаут для эдиттекста материал дезайн(обязательная) только для едит текстов используется
    app:errorEnabled="true">            //включить отслеживание ошибок при вводе в едит тексте

            <com.google.android.material.textfield.TextInputEditText    //сам эдит текст материал дезайна
    android:inputType="textPersonName"    //тип вводимых данных
    app:passwordToggleEnabled="true"//включает глаз при нажатии скрыть открыть видимость пороля
    app:counterEnabled="true"//отображает сколько символов мы ввели
    />
    </com.google.android.material.textfield.TextInputLayout>


    //кнопка материал батн
    <com.google.android.material.button.MaterialButton
      app:cornerRadius="20dp"//закругление углов у кнопки
        app:icon="@drawable/ic_launcher_foreground"//добавить иконку на кнопку
      app:strokeColor="@color/colorPrimary"//цвет контура кнопки
      style="@style/Widget.MaterialComponents.Button.OutlinedButton"//тема кнопки(эта тема пустая кнопка внутри,только обведена линией)
      />

        <com.google.android.material.floatingactionbutton.FloatingActionButton//кнопка которая подлетакт при вызове снэкпара
        android:id="@+id/fab"
         app:rippleColor="@color/colorPrimary"//цвет при нажатии
         app:backgroundTint="@color/colorPrimary"//цвет кнопки
        app:srcCompat="@android:drawable/ic_dialog_email" />//задаем иконку кнопке

//тулл бар с фишками

//все это должно быть в координатор лейауте(нужно потестить)

<androidx.coordinatorlayout.widget.CoordinatorLayout>//неста скролл вью тоже сюда пихается

        <com.google.android.material.appbar.AppBarLayout //апп бар лейаут для хранения тулбара и осуществления в нем функций
        android:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar">

        <com.google.android.material.appbar.CollapsingToolbarLayout   // это тулбар в развернутом состоянии
            android:fitsSystemWindows="true"    //заполнить полностью весь экран в котором он находится
            app:expandedTitleMarginEnd="64dp"// отступ справа у заголовка тулбара = 64дп  ( в развернутом состоянии)
            app:expandedTitleMarginStart="48dp" //отступ слева 48 дп  (в развернутом состояни)
            app:contentScrim="?attr/colorPrimary"  //цвет нашего контента когда мы его свернули( тулбар)
            app:layout_scrollFlags="scroll|exitUntilCollapsed"> //будет работать коллапсинг тулбар на скролл и будет выезжать когда скролл текста не идет уже(когда уперся в потолок)

            <ImageView    //контент нашего тулбара в развернутом состоянии
                android:fitsSystemWindows="true"//в полный размер того где он находится
                android:scaleType="centerCrop"//вроде это обрезание краев картинки
                app:layout_collapseMode="parallax" />//эввект скрывания картинки(параллакс)

            <androidx.appcompat.widget.Toolbar              //стандартный тулбар
                app:layout_collapseMode="pin"             //как будет тулбар в свернутом виде(пин в стандартную полоску)
                app:popupTheme="@style/ThemeOverlay.AppCompat.Light"/>             //тема тулбара( вроде в свернутом виде)

        </com.google.android.material.appbar.CollapsingToolbarLayout>
    </com.google.android.material.appbar.AppBarLayout>
</androidx.coordinatorlayout.widget.CoordinatorLayout>

//NestedScrollView
    <androidx.core.widget.NestedScrollView
        app:layout_behavior="@string/appbar_scrolling_view_behavior">//этой строкой задаем что когда мы скролим контент в конце начинает скролиться тулбар( это строка системная,не наша)
            <include layout="@layout/include_layout" />инклуд вставляет в неста скрол вью все содержимое указанного монитора(можно просто так добавить
             //внутри неста скрол вью устанавливается контент
    </androidx.core.widget.NestedScrollView>


//styles стили применяются через style для любой вьюхи
    style name="gogostyle" parent="пишем от чего наследуемся">//имя стиля и родитель(можно переопределять элементы подителя
        <item name="android:layout_margin">10dp</item>
        <item name="cardCornerRadius">10dp</item>//закругляем углы кнорки
        <item name="android:textSize">18dp</item>//размер текста
    </style>

    //Theme
  <style name="myFirstTheme" parent="Theme.AppCompat.Light.DarkActionBar"> //создаем тему и наследуем от любой другой(применяется тема в манифесте или через setTheme
        <item name="colorPrimary">#CDDC39</item>//установить цвета которые использует наше приложение
        <item name="colorPrimaryDark">#FFEB3B</item>
        <item name="colorAccent">#FFC107</item>

        <item name="buttonStyle" >@style/Widget.MaterialComponents.Button.OutlinedButton</item> все кнопки в этой теме будут стаким стилем,можно любые настройки для кнопок засунуть сюда(отступ,цвет и тд)


     */
}

