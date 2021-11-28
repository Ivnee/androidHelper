package com.geek.infoandroid.Kotlin.materialDesign.les4

//ConstraintLayout
//android:animateLayoutChanges="true"//включить дефолтную анимацию при View.gone View.visible для констреинт лейаута
class ChainsXML {
/*
    <Button
    app:layout_constraintVertical_chainStyle="spread"
    app:layout_constraintHorizontal_chainStyle="spread_inside"
        spread_inside(крайние вью крепятся к краям лэйаута)
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
    app:constraint_referenced_ids="tv1,tv2"//выбираем вьюхи ,к которым прекрепится барьер (прикрепится к концу)
    app:barrierDirection="end"/>//где будет линия относительно выбранных вьюх

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
class guidline{
/*
    <androidx.constraintlayout.widget.Guideline
    android:id="@+id/gl1"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:layout_constraintGuide_percent="0.2"//отодвинется от начала экрана на 20 проц
    android:orientation="vertical"//как будет расположена линия
    />
*/

}