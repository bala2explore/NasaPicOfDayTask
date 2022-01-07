package com.nasa.pic.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nasa.pic.data.entity.NasaEntity

@Dao
interface NasaDao {

    @Query("SELECT * FROM nasa_pic_of_day ORDER BY date DESC")
    fun getAllPictures(): LiveData<List<NasaEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPicture(nasaEntity: NasaEntity)
}