package com.ramirez.rickandmorty.data.remote.network

import com.ramirez.rickandmorty.data.remote.response.CharacterResponse
import kotlinx.coroutines.flow.Flow

interface RemoteCharacterDataSource {

    fun getCharacters(): Flow<CharacterResponse>
}