package com.geek.infoandroid.Kotlin.KOIN

import androidx.appcompat.app.AppCompatActivity
import com.example.gitt.moxy.MainView
import com.example.testcode.features.ViewModel1
import com.geek.infoandroid.Kotlin.StartAndroid.coroutines.practic.apiRoomFull.MainViewModel
import com.geek.infoandroid.Kotlin.modules.Versions.koin
import com.geek.infoandroid.android.Level2.les4.RetrofitInfo.RetrofitModel.Weather
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject
import org.koin.android.scope.newScope
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent

class GetDepend : AppCompatActivity() {
    //ВЬЮ МОДЕЛЬ НЕ НАЗЫВАТЬ viewModel!!!
    private val model1: ViewModel1 by viewModel()//предоставить вью можель сразу с фэктори
    private val model2 by viewModel<ViewModel1>()//или так получаем вью модель

    private val weather by inject<Weather>()//получаем экземпляр класса из коина

    //scope
    //получаем
    val scope2 = getKoin().createScope("id",named("my scope"))//создать скоуп
    val scope = getKoin().getOrCreateScope("id name", named("my scope"))//получить или создать скоуп
    val createdScope = getKoin().getScope("id name")//получили скоуп по id

    ////?????////////////////////разобраться с этой фигней
    //видимо просто нужно наследовать от ScopeFragment или ActivityFragment
    //и скоуп будет работать самостоятельно
    val model: MainViewModel = scope.get()//получаем модель от нашего скоупа
    val modell: MainViewModel by currentScope.viewModel(this)//есть еще каррент скоуп которым можно получать элементы(скоуп сам будет в нужный момент создаваться и уничтожаться)
    val viewModel: MainViewModel by currentScope.inject()
//////////////////////

    override fun onDestroy() {//убиваем скоуп
        super.onDestroy()

        val checkScopeForDestroy =
            KoinJavaComponent.getKoin().getScopeOrNull("id name")//по id получаем скоуп,если он есть
        checkScopeForDestroy?.close()//убьет скоуп(можно закинуть в он дестрой)

    }



    fun work(){
//как добавить какой-нибудь класс в жизн цикл нашего скоупа

        // Создаём компонент динамически
        val a = Weather()

        // Получаем экземпляр пустого скоупа
        val myScopeInstance = getKoin().createScope("myScopeId",named("a"))

        // Помещаем компонент в скоуп
        myScopeInstance.declare(a)

        // Теперь можно получать компонент, когда он нужен
        myScopeInstance.get<Weather>()

        // Удаляем скоуп
        myScopeInstance.close()
    }



}