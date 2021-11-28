package com.geek.infoandroid.android.LayoutsAndOtladka;

public class SimpleAboutLayouts {
    /*
            FrameLayout — базовый контейнер для многих макетов, создан для хранения одного элемента.GRAVITY(располагает элементы друг над другом)
            LinearLayout — элементы в этом макете располагаются последовательно, по горизонтали или по вертикали.ORIENTATION HORIZONTAL ILI VERTICAL и WEIGHT(кол во мета)
            TableLayout — элементы располагаются в виде таблицы.
            CoordinatorLayout — элементы взаимодействуют в этом макете.(для открытия тулбара прокруткой и других интересных эффектов)(ФРЕЙМ ЛЕЙАУТ НА СТЕРОИДАХ)
            ConstraintLayout — элементы располагаются друг относительно друга, учитывая края экрана.
            ScrollView  - можно листать элементы которые не помещаются полностью
            HorizontalScrollView - листать элементы по горизонтали
            appBarLayout - лейаут который содержит в себе тулбар и помогает из него прокручивать элементы(из материал дизайна) ,для добавления сендвича в сайд меню и тд
            DrawerLayout - для создания навигационных вьюх ,по сути это все что в сайд меню ,все в этом лейауте(сетится в AppBarConfiguration.Build)
            CardView - для ресайкл вью как элемент( в него вставляются куча элементов любых)

app:layout_constraintTop_toTopOf=”parent” — привязка верхнего края текущего элемента к верхнему краю указанного элемента. В качестве края могут использоваться следующие: Start, End, Left, Right, Top, Bottom, Baseline (линия текста внутри элемента). В качестве параметра указывается идентификатор элемента или parent — родительский контейнер. В данном случае привязан верхний край элемента к верхнему краю родительского контейнера.
app:layout_constraintLeft_toRightOf="@+id/text1" — здесь привязан левый край одного элемента к правому краю другого. Можно привязывать однородные края друг к другу, но, к примеру, левый край одного элемента нельзя привязать к верхнему краю другого.
android:layout_marginTop="20dp" — если при привязке верхнего края указан этот параметр, то делаем соответствующий отступ между элементами. Этот же механизм работает и при других привязках.
app:layout_goneMarginLeft="20dp" — если надо привязаться к элементу в статусе gone (элемент не отображается), чтобы текущий элемент съехал на место привязанного, используйте этот параметр.
app:layout_constraintBaseline_toBaselineOf="@id/text5" — привязка по базовым линиям, то есть по горизонтальным линиям самого текста.
app:layout_constraintEnd_toEndOf="parent" app:layout_constraintStart_toStartOf="parent" — если одновременно привязаться и к началу и к концу (начало в европейских языках — левый край, в арабских — правый) другого одного элемента (можно работать с левым и правым, верхним и нижним), то мы получим центрирование текущего элемента.
app:layout_constraintHorizontal_bias="0.169" — если есть потребность сдвинуть элемент относительно центра, используйте этот параметр, где значение 0.5 — центр, от 0 до 0.5 — сдвиг влево, от 0.5 до 1 — сдвиг вправо.
app:layout_constraintCircle="@id/text8" app:layout_constraintCircleRadius="100dp"  app:layout_constraintCircleAngle="135" — задание полярных координат относительно элемента. Работает начиная с  1.1 версии библиотеки constraint. Первый параметр — относительно  какого элемента, второй параметр — какое будет расстояние между элементами, последний параметр — на какой угол по часовой стрелке в градусах.
app:layout_constraintHorizontal_chainStyle="packed" — если элементы привязаны друг относительно друга, например, правый край первого элемента привязан к левому краю второго, а левый край второго элемента — к правому краю первого, они называются цепочкой. Параметр layout_constraintHorizontal_chainStyle задает управление такой цепочкой.  Значение packed — элементы будут прижиматься друг к другу, spread — будут раздвигаться на равные расстояния, spread_inside — крайние элементы прижмутся к краям, расстояние между остальными рассредоточится. Такой параметр надо указывать у первого элемента из цепочки. Кроме того, можно задавать вес элемента в цепочке параметром layout_constraintHorizontal_weig: чем больше вес, тем больше размер. У packed цепочки можно указывать параметр bias

         */

    int a = 1;
    /*
    FrameLauout //слева направо сверху вниз и каждый след элемен ставится поверх другого
    android:layout_gravity="end" //прелипить к како-то стороне экрана(end к правой)(center,start,bottom)center_vertical|end=по верьикали центр и право
        android:layout_marginEnd="20dp"//отступ справа 20 пикс(все вместе двигает)
        android:layout_marginTop="20dp"//отступ сверху 20
        android:paddingEnd="20dp"//двигает элемент внутри контейнера от края(правый)
    */
    int b = 2;
    /*
    LinearLayout //либо горизонтально либо вертикальнол
    android:orientation="horizontal"//отоброжает все горизонтально

   android:width="0dp"?/ширина (кнопки например)
   android:layout_weight="2"//вес кнопки2/3
    android:layout_weight="1"//1/3
*/
    int c = 3;/*
    TableLayout// расположение элементов в виде таблицы
    android:orientation="horizontal"//данные по горизонтали
    android:layout_columnSpan="3"//количество колонок будет 3
    android:gravity="center"//двигает контент(внутри контейнера) в центр
    android:layout_gravity="center" //двигает в центр все вместе

    <TableRow   //ставится внутри тейбл лейаута(создает строку
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:gravity="center">//ставить все по центру
    </TableRow>
    */

    int d = 4;/*
    GridLayout //
    android:columnCount="4"// 4 столба всего,когда больше четырех элементов ,переносит на следующую строчку
    android:orientation="horizontal">
        <Button
        android:layout_column="3"// эта кнопка должа быть в колонке 4(индекс 3)/>
        */
    int e = 5;/*
    RelativeLayout//реглировка элементов относительно друг друга по айди
            <Button
    android:id="@+id/btn5"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="@string/_5"
    android:layout_centerInParent="true"/>//поставить в центре
    <Button
    android:id="@+id/btn4"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_toStartOf="@+id/btn5"//слева от кнопки 5
    android:layout_alignTop="@id/btn5"// выровнять верх кнопки по кнопке номер 5
    android:text="@string/_4"
        android:layout_alignParentEnd="true"//расположить в правой части родителя(в чем лежит эта кнопка(лэйоут))

        android:layout_centerInParent="true"//расположить элемент по центру лейаута
            />
*/
    int f = 6;/*
    ConstraintLayout//универсальный лейаут(удобно работать через графический редактор)
    */
    int g = 7;
    //ScrollView// прокручивавет текст
    int h = 8;
    //<HorizontalScrollView
    int i = 9;/*
    <androidx.cardview.widget.CardView  //вид лэйаута кард вью(вставляются элементы внутри)
    app:cardCornerRadius="8dp"//закруглить карточку
    app:cardElevation="8dp"//приподнять карточку
            >*/
}
