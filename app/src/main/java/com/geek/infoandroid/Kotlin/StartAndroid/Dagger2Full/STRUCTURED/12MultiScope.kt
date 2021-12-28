package com.example.daggertest.`12MultiScope`

import android.app.Activity
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import javax.inject.Inject
import javax.inject.Scope

//есть 2 активити и для каждой нужен свой UiHelper ,как получить это в даггере
class UiHelper(activity: Activity) {}

//1)OrderActivity
@OrderScope
@Subcomponent(modules = [OrderModule::class])
interface OrderComponent {
}
@Module
class OrderModule(){
    @OrderScope//создает синглтон в компонете только с такой же аннотацией
    @Provides
    fun provideUiHelper(activity: Activity): UiHelper = UiHelper(activity)
}
//2)UserActivity тоже самое
@UserScope
@Subcomponent(modules = [UserModule::class])
interface UserComponent{}
@Module
class UserModule {
    @UserScope//так же создает UiHelper , но для другого компонента (для другой активити в перспективе)
    @Provides
    fun provideUiHelper(activity: Activity): UiHelper = UiHelper(activity)

}
//3)если мы просото inject-им нужный объект,как его создать для разных сабкомпонентов

@ActivityScope//указываем у обоих компонентов общий скоуп(специально для этого созданный)
@UserScope//у компонентов может быть несколько скоупов, а у класса который инжектим(Inject)можно указать только 1
@Subcomponent
interface UserComponent2{}

@ActivityScope//mакой же синглтон(каждый компонент создает свой экземпляр синглтона)
@UserScope
@Subcomponent
interface OrderComponent2{}

@ActivityScope//теперь оба компонента смогут создавать себе синглтоны
class UiHelper2 @Inject constructor(activity: Activity) {}//создаем объект через инжект



///////////////Аннотации
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class OrderScope
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class UserScope
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScope//общий скоуп для нескольких сабкомпонентов