package com.moviles.vinilos.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bands_table")
data class BandModel(
    @PrimaryKey val id: Int,
    val name: String,
    val image: String,
    val description: String,
    val creationDate: String
)
