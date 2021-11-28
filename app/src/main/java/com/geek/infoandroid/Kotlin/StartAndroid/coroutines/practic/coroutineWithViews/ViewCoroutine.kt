package com.geek.infoandroid.Kotlin.StartAndroid.coroutines.practic.coroutineWithViews

import android.animation.ObjectAnimator
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.geek.infoandroid.R
import com.geek.infoandroid.databinding.Fragment1Binding
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class ViewCoroutine : Fragment(R.layout.fragment1) {
    lateinit var viewBinding: Fragment1Binding
    var listener: View.OnLayoutChangeListener? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = Fragment1Binding.bind(view)

        lifecycleScope.launch{
            viewBinding.btn.setText("12313")//изменили текст(и заодно ширину кнопку)
            //еслы вызов лога с размером кнопки будет здесь,то он выдаст старый размер,нужен листенер чтоб лог выводился после отрисовки
            Log.d("tag","old size is ${viewBinding.btn.width}")
            viewBinding.btn.awaitLayoutChange()//ЭТО СУСПЕНД ФУНКЦИЯ ,корутина ждет ее выполнения а потом приступает к остальному коду
            Log.d("tag","new size is ${viewBinding.btn.width}")
        }
    }

    suspend fun View.awaitLayoutChange() = suspendCancellableCoroutine<Unit> {cont->//суспенд метод,который ждет когда вызовется метод изменения размеров у вть
        val listener = object : View.OnLayoutChangeListener {

            override fun onLayoutChange(v: View?, left: Int, top: Int, right: Int, bottom: Int, oldLeft: Int, oldTop: Int, oldRight: Int, oldBottom: Int
            ) {
                view?.removeOnLayoutChangeListener(this)//когда вызвался метод у нашего вью после изменения размеров мы удаляем листенер
                cont.resume(Unit)//??
            }
        }

        addOnLayoutChangeListener(listener)//добавляем листенер нашей вью,который удалится,когда он перерисуется ,благодаря корутине код не продолжится,пока не закончится этот метод
        cont.invokeOnCancellation { removeOnLayoutChangeListener(listener) }//есои корутина была отменена,то тоже удаляем этот листенер
    }

//флоу EditText
val flow = callbackFlow<String> {//флоу следит за нашим текстом в едит тексте

    val textWatcher = object: TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            editable?.toString()?.let{ trySend(it)}//после изменений в тексте ,отправляем их
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }

    viewBinding.btn.addTextChangedListener(textWatcher)//вставляем в текст вью(тут btn) наш текст вотчер
    awaitClose { viewBinding.btn.removeTextChangedListener(textWatcher) }
}


    //анимация
    fun animationView(){
        lifecycleScope.launch {
            val animator = ObjectAnimator.ofFloat(viewBinding.img, View.ALPHA, 0f, 1f)
            animator.start()
           // animator.awaitEnd()//функция, которая приостановит корутину, пока анимация не завершится.
        }
    }
}
