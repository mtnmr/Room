package com.example.roomsample.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sample")
data class SampleEntity(
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
    @ColumnInfo(name = "description")
    val description: String
)
