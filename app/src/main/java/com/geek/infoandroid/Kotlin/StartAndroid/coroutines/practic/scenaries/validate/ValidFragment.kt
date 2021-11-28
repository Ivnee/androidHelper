package com.geek.infoandroid.Kotlin.StartAndroid.coroutines.practic.scenaries.validate

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.geek.infoandroid.R
import com.geek.infoandroid.databinding.Fragment1Binding

class ValidFragment:Fragment(R.layout.fragment1) {

    val model :ValidViewModel by viewModels()
    lateinit var viewBinding: Fragment1Binding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
                viewBinding = Fragment1Binding.bind(view)
        viewBinding.editText.addTextChangedListener{ model.setName(it.toString()) }//вызываем методы вью модели
        viewBinding.editText2.addTextChangedListener { model.setAge(it.toString()) }
        initLiveData()
    }

    private fun initLiveData() {
        model.dataIsValid.observe(viewLifecycleOwner){//подписываемся на лафй дату,и следим на валидностью
            viewBinding.btn.visibility = if(it)View.VISIBLE else View.GONE
        }
    }
}