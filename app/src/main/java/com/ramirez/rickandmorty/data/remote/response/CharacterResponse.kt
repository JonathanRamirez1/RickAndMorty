package com.ramirez.rickandmorty.data.remote.response

import com.google.gson.annotations.SerializedName
import com.ramirez.rickandmorty.data.remote.models.CharacterModel

data class CharacterResponse(
    @SerializedName("results") val results: List<CharacterModel>
)