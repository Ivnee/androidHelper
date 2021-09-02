package com.geek.infoandroid.Kotlin.materialDesign

import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.geek.infoandroid.R
import com.google.android.material.bottomsheet.BottomSheetBehavior

class BottomSheetBehavior {

    class MyFragment: Fragment(R.layout.fragment1) {//фрагмент в который мы добавим снизу выдвижной бехевьер
        lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>//объявляем наш боттом щит бихевьер

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            setBottomSheetBehavior(view.findViewById(R.id.bottom_sheet_container))//по сути здесь вся логика того как показать боттои щит

            bottomSheetBehavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback(){//как сделать обработку действий боттом щита
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    when(newState){
                        BottomSheetBehavior.STATE_DRAGGING -> print("dragging")
                        BottomSheetBehavior.STATE_COLLAPSED ->print("dragging")
                        BottomSheetBehavior.STATE_EXPANDED ->print("dragging")
                        BottomSheetBehavior.STATE_HALF_EXPANDED ->print("dragging")
                        BottomSheetBehavior.STATE_HIDDEN ->print("dragging")
                        BottomSheetBehavior.STATE_SETTLING ->print("dragging")
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                    TODO("Not yet implemented")
                }
            })
        }

        private fun setBottomSheetBehavior(bottomSheet: ConstraintLayout) {
            bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
            bottomSheetBehavior.state= BottomSheetBehavior.STATE_HALF_EXPANDED//состояние бихевьера (указали чтоб вылез наполовину )
        }
    }

/*
ВСЕ ЭТО ДОЛЖНО БЫТЬ В КООРДИНАТОР ЛЭЙАУТЕ!!!!

<androidx.constraintlayout.widget.ConstraintLayout
    android:id="@+id/bottom_sheet_container"
    android:background="#f6f6fa"
    app:behavior_hideable="true"
    app:behavior_peekHeight="50dp"//указывает высоду боттом щит фрагмента
    android:visibility="gone"
    app:layout_behavior="com.google.android.material.bottomsheet.BottomSheetBehavior">

    <View//типа полосочки за которую тянуть
        android:id="@+id/bottom_sheet_line"
        android:layout_width="25dp"
        android:layout_height="1dp"
        android:layout_marginTop="5dp"
        android:background="#663b4351"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>




    * */
}