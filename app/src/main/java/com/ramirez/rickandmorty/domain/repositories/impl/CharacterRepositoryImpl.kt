package com.ramirez.rickandmorty.domain.repositories.impl

import com.ramirez.rickandmorty.data.local.datasource.LocalCharacterDataSource
import com.ramirez.rickandmorty.data.remote.network.RemoteCharacterDataSource
import com.ramirez.rickandmorty.di.IoDispatcher
import com.ramirez.rickandmorty.domain.models.Character
import com.ramirez.rickandmorty.domain.repositories.CharacterRepository
import com.ramirez.rickandmorty.presentation.utils.InternetCheck
import com.ramirez.rickandmorty.presentation.utils.toCharacter
import com.ramirez.rickandmorty.presentation.utils.toCharacterEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteCharacterDataSource,
    private val localDataSource: LocalCharacterDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : CharacterRepository {

    override fun getAllCharacters(): Flow<List<Character>> {
        return localDataSource.getAllCharacters().map { entities ->
            entities.map { entity -> entity.toCharacter() }
        }
    }

    override suspend fun fetchAndStoreCharacters() {
        withContext(ioDispatcher) {
            if (InternetCheck.isNetworkAvailable()) {
                remoteDataSource.getCharacters().collect { response ->
                    val entities = response.results.map { it.toCharacterEntity() }
                    localDataSource.insertAll(entities)
                }
            }
        }
    }

    override suspend fun updateFavoriteStatus(id: Int, isFavorite: Boolean) {
        withContext(ioDispatcher) {
            localDataSource.updateFavoriteStatus(id, isFavorite)
        }
    }

    override suspend fun shouldFetchFromRemote(): Boolean {
        return withContext(ioDispatcher) {
            val localData = localDataSource.getAllCharacters().first()
            localData.isEmpty() && InternetCheck.isNetworkAvailable()
        }
    }
}