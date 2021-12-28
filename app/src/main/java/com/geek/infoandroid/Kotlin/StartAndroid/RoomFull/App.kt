package com.example.testroom

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.testroom.App.Companion.dao

object App: Application() {
    val database = Room
        .databaseBuilder(applicationContext,AppDataBase::class.java,"db")
        .fallbackToDestructiveMigration()//при изменении структуры таблицы ,просто пересоздаст бд и все данные пропадут,зато не нужна миграция
        .allowMainThreadQueries()//разрешит работать в мэйн потоке(только для тестов)
        .fall
        .addMigrations(AppDataBase.MIGRATION_1_2)//добавляем изменене в таблице
        .build()





//////////////////
class App : Application() {
    lateinit var db:DaoDao
    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(this,NoteDb::class.java,"note.db").build

    }
}

    val Context.app: App
        get() = applicationContext as App
}