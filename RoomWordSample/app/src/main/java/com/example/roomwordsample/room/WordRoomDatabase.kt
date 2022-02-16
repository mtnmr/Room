package com.example.roomwordsample.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class WordRoomDatabase : RoomDatabase() {
    abstract fun wordDao():WordDao

    companion object{
        //データベースのインスタンスが複数同時に開かれるのを防ぐため、シングルトン定義

        @Volatile
        private var INSTANCE : WordRoomDatabase? = null

        //最初にアクセスされたらデータベースを作成
        //アプリ起動のたびにデータをリセットするためにRoomDatabase.Callbackを実装
        //Roomの操作はコルーチンで行うため引数に追加（WordsApplicationにも）
        fun getDatabase(context:Context, scope:CoroutineScope):WordRoomDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordRoomDatabase::class.java,
                    "word_database")
                    .addCallback(WordDatabaseCallback(scope))
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }

    ////アプリ起動のたびにデータをリセットするためにRoomDatabase.Callbackを実装
    private class WordDatabaseCallback(private val scope: CoroutineScope)
        :RoomDatabase.Callback(){

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let{ database ->
                scope.launch {
                    val wordDao = database.wordDao()
                    wordDao.deleteAll()

                    //sample wordを追加しておく
                    var word = Word("Hello")
                    wordDao.insert(word)
                    word = Word("World")
                    wordDao.insert(word)
                }
            }
        }
    }
}