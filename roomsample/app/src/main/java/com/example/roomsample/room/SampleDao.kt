package com.example.roomsample.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SampleDao {
    @Query("SELECT * FROM sample")
    fun loadAllData():List<SampleEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveData(sampleEntity: SampleEntity)
}