package com.geek.infoandroid.Kotlin.les7RetrofitOkHttpGlide.retrofitCoroutines

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import com.geek.infoandroid.R
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class CoroutineFragment : Fragment(R.layout.fragment1) {
    val mainScope = MainScope()//объявляем мэйнскоуп для запуска корутины(нужно отключать в он стоп)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {//для использования репит он лайфцайкл необходимо подключит ьбиблиотеку
                //используется для контроля жизненного цикла,сам подключает и выключает подписку на флоу

            }
        }
        //мэйнскоуп не управляет лайфцайклом,его нужно вырубать в онСтопе
        mainScope
        //лайфцайкл скоуп сам следит за жизн циклом и сам вырубится
        lifecycleScope.launch {
            //будет работать после метода он стартед
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.error.collect {
                    val error = it ?: return@collect
                    Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
                    with(viewBinding) {
                        progress.visibility = View.VISIBLE
                        temp.visibility = View.GONE
                    }
                }
            }
        }
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.loading.collect {
                    viewBinding.progress.visibility = if (it) {
                        View.VISIBLE
                    } else {
                        View.GONE
                    }
                }
            }
        }
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.weather.collect {
                    val weatherInfo = it ?: return@collect
                    with(viewBinding) {
                        city.apply {
                            visibility = View.VISIBLE
                            text = weatherInfo.cityName
                        }
                        temp.apply {
                            visibility = View.VISIBLE
                            text = weatherInfo.weather
                        }
                    }
                }
            }
        }
        //аналог репит он лайфцайкл
        lifecycleScope.launchWhenStarted {
            viewModel.loading.collect {

            }
        }
    }

    override fun onStop() {
        super.onStop()

        //вырубаем вручную скоуп если не используем лайфцайкл скоуп
        mainScope.coroutineContext.cancel()
    }
}