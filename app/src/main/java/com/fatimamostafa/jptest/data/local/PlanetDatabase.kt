package com.fatimamostafa.jptest.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [PlanetEntity::class],
    version = 2,
)

abstract class PlanetDatabase : RoomDatabase() {
    abstract val dao: PlanetDao
}