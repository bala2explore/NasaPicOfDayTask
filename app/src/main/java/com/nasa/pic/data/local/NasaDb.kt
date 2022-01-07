package com.nasa.pic.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nasa.pic.data.entity.NasaEntity

@Database(
    entities = [NasaEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class NasaDb : RoomDatabase() {

    abstract fun NasaDao(): NasaDao
}