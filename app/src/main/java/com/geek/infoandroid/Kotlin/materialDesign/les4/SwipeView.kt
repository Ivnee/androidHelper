package com.geek.infoandroid.Kotlin.materialDesign.les4

import android.view.View
import androidx.cardview.widget.CardView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.behavior.SwipeDismissBehavior

class SwipeView {
//СВАЙП РАБОТАТЕТ КОГДА ВЬЮХИ В КООРДИНАТОР ЛЕЙАУТЕ

/*
    val card: CardView = view.findViewById(R.id.card)//определяем нашу вью
    val swipe : SwipeDismissBehavior<CardView?> = SwipeDismissBehavior()//создаем свайп
    swipe.setSwipeDirection(SwipeDismissBehavior.SWIPE_DIRECTION_ANY)//в какую сторону можно свайпать(any -в любую)
    swipe.listener = object : SwipeDismissBehavior.OnDismissListener{//когда выходим за границы вью (при нажатии)
    override fun onDismiss(view: View?) {
        print("мы свайпнули вьюху до конца")
        card.visibility = View.GONE//убрали нашу вью
    }

        override fun onDragStateChanged(state: Int) {}
    }
    val coordinatorParams = card.layoutParams as CoordinatorLayout.LayoutParams//достаем параметры лэйаутов
    coordinatorParams.behavior = swipe//указываем поведение вью*/
}