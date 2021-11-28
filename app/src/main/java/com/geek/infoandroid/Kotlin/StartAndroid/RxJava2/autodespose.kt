package com.geek.infoandroid.Kotlin.StartAndroid.RxJava2

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class autodespose {

    fun Disposable.autoDispose(compositeDisposable:CompositeDisposable) {
        compositeDisposable.add(this)
    }
}