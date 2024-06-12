package com.ramirez.rickandmorty.data.remote.network

import com.ramirez.rickandmorty.data.remote.response.CharacterResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteCharacterDataSourceImpl @Inject constructor(private val rickAndMortyApi: RickAndMortyApi): RemoteCharacterDataSource {

    override fun getCharacters(): Flow<CharacterResponse> = flow {
        emit(rickAndMortyApi.getCharacters())
    }
}