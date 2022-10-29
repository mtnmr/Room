package com.example.roomsample.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [SampleEntity::class], version = 3, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class AppDatabase:RoomDatabase() {
    abstract fun sampleDao():SampleDao
}