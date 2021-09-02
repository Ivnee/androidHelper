package com.geek.infoandroid.Kotlin.materialDesign

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.geek.infoandroid.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomNavigationFragment: BottomSheetDialogFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
/*// листенеры на фрагмент меню
        view.findViewById(R.id.navigation_view).setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_one -> Toast.makeText(context, "1", Toast.LENGTH_SHORT).show()
                R.id.navigation_two -> Toast.makeText(context, "2", Toast.LENGTH_SHORT).show()
            }
            true
        }*/
        BottomNavigationFragment().show(childFragmentManager,"123")//как вызывается боттом фрагмент из мэйн фрагмента
}


    /*//настройка боттом навигейшена
    backgroundTint — цвет фона. Цвет по умолчанию: colorSurface и colorPrimary для colored;
    itemTextColor/itemIconTint — цвет иконок и заголовков. Цвета по умолчанию: colorOnSurface/colorPrimary (при выбранном);
    itemHorizontalTranslationEnabled — флаг, решающий, будет ли анимация при перелистывании. По умолчанию == false.

     */

    /*это лэйаут боттом шит диалог фрагмента
    <constraintLayout>
    <com.google.android.material.navigation.NavigationView
        android:id="@+id/navigation_view"
        android:layout_gravity="bottom"   //указываем чтоб прилеплялась к низу
        app:menu="@menu/menu_bottom_navigation" //указываем меню с итемами
        />
</constraintLayout>


......menu стандартное
    <group android:checkableBehavior="none">
        <item
            android:id="@+id/navigation_one"
            android:icon="@drawable/ic_archive"
            android:title="На экран 1" />
        <item
            android:id="@+id/navigation_two"
            android:icon="@drawable/ic_send"
            android:title="На экран 2" />
    </group>
     */
}