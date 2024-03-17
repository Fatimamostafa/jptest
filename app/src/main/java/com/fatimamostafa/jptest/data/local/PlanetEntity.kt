package com.fatimamostafa.jptest.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PlanetEntity(
    @PrimaryKey
    val url: String,
    val name: String,
    val uid: String,
)
