package com.example.testcode.hilt

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import dagger.Component
import dagger.Subcomponent
import javax.inject.Inject

//Для апп
class App : HiltApp() {
    @Inject
    lateinit var databaseHelper: DatabaseHelper//хилт апп заинжектил наш апп в хилт компонент и теперь предоставит нам дб хелпер
//теперь мы в классе апп имеет готовый объект
}

@Component
interface HiltAppComponent {
    //компонент хилта который инжектит объекты в наш апп класс
    fun injectApp(app: App)//сюда подставим апп
    fun getActivityComponent(): HiltActivityComponent//этим методом получаем наш компонент для активити
}

open class HiltApp : Application() {
    //класс,который создает хилт как оболочку для App
    val appComponent = DaggerHiltAppComponent.create()//в ней он создает апп компонент хилта и

    override fun onCreate() {
        super.onCreate()
        appComponent.injectApp(this as App)//инжектит все в наш апп(компонент проверит есть у Апп объекты, помеченные inject и поместит туда их)
    }

}

class DatabaseHelper @Inject constructor() {}//класс,который нужно будет предоставлять

//Для Активити
@Subcomponent//сабкомпонент хилта,который будет инжектить все активити(поэтому название Активити компонент)
interface HiltActivityComponent {
    fun injectOrderActivity(activity: OrderActivity)
    fun injectUserActivity(activity: UserActivity)

    fun getFragmentComponent(): HiltFragmentComponent//метод получения компонента с флагментами
}

open class HiltOrderActivity : AppCompatActivity() {
    //хилт активити ,создается хилтом поверх ордер активити
    lateinit var activityComponent: HiltActivityComponent//здесь объявляем сабкомпонент хилта для активити
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent = (application as App).appComponent.getActivityComponent()//инициализируем
        activityComponent.injectOrderActivity(this as OrderActivity)//и инжектим нашу активити
    }
}

class OrderActivity : HiltOrderActivity() {
    //наследуемся от оболочки хилта,в которой получили активити компонент
    @Inject
    lateinit var repository: OrderRepository//инжектим репозиторий теперь сразу,а все остальное под капотом сделает хилт
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //в он креейт уже можем пользоатьс ремозиторием
    }
}

class OrderRepository @Inject constructor() {}//класс для проверки инжекта
//активити2
class UserActivity: HiltUserActivity(){
    @Inject
    lateinit var userRepository: OrderRepository//инжектится под копотом
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userRepository.toString()
    }
}
open class HiltUserActivity: AppCompatActivity() {//хилт класс обертка для активити в котором спрятан весь даггер код
    lateinit var activityComponent: HiltActivityComponent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent = (application as App).appComponent.getActivityComponent()
        activityComponent.injectUserActivity(this as UserActivity)
    }
}
class UserRepository @Inject constructor() {}

//Fragment
class OrderFragment : HiltOrderFragment(){
    @Inject
    lateinit var orderRepository: OrderRepository//сразу можем инжектить репозиторий и он будет доступен в он креейт.даже раньше ,в он аттач
}
@Subcomponent//этот компонент будет дочкой активити компонента,там и будет гет метод для его получения
interface HiltFragmentComponent {//это сабкомпонент хилта ,в который так же будут инжектиться все фрагменты
    fun injectOrderFragment(fragment: OrderFragment)
}
open class HiltOrderFragment: Fragment() {//хилт обертка для фрагмента ,в которой будет инициализироваться сабкомпонент и инжектиться наш фрагмент под копотом
    lateinit var fragmentComponent: HiltFragmentComponent//инжект под копотом
    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentComponent = (context as OrderActivity).activityComponent.getFragmentComponent()//запрашиваем у активити компонента фрагмент копмонент
        fragmentComponent.injectOrderFragment(this as OrderFragment)//а к фрагмент компоненту инжектим сам фрагмент
    }

}