package com.example.testingdagger2

import javax.inject.Inject

class Auto @Inject constructor(val depend1: Depend1, val depend2: Depend2) {//по сути делает new Depend1&2
    fun startDep(){
        depend1.start()
        depend2.start()
    }
}