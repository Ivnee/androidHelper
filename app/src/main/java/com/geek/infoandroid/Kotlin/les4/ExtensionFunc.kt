package com.geek.infoandroid.Kotlin.les4

import android.view.View
//расширяем класс вью
//экстеншн функция которая принимает функцию ,возвращающую тип буллин и без аргументов
inline fun View.visibleOrGone(lambda:()->Boolean):View{//тип возвращаемого значения View//пометив инлайн,эта функция просто подставляется в место вызова и не жрет лишних ресурсов и не добавляется в стэк визова
    //теперь метод visibleOrGone можно вызвать в любой View
    visibility = if(lambda()) View.VISIBLE else View.GONE
    return this
}

fun View.visibleOrGone2(isVisible:Boolean):View{//можно просто через булин сделать,в случае если нужно просто вставить тру или фолс 1 строчкой,лямбда для гибкости,можно и логику впихнуть и сразу тру поставить
    visibility = if(isVisible) View.VISIBLE else View.GONE
    return this
}
/*
КАК ЭТО ИСПОЛЬЗУЕТСЯ
viewModel.loadingLiveData.observe(viewLifecycleOwner) {
    viewBinding.progress.visibleOrGone{it}//вставляем boolean из лайфдаты в наш метод ,и теперь этот метод вернул boolean и выполнится наша экстеншин функция
    или
    viewBinding.progress.visibleOrGone{5+5=10}//здесь описали метод,который вернет тру,моожно писать что угодно в теле лямбды,главное вернуть булин тип

}*/
