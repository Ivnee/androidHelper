package com.geek.infoandroid.Kotlin.StartAndroid.coroutines.teory

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.geek.infoandroid.R
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext

class Scope : AppCompatActivity(R.layout.activity_main) {

    private var formatter = SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault())

    lateinit var job2: Job
    val scope =
        CoroutineScope(Job())//самый изи скоуп корутины(для одного скопа можно создать несколько корутин)
    lateinit var job: Job//джоб корутины

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        scope.launch {//запускаем первый скоуп с кодом внутри
            print("1 coroutine")//какой-то метод суспенд
            this.launch { print("2 coroutine") }//(дочерний скоуп)this ссылается на еще 1 скоп(который сам нагенерил) внутри нашего скопа (можно и без this)
        }
        job = scope.launch {
            var x = 0
            while (x < 5 && isActive) {//проверяет статус джоба(выполняется корутина или нет)
                TimeUnit.MILLISECONDS.sleep(1000)//делает паузу в корутине
            }
            repeat(4) { print("повторяет 4 раза это действие") }
        }
        job.cancel()//отключить корутину(isActive = false)

    }

    private fun onRun() {
        scope.launch {
            job = launch {//вложенная корутина(дочерняя)
                var x = 0
                while (x < 5 && isActive) {//проверяет статус джоба(выполняется корутина или нет)
                    TimeUnit.MILLISECONDS.sleep(1000)//делает паузу в корутине
                }
            }
            job.join()//приостановить код пока корутина не завершит свою работу(в данном случае ожидает завершения чаилд корутины)приостановит выполнение родительской корутины,но не заблокирует ее поток
        }

        job.cancel()//отключить корутину(isActive = false)
    }

    private fun lazyRun() {
        job2 =
            scope.launch(start = CoroutineStart.LAZY) {//отложенный запуск корутины,можно передать ран в другой метод/класс
                TimeUnit.MILLISECONDS.sleep(1000)//код корутины
            }
        job2.start()//запустили корутину после
    }

    private fun asyncAwaitCoroutine() {
        //если запустить асинк в lazy  то .await запустит ее
        scope.launch {
            val deffered = async {//аналог launch , но возвращает результат корутины
                TimeUnit.MICROSECONDS.sleep(1000)
                "async result"//результат асинка

            }
            val result =
                deffered.await()//(метод для получения результата(подписываемся на ожидание результата))эвейт приостановит родительскую корутину ,пока не выполнится дочерняя
        }
    }


    private fun coroutineContext() {
        val coroutineContext =
            Job() + Dispatchers.Default//создание контекста для корутин вместе с диспатчером
        //можно чисто диспатчер или джоб передать,все это создаст контекст корутины,если джоба не будет в контексте он будет создан сам
        val newScope = CoroutineScope(coroutineContext)

        //добавляем наш дата класс к контекст корутин(cоздается дата класс в самом низу)
        val userData = UserData(
            1,
            "name1",
            0
        )//создаем какой-то дата класс(он должен имплементить AbstractCoroutineContextElement())
        val scope =
            CoroutineScope(Job() + Dispatchers.Default + userData)//создаем контекст с нашими данными
        val getData = coroutineContext[UserData]//а достать из контекста можно так


//вложенный корутин
        val scope2 =
            CoroutineScope(Job() + Dispatchers.Main)//деспатчер передастся всем вложенным корутинам, джоб всегда создается новый
        scope2.launch {
            launch(Dispatchers.Default) {//если не указать нужный деспатчер он передастся от родителя или создастся дефолтный
                launch {//здесь будет деспатер из корутины сверху(передается по цепочке)
                }
            }
        }

    }

    private fun dispatchers() {
        val scope =
            CoroutineScope(Dispatchers.Default)//для вычестительных операций ,создает количество потоков равное количеству ядер
        val scopeIO =
            CoroutineScope(Dispatchers.IO)//для запросов в сеть ,чтения с диска и тд,количество потоков =64 или количеству ядер,если их > 64
        val scopeExecutor = CoroutineScope(
            Executors.newSingleThreadExecutor().asCoroutineDispatcher()
        )//только 1 поток,в виде диспатчера можно делать свой кастомный экзекютор
        val scopeMain =
            CoroutineScope(Dispatchers.Main)//для выполнения в мейн поток,если нужно сразу засетить данные во вьюху(не блокирует поток,но ждет получения данных для сета в вьюху)
        val scopeUnconfined =
            CoroutineScope(Dispatchers.Unconfined)//выполняет все в одном потоке,не перескакивая по ним(в мэйн или в запущенном в ручнуть Thread())

    }

    private fun ExceptionsScope() {
        val handler =
            CoroutineExceptionHandler { context, exception ->//ловим ошибку и в теле показываем что с ней делать
                println("handled $exception")//эксепшн будет обработан ,но все равно поотменяет все корутины на своем пути
            }
        scope.launch(handler) {//запускаем корутину с обработчиком ошибок
            Integer.parseInt("a")//когда ловится эта ошибка ,меняется isActive = false
        }

        // как создать скоп со всем сразу
        val scope = CoroutineScope(Job() + Dispatchers.Default + handler)
        val scopeSupervisor =
            CoroutineScope(SupervisorJob() + Dispatchers.Default + handler)//supervisorJob()не отменяет всех своих ланчей ,если в одном из них отловил исключение(но отменяет для всех родителей и их вложенных чайлдов)
        scope.launch {
            launch(SupervisorJob()) { //так нельзя,это нарушит принцип связей между корутинами,супервисоср джоб вставляется только в скоуп
            }
            launch(SupervisorJob(coroutineContext[Job])) {}//костыль который сработает для остановки эксепшена(но лучше SupervisorJob())

        }

    }

    private fun coroutineScopeExc() {
        scope.launch(CoroutineName("1")) {
            //корутинаСкоуп оборачиваем в трайкеч чтоб ошибка не ушла наверх
            try {//корутина скоуп может вернуть результат своей работы
                val result =
                    coroutineScope {//ошибка останется здесь и не отменит всю корутину,и ее мы отловим с помощью трай кэч,потому как корутин скоп ее не передает родителям выше а просто кидает в наш код
                        launch(CoroutineName("1_1")) {
                        }

                        launch(CoroutineName("1_2")) {
                            // exception
                        }
                    }
            } catch (e: Exception) {
            }
            val handl = CoroutineExceptionHandler { context, exception ->
                print(exception)
            }
            supervisorScope {//обрабатывает ошибку установленным хендлером,если его нет,то будет крэш,который не поймать трай кечем
                launch(handl) { }
            }
            scope.launch {
                withContext(Dispatchers.Main) {//чаще всего используется для смены диспетчера чтоб поменять поток,а так для заменя элементов контекста
                    runBlocking {//используется для тестов , которая блокирует текущий поток, пока не завершит свою работу (и пока не дождется завершения дочерних корутин)
                    }
                }
            }
        }
    }

    private fun coroutineChannel() {
        val channel =
            Channel<Int>(2)//отправляет данные внутри корутин(2 это макс количество элементов ,которые ждут ресива,по умолчанию он 1)//когда он заполняется ,сенды снова ждут ,даже после закрытия канала, ресив сможет получить данные из буфера
        val channel2 =
            Channel<Int>(Channel.Factory.CONFLATED)//хранится одно значение в буфере, каждый сенд не ждет получается ,а просто перезаписывает значение
        val channelUnlimited =
            Channel<Int>(Channel.Factory.UNLIMITED)//ограничен только памятью устройства
        val channelBuffered = Channel<Int>(Channel.Factory.BUFFERED)//64 элемента по умолчанию
        val broadcastChannel =
            BroadcastChannel<Int>(1)//(капасити необходим)в этом канале все получаатели получат все данные(в обычном данные распределяются равномерно между получателями)
        val channelReceiverForBroadcast =
            broadcastChannel.openSubscription()//создаем канал для ресива броадкаст ченала
        scope.launch {
            launch {
                broadcastChannel.send(1)//бродкаст отправлчет данные
                channel.send(5)//отправить элемент 5
                channel.send(6)//на каждый сенд должен быть ресив и на каждый ресив должен быть сенд
                channel.send(7)//если сенд больше ресивов ,то поток приостановится .сенд будет ждать получаетля и на оборот
                channel.close()//закрыть канал
                channel.cancel()//тоже ссамое что и клоуз но еще отчищает буфер(нельзя достать данные из буфера после канцела)
            }
            launch {
                var i = channel.receive()
                channelReceiverForBroadcast.receive()//получаем данные из бродкса ченнела
                i = channel.receive()
                i = channel.receive()
                i = channel.receive()
                i = channel.receive()
                //или лучше запустить получение элементов пока не закроется канал
                for (element in channel) {
                    //здесь будут ловиться сенды до тех пор пока канал не закроется с помощью close
                    print(element)
                }
                channel.consumeEach { print(it) }//тоже самое как цикл,получает все данные с сендов канала
                channel.isClosedForReceive//закрыли канал или нет?на стороне получателя метод
            }
            launch {//парент ланч
                coroutineContext[Job]?.invokeOnCompletion {//когда все корутины внутри закончат свою работу , канал закроется(когда все сенды отправятся)и соответственное закроется цикл for Для получения данных (этот цикл выше)
                    channel.close()
                }

                launch {
                    // channel.send(...)


                    launch {
                        // channel.send(...)
                    }
                    ////
                    channel.consumeEach {//оборачивает в цик и в трай кеч/если ошибка вылетел в обычном цикле ,то канал не отменится
                    }

                }
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }

    private fun log(text: String) {
        Log.d("TAG", "${formatter.format(Date())} $text [${Thread.currentThread().name}]")

    }

    suspend fun myCancellableSuspendFunction(): String {//отменяемая суспенд функция ,которая при вызове cancel прокидывает cancellation ошибку
        return suspendCancellableCoroutine { continuation ->
            continuation.invokeOnCancellation {
                //Чтобы прервать работу suspend функции в случае отмены вызвавшей ее корутины, нам доступен данный коллбэк
            }
        }
    }
}




data class UserData(
    val id: Long,
    val name: String,
    val age: Int
) : AbstractCoroutineContextElement(UserData) {
    companion object Key : CoroutineContext.Key<UserData>
}
