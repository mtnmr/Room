package com.example.roomwordsample.reposistory

import androidx.annotation.WorkerThread
import com.example.roomwordsample.room.Word
import com.example.roomwordsample.room.WordDao
import kotlinx.coroutines.flow.Flow

class WordRepository(private val wordDao: WordDao) {

    val allWords: Flow<List<Word>> = wordDao.getAlphabetizedWords()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word:Word){
        wordDao.insert(word)
    }
}