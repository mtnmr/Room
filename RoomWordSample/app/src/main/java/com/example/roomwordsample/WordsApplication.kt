package com.example.roomwordsample

import android.app.Application
import com.example.roomwordsample.reposistory.WordRepository
import com.example.roomwordsample.room.WordRoomDatabase

class WordsApplication:Application() {
    val database by lazy { WordRoomDatabase.getDatabase(this) }
    val repository by lazy { WordRepository(database.wordDao()) }
}