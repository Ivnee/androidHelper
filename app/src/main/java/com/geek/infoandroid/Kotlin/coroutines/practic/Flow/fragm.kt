package com.geek.infoandroid.Kotlin.coroutines.practic.Flow

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.geek.infoandroid.R
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class fragm : Fragment(R.layout.fragment1) {
    val viewModel: SharedFlowMy by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenStarted {//запускаем после метода стартед и сам отпишется в методе стоп
            viewModel.myFlow.collect {//собираем данные
                print(it)
            }
        }

    }
}