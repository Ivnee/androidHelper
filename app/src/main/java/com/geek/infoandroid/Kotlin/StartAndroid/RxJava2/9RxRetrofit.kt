package com.example.testrxjava

import com.google.gson.annotations.SerializedName
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Func1
import rx.schedulers.Schedulers

class `9RxRetrofit` {
    interface WebApi {
        @GET("message{page}.json")
        fun message(@Path("page") page: Int): Observable<List<Message>>//метод должен возвращать обсервабл
    }

    private val retrofit = Retrofit
        .Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//метод ,который оборачивает ответ на запрос в обсервабл
        .baseUrl("https://rawgit.com/startandroid/data/master/messages/")
        .build()

    private val myWebApi = retrofit.create(WebApi::class.java)
    private val observable = myWebApi.message(1)//обсервабл с нашим запросом
    private fun apiWork() {
        observable.subscribeOn(Schedulers.io())//поток ,в котором происходит запрос и вся работа
            .observeOn(AndroidSchedulers.mainThread())//поток, в котором работают подписчики с данными
            .subscribe(object : Observer<List<Message>> {
                //вставляем получаетеля данных
                override fun onCompleted() {
                    TODO("Not yet implemented")
                }

                override fun onError(e: Throwable?) {
                    TODO("Not yet implemented")
                }

                override fun onNext(t: List<Message>?) {
                    print(t?.size)
                }
            })
//Lambda
        //лямбда вариант
        observable
            .map { Func1<List<Message>,String> { it[0].text }}
            //.map { Func1<String, Int> { string -> string.toInt()}}//лямбда мапа
            .subscribe(//здесь под капомто Action1<List<Message>> , Action1<Throwable>, Action0
                { print(it)},
                { print(it.message)},
                { print("onComplete")})

    }
    data class Message(
        @SerializedName("id")
        val id: Int,
        @SerializedName("time")
        val time: Long,
        @SerializedName("text")
        val text: String,
        @SerializedName("image")
        val image: String
    )
}

