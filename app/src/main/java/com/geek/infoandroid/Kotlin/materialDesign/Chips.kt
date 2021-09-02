package com.geek.infoandroid.Kotlin.materialDesign

class Chips {
/*
    <com.google.android.material.chip.ChipGroup//если 1 листенер,который работает на все чипсы внутри него,и можно по айди устанавливать листенеры
    app:chipSpacingHorizontal="25dp"//отступы по горизонтали между чипсами
    app:singleSelection="true"//выбрать только 1 чипс ,для чипса чейс= style="@style/Widget.MaterialComponents.Chip.Choice"
    android:layout_marginTop="16dp">

        <com.google.android.material.chip.Chip//обыычный чипс
            app:rippleColor="@android:color/holo_orange_dark" //цвет при нажатии ,который расплывается
            app:chipSurfaceColor="@color/black"//цвет выбранного чипса

            app:iconStartPadding="10dp"//отступ иконки в чипе
            app:chipIcon="@drawable/ic_archive"//иконка
            android:text="Default" />

        <com.google.android.material.chip.Chip//энтри чипс который можно удалить,появляется крестик и через листенер указываем логику
            style="@style/Widget.MaterialComponents.Chip.Entry"  //setOnCloseIconClickListener {it.visability=View.GONE}
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Entry" />
            .........chip_close.setOnCloseIconClickListener {листенер на крестик у ентри чипса}

        <com.google.android.material.chip.Chip
            style="@style/Widget.MaterialComponents.Chip.Choice"//имеет 2 состояния (чек и анчек)типа вкл и выкл .setOnCheckedChangeListener()
            android:text="Choice" />

        <com.google.android.material.chip.Chip//точно такой же как и обычный чипс
            style="@style/Widget.MaterialComponents.Chip.Action"
            android:text="Action" />

        <com.google.android.material.chip.Chip//не объяснил
            style="@style/Widget.MaterialComponents.Chip.Filter"
            android:text="Filter" />

    </com.google.android.material.chip.ChipGroup>
    */
}