package com.moviles.vinilos.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.moviles.vinilos.models.BandModel

@Dao
interface BandsDao {
    @Query("SELECT * FROM bands_table")
    fun getBands():List<BandModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(band: BandModel)

    @Query("DELETE FROM bands_table")
    suspend fun deleteAll()
}