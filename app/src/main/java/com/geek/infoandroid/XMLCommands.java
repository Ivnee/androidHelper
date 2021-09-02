package com.geek.infoandroid;

public class XMLCommands {
/*

<androidx.cardview.widget.CardView  //вид лэйаута кард вью(вставляются элементы внутри)
    app:cardCornerRadius="8dp"//закруглить карточку
    app:cardElevation="8dp"//приподнять карточку
    >


<view//для пробелов в основном
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"

//фишки констрейнт лейаута       ......................................
//guidline используется в констрейт лейауте, для того чтоб разделить или привязать к ней элементы(например можно вызравнивать элементы по этой линии)
ее не видно
    <androidx.constraintlayout.widget.Guideline//
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_constraintGuide_percent="0.3"// разместить с отступом на 30%  от верха экрана
        android:orientation="horizontal//ориентация линии
        />

    <androidx.constraintlayout.widget.Barrier// отделяем выбранные вьюхи от друних элементов
        android:id="@+id/barrier1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:barrierDirection="end"//указываем что барьер будет стоять после выбранных элементов
        app:constraint_referenced_ids="firstTextView,secondTextView"/>//айдишники элементов которые мы отделяем
...........................................
 <EditText
    android:ems="10"//Ширина которая измеряется в больших M(ширина 10 больших М)
    android:hint="EnterTheName" //// прозрачный текст который подсказывает
 />
//Флоатин экшн батн

<com.google.android.material.floatingactionbutton.FloatingActionButton
        android:id="@+id/fab"//Id
        app:srcCompat="@android:drawable/ic_dialog_email" />//установить картинку на фаб(письмо)

https://romannurik.github.io/AndroidAssetStudio/(CСЫЛКА ДЛЯ КАРТИНОК РАСТРОВОЙ ГРАФИКИ)сразу делает из картинки несколько экземпляров под все типы экранов
для векторной графики просто создаем сами картинку ,и она под любой экран автоматом подгонится(создается пкм по папке дровбл и там создать вектор)
  <ImageView
        android:scaleType="CentreInside" //центрируем картинку посередине
        android:src="@mipmap/ic_launcher"        //ссыдка на картинку
        android:fitsSystemWindows="true"//тоже центрирует картинку и обрезает чтоб занимало все нужное пространство
        app:tint="@color/purple_200" />//смена цвеета картинка,для векторной картинки
        />

  <TextView   //настройка элементов(текст)
    android:visability="gone"   //убрать элемент с экрана
    android:textStyle="bold"           =====стиль текста
    android:lines="1"//количество строк
    android:textAlignment="textEnd"//текст вконце строки
    android:fontFamily="@font/nazvanie.tff" указываем скаченный стиль текста
    android:drawableLeft="@mipmap/ic_launcher"  =====указать картинку рядом с текстом
    android:foreground="?android:attr/selectableItemBackground"// добавляет анимацию нажатия
   />

    <WebView/>//типа браузера у нас в макете,можно передать в нее урл и он его откроет
//кард вью
            //для ресайкл вью лучше брать (класс контейнер)DataClass[] dc = new DataClass[];//в котором будут данные всех вьюх из кард вью
       <androidx.cardview.widget.CardView //карточка ,внутри нее только 1 лэйаут может быть ,в лейауте вставляем все,имидж вью, текст вью, едит текс и тд
            app:cardCornerRadius="10dp"//скругление углов
            app:cardElevation="10dp"//приподнимаем
            app:cardBackgroundColor="@color/cardview_shadow_start_color"//цвет фона в кард вью(обычный бэкграунд работать не будет)/>

       <fragment
        android:name="com.geek.infoandroid.Fragments5.Fragment1"/>  //какой фрагмент отображаем



//Material Design
        <com.google.android.material.textfield.TextInputLayout//лейаут для эдиттекста материал дезайн(обязательная) только для едит текстов используется
        app:errorEnabled="true">//включить отслеживание ошибок при вводе в едит тексте

            <com.google.android.material.textfield.TextInputEditText//сам эдит текст материал дезайна
            android:inputType="textPersonName"/>//тип вводимых данных

         </com.google.android.material.textfield.TextInputLayout>

    //кнопка материал батн
    <com.google.android.material.button.MaterialButton" id и тд/>

    //тулбар из материал дезайна с фишками и тд
    <androidx.coordinatorlayout.widget.CoordinatorLayout>//неста скролл вью тоже сюда пихается(ФРЕЙМ ЛЕЙАУТ НА СТЕРОИДАХ)
        <com.google.android.material.appbar.AppBarLayout //апп бар лейаут для хранения тулбара и осуществления в нем функций
        android:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar">

        <com.google.android.material.appbar.CollapsingToolbarLayout   // это тулбар в развернутом состоянии
            android:fitsSystemWindows="true"    //заполнить полностью весь экран в котором он находится
            app:expandedTitleMarginEnd="64dp"// отступ справа у заголовка тулбара = 64дп  ( в развернутом состоянии)
            и app:expandedTitleMarginStart="48dp" //отступ слева 48 дп  (в развернутом состояни)
            app:contentScrim="?attr/colorPrimary"  //цвет нашего контента когда мы его свернули( тулбар)
            app:layout_scrollFlags="scroll|exitUntilCollapsed"> //будет работать коллапсинг тулбар на скролл и будет выезжать когда скролл текста не идет уже(когда уперсы в потолок)

            <ImageView    //контент нашего тулбара в развернутом состоянии
                android:fitsSystemWindows="true"???
                android:scaleType="centerCrop"//вроде это обрезание краев картинки
                app:layout_collapseMode="parallax" />//эввект скрывания картинки(параллакс)

            <androidx.appcompat.widget.Toolbar              //стандартный тулбар
                app:layout_collapseMode="pin"             //как будет тулбар в свернутом виде(пин в стандартную полоску)
                app:popupTheme="@style/ThemeOverlay.AppCompat.Light"/>             //тема тулбара( вроде в свернутом виде)

        </com.google.android.material.appbar.CollapsingToolbarLayout>
    </com.google.android.material.appbar.AppBarLayout>
    </androidx.coordinatorlayout.widget.CoordinatorLayout>//неста скролл вью тоже сюда пихается
//добавляем тулбар лэйаут и тулбар
<com.google.android.material.appbar.AppBarLayout//лэйаут для нашего тулбара
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <androidx.appcompat.widget.Toolbar//сам тулбар
        <item name="android:background">#757575//цвет тулбара
        <item name="titleTextColor">@android:color/holo_red_dark//цвет текста тулбара
            app:popupTheme="@style/AppTheme2">//тема тулбара

            <ImageView// положили картинку внутри тулбара
                android:src="@mipmap/ic_launcher_round" />

        </androidx.appcompat.widget.Toolbar>

    </com.google.android.material.appbar.AppBarLayout>

        <androidx.recyclerview.widget.RecyclerView //ресайкл вью ,список
        app:layout_behavior="@string/appbar_scrolling_view_behavior"/>//показывает что он должен быть под тулбаром

//NestedScrollView
    <androidx.core.widget.NestedScrollView
        app:layout_behavior="@string/appbar_scrolling_view_behavior">//этой строкой задаем что когда мы скролим контент в конце начинает скролиться тулбар( это строка системная,не наша)
            <include layout="@layout/include_layout" />инклуд вставляет в неста скрол вью все содержимое указанного монитора(можно просто так добавить
             //внутри неста скрол вью устанавливается контент
    </androidx.core.widget.NestedScrollView>


ресурсы
//массивы
    <string-array name="array">
        <item>предмет 0 и тд</item>
    </string-array>

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


//отключаем тулбар и отключаем название аппа на тулбаре
    <style name="AppTheme.NoActionBar">
        <item name="windowActionBar">false</item>
        <item name="windowNoTitle">true</item>
    </style>

drawable
 //decorator суется в ресурсы/дровабл/decorator_item228
 <shape xmlns:android="http://schemas.android.com/apk/res/android"//какая-то фигура
    android:shape="rectangle">//прямоугольник
    <size android:height="10dp"/>//высота фигуры 10 дп (ширина по умолчанию матч парент)
    <solid android:color="@color/design_default_color_primary_dark"/>//цвет бэкграунда
</shape>
//шэйпы вставляются по названию хмл файла через background=@drawable/shape_name
<shape
    android:shape="rectangle"
    xmlns:android="http://schemas.android.com/apk/res/android">

    <corners android:radius="10dp"/>//закругление всех углов
    <corners android:bottomLeftRadius="4dp"/>//закругление одного угла
    <stroke android:color="@color/black" android:dashGap="5dp"/>//цвет обводки и чтоб она была пунктиром

</shape>

 */
}
