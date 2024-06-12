package com.ramirez.rickandmorty.data.local.datasource

import com.ramirez.rickandmorty.data.local.entities.CharacterEntity
import kotlinx.coroutines.flow.Flow

interface LocalCharacterDataSource {

    suspend fun insertAll(characters: List<CharacterEntity>)
    fun getAllCharacters(): Flow<List<CharacterEntity>>
    suspend fun updateFavoriteStatus(id: Int, isFavorite: Boolean)
}