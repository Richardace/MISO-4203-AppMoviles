package com.moviles.vinilos.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.moviles.vinilos.models.BandModel

@Database(entities = [BandModel::class], version = 1, exportSchema = false)
abstract class VinylsRoomDatabase : RoomDatabase() {
    abstract fun bandsDao(): BandsDao

    companion object {
        @Volatile
        private var INSTANCE: VinylsRoomDatabase? = null

        fun getDatabase(context: Context): VinylsRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    VinylsRoomDatabase::class.java,
                    "vinyls_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}