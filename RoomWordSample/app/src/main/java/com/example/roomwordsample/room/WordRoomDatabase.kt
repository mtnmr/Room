package com.example.roomwordsample.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [WordDao::class], version = 1, exportSchema = false)
abstract class WordRoomDatabase : RoomDatabase() {
    abstract fun wordDao():WordDao

    companion object{
        //データベースのインスタンスが複数同時に開かれるのを防ぐため、シングルトン定義

        @Volatile
        private var INSTANCE : WordRoomDatabase? = null

        //最初にアクセスされたらデータベースを作成
        fun getDatabase(context:Context):WordRoomDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordRoomDatabase::class.java,
                    "word_database").build()
                INSTANCE = instance
                return instance
            }
        }
    }
}