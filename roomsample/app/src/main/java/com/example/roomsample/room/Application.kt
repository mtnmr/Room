package com.example.roomsample.room

import android.app.Application
import androidx.room.Room

class Application : Application(){

    //companion objectとしてAppDatabaseのインスタンスを定義することで
    //アプリ起動中はAppDatabaseのインスタンスを共有できるようにする
    companion object{
        lateinit var database: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "app_database"
        ).fallbackToDestructiveMigration().build()

    }
}