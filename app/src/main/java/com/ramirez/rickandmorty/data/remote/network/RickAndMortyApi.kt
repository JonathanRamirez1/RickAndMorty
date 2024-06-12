package com.ramirez.rickandmorty.data.remote.network

import com.ramirez.rickandmorty.data.remote.response.CharacterResponse
import retrofit2.http.GET

interface RickAndMortyApi {

    @GET("character")
    suspend fun getCharacters(): CharacterResponse
}