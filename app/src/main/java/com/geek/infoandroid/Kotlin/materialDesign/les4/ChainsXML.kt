package com.geek.infoandroid.Kotlin.materialDesign.les4

//ConstraintLayout
//android:animateLayoutChanges="true"//включить дефолтную анимацию при View.gone View.visible для констреинт лейаута
class ChainsXML {
/*
    <Button
    app:layout_constraintVertical_chainStyle="spread"
    app:layout_constraintHorizontal_chainStyle="spread_inside"
        spread_inside(крайние вью крепятся к краям дэйаута)
        packed(все будут по центу друг к другу()можно добавить маргинов просто)
    app:layout_constraintHorizontal_weight="1"//устанавливается для каждого элемена,указывает вес по горизонтали(ширину на 0dp)




 */
}
class Barrier{
/*
    <androidx.constraintlayout.widget.Barrier//не пропускает указанные вью за свои границы
    android:id="@+id/barrier"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:barrierDirection="end"//будет крепиться к концу указанных вьюх
    app:constraint_referenced_ids="tv1,tv2"/>//вью ,к которым прекрепится барьер (прикрепится к концу)
    *
 */

}
class group{/*
    <androidx.constraintlayout.widget.Group//чтоб сразу убирать несколько вьюх например View.gone
    android:id="@+id/group"
    app:constraint_referenced_ids="tv1,tv2"//какие вью добавили в группу
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"/>*/
}