package com.geek.infoandroid.Kotlin.les2

class   ViewBinding {/*
//Fragment
    buildFeatures{viewBinding true}подключение вью биндинка,вставляется в гредл модуль
     private var viewBinding: MainFragmentBinding? = null
     //в методе onViewCreated
     viewBinding = MainFragmentBinding.bind(view)

//null safe
         private var _binding:FragmentMainBinding? = null
         private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentMainBinding.bind(view)
    }
    override fun onDestroyView() {
        _binding = null
    }


//Activity
private lateinit var binding: ResultProfileBinding
@Override
fun onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    binding = ResultProfileBinding.inflate(layoutInflater)
    setContentView(binding.root)
}

    */
}