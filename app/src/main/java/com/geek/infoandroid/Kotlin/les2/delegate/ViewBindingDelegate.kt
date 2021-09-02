package com.example.infokotlin.A2MVVM.delegate

import android.media.audiofx.Visualizer
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty
//КАК ВЫЗВАТЬ ВЬЮ БИНДИНГ ВО ФРАГМЕНТЕ
//private val viewBinding: MainFragmentBinding by viewBinding(MainFragmentBinding::bind)
//ЭКСТЕНШЕН ФУНКЦИЯ
fun <V:ViewBinding>Fragment.viewBinding(bind: (view: View) -> V):ViewBindingDelegate<V>{
    return ViewBindingDelegate(this,bind)
}
//САМ ДЕЛЕГАТ , В КОТОРОМ МЫ ОПРЕДЕЛЯЕМ ВЬЮ БИНДИНГ
class ViewBindingDelegate<V : ViewBinding>(fragment: Fragment,private val bind: (view: View) -> V) :
    ReadOnlyProperty<Fragment, V> {

    init {
        fragment.viewLifecycleOwnerLiveData.observe(fragment){
            it.lifecycle.addObserver(object :LifecycleObserver{
                @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
                fun destroyed(){
                    viewBinding = null
                }
            })
        }
    }
    private var viewBinding :V? = null
    override fun getValue(thisRef: Fragment, property: KProperty<*>): V {
        return viewBinding ?: run{
            val view = thisRef.requireView()
            bind(view)
        }
    }
}
