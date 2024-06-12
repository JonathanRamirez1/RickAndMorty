package com.ramirez.rickandmorty.domain.repositories

import com.ramirez.rickandmorty.domain.models.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    fun getAllCharacters(): Flow<List<Character>>
    suspend fun fetchAndStoreCharacters()
    suspend fun updateFavoriteStatus(id: Int, isFavorite: Boolean)
    suspend fun shouldFetchFromRemote(): Boolean
}