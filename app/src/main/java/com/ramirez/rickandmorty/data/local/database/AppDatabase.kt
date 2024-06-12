package com.ramirez.rickandmorty.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ramirez.rickandmorty.data.local.dao.CharacterDao
import com.ramirez.rickandmorty.data.local.entities.CharacterEntity

@Database(entities = [CharacterEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun characterDao(): CharacterDao
}