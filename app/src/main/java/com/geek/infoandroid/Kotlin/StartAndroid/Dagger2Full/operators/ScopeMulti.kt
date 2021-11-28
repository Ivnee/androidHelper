package com.example.testcode.operators

import androidx.appcompat.app.AppCompatActivity
import com.example.testcode.App
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import javax.inject.Inject
import javax.inject.Scope

//activity
class OrderActivity1:AppCompatActivity(){}
class UserActivity:AppCompatActivity(){}

//Components
@ActivityScope//создается через инжект
@OrderScope1//в итоге каждый компонент будет создавать свой синглтон для UiHelper(если присвоить каждому компоненту и модулю свои скоупы)
@Subcomponent(modules = [OrderModule1::class])
interface OrderComponent1{}
@ActivityScope
@UserScope
@Subcomponent
interface UserComponent{}

//Modules
@Module
class OrderModule1{
    @OrderScope1
    @Provides
    fun provideUiHelper(activity:AppCompatActivity):UiHelper = UiHelper(activity)
}
@Module
class UserModule{
    @UserScope
    @Provides
    fun provideUiHelper(activity:AppCompatActivity):UiHelper = UiHelper(activity)
}


//Scopes
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class OrderScope1
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class UserScope
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScope//название такое, потому как это совпадает с жизненным циклом активити

//ЕСЛИ У НАС ИНЖЕКТ КЛАСС,ТО СОЗДАЕМ НЕ ЧЕРЕЗ МОДУЛИ
@ActivityScope//сюда можно только 1 скоп аннотацию,а в сабкомпоненты много
class UiHelper @Inject constructor(activity:AppCompatActivity){}