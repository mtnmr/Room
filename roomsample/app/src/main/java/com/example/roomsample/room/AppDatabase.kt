package com.example.roomsample.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SampleEntity::class], version = 1)
abstract class AppDatabase:RoomDatabase() {
    abstract fun sampleDao():SampleDao
}