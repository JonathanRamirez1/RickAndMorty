package com.ramirez.rickandmorty.data.local.datasource.impl

import com.ramirez.rickandmorty.data.local.dao.CharacterDao
import com.ramirez.rickandmorty.data.local.datasource.LocalCharacterDataSource
import com.ramirez.rickandmorty.data.local.entities.CharacterEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalCharacterDataSourceImpl @Inject constructor(private val characterDao: CharacterDao): LocalCharacterDataSource {

    override suspend fun insertAll(characters: List<CharacterEntity>) {
        characterDao.insertAll(characters)
    }

    override fun getAllCharacters(): Flow<List<CharacterEntity>> {
        return characterDao.getAllCharacters()
    }

    override suspend fun updateFavoriteStatus(id: Int, isFavorite: Boolean) {
        characterDao.updateFavoriteStatus(id, isFavorite)
    }
}