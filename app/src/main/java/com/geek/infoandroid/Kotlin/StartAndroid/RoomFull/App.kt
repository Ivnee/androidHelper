package com.example.testroom

import android.app.Application
import androidx.room.Room
import com.example.testroom.App.Companion.dao

object App: Application() {
    val database = Room
        .databaseBuilder(applicationContext,AppDataBase::class.java,"db")
        .fallbackToDestructiveMigration()//при изменении структуры таблицы ,просто пересоздаст бд и все данные пропадут,зато не нужна миграция
        .allowMainThreadQueries()//разрешит работать в мэйн потоке(только для тестов)
        .addMigrations(AppDataBase.MIGRATION_1_2)//добавляем изменене в таблице
        .build()





//////////////////
        companion object {
            private var dao: CarDao? = null
            fun getDao() = dao ?: throw RuntimeException("db has not been created")
        }

        override fun onCreate() {
            super.onCreate()
            dao = Room.databaseBuilder(this, AppDataBase::class.java, "GitHubDataBase")
                .fallbackToDestructiveMigration()
                .build().gitHubDao()
        }
}