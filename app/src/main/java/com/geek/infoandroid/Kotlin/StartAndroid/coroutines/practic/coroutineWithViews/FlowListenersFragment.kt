package com.geek.infoandroid.Kotlin.StartAndroid.coroutines.practic.coroutineWithViews

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.geek.infoandroid.R
import com.geek.infoandroid.databinding.Fragment1Binding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class FlowListenersFragment : Fragment(R.layout.fragment1) {
    lateinit var viewBinding1: Fragment1Binding
    val viewModel: FlowListenersWithViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding1 = Fragment1Binding.bind(view)
        viewBinding1.editText.addTextChangedListener { viewModel.search(it.toString()) }//отправляем все изменения едит текста в нашу вью модель
    }
    fun collection(){
        lifecycleScope.launch{
            viewModel.searchResultFlow.collect{
                viewBinding1.textViewForList.text = it.toString()
            }
        }
    }
}