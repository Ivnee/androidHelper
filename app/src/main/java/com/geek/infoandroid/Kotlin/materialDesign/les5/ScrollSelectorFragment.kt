package com.geek.infoandroid.Kotlin.materialDesign.les5

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.geek.infoandroid.R
import com.geek.infoandroid.databinding.ScrollSelectorFragmentBinding

class ScrollSelectorFragment: Fragment(R.layout.scroll_selector_fragment) {
    private var isExpanded = false
    var viewBinding:ScrollSelectorFragmentBinding? = null


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = ScrollSelectorFragmentBinding.bind(view)
        val safeBinding = viewBinding ?: return
        safeBinding.scrollView.setOnScrollChangeListener { _, _, _, _, _ ->//слушатель на кручение скролл вью
            safeBinding.header.isSelected =
                safeBinding.scrollView.canScrollVertically(-1)//-1 (направление вверх),когда докрутили до самого верха ,сработает,1 cработает когда докрутим в самый низ
        }
    }/*
    <selector xmlns:android="http://schemas.android.com/apk/res/android">

        <item
        android:state_selected="true">
            <objectAnimator
                android:duration="200"//длительность анимации
                android:propertyName="elevation"//какой параметр будем менять
                android:valueTo="24dp"//значение если тру
                android:valueType="floatType"/>тип значенияя
        </item>
        <item>
        <objectAnimator
            android:duration="200"
            android:propertyName="elevation"
            android:valueTo="0dp"//значение если фолс
           android:valueType="floatType"/>
        </item>
    </selector>*/

/*
    <FrameLayout
    android:id="@+id/header"
    android:layout_width="match_parent"
    android:layout_height="52dp"
    android:background="@color/white"
    android:stateListAnimator="@animator/toolbar_elevation"//КАК УКАЗЫВАТЬ СЕЛЕКТОР В ВЬЮХАХ
    >*/
}