package com.geek.infoandroid.Kotlin.libs

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.geek.infoandroid.R
import com.geek.infoandroid.databinding.Fragment1Binding
import com.geek.infoandroid.databinding.MainActivity2Binding

class viewBinding: Fragment(R.layout.fragment1) {
    private var _binding:Fragment1Binding? = null//в фрагменте надо биндинг делать нулабл,чтоб занулить в onDestroyView()
    private val binding:Fragment1Binding
        get() = binding!!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = Fragment1Binding.bind(view)//получаем биндинг
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
//биндинг в активити
class MainActivity:AppCompatActivity(){
    private lateinit var binding:MainActivity2Binding//через лейтинит можно только в активити

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = MainActivity2Binding.inflate(layoutInflater)//инициализация биндинга,передаем инфлейтер
        setContentView(binding.root )//в нашу активити сетим рут биндинга(main_activity2.xml)
        //нет необходимости занулять в onDestroy()
    }
}