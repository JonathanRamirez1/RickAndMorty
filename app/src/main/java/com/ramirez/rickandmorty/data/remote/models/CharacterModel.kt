package com.ramirez.rickandmorty.data.remote.models

import com.google.gson.annotations.SerializedName

data class CharacterModel(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("status") val status: String,
    @SerializedName("species") val species: String,
    @SerializedName("type") val type: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("image") val image: String,
    @SerializedName("url") val url: String,
    @SerializedName("created") val created: String,
    var isFavorite: Boolean = false
)
