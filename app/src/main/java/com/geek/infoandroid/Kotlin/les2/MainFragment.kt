package com.geek.infoandroid.Kotlin.les2

import android.app.Fragment
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.geek.infoandroid.R
import com.geek.infoandroid.android.LiveDataViewModel.KOTLINViewModel.MainViewModel

class MainFragment : Fragment(R.layout.fragment1) {

    companion object {
        //создаем фрагмент здесь
        fun newInstance() = MainFragment()

        //подробно создаем фрагмент аргументс
        /* companion object {
             fun newInstance(movie: Movie) =
                 MovieFragment().apply {
                     arguments = Bundle().apply {
                         putSerializable(ARG_MOVIE, movie)
                     }
                 }
         }

         override fun onCreate(savedInstanceState: Bundle?) {
             super.onCreate(savedInstanceState)
             arguments?.let {
                 movie = it.getSerializable(ARG_MOVIE) as Movie?
             }
         }*/
    }

    private lateinit var viewModel: MainViewModel//создаем вью модель

    fun shortCreateViewModel() {//как создать вью модел с экстеншенами
/*
подключаем     implementation 'androidx.fragment:fragment-ktx:1.3.4'
и делаем для включения вью модели

    private val viewModel:MainViewModel by viewModels{
        MainViewModelFactory(ResourceProvider(requireActivity().application))
    }

        }*/
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val str = "10"
        viewModel = ViewModelProvider(
            requireActivity(),
            MainViewModelFactory(str)
        ).get(MainViewModel::class.java)//создаем вью модель которой передали фэктори
    }
}

class MainViewModelFactory(val resourceProvider: String) : ViewModelProvider.Factory {
    //фектори для вью модели
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        MainViewModel(resourceProvider) as T

}