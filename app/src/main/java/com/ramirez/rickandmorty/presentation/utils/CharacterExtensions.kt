package com.ramirez.rickandmorty.presentation.utils

import com.ramirez.rickandmorty.data.local.entities.CharacterEntity
import com.ramirez.rickandmorty.data.remote.models.CharacterModel
import com.ramirez.rickandmorty.domain.models.Character

fun CharacterEntity.toCharacter(): Character {
    return Character(
        id = this.id,
        name = this.name,
        status = this.status,
        species = this.species,
        type = this.type,
        gender = this.gender,
        image = this.image,
        url = this.url,
        created = this.created,
        isFavorite = this.isFavorite
    )
}

fun Character.toCharacterEntity(): CharacterEntity {
    return CharacterEntity(
        id = this.id,
        name = this.name,
        status = this.status,
        species = this.species,
        type = this.type,
        gender = this.gender,
        image = this.image,
        url = this.url,
        created = this.created,
        isFavorite = this.isFavorite
    )
}

fun CharacterModel.toCharacterEntity(): CharacterEntity {
    return CharacterEntity(
        id = this.id,
        name = this.name,
        status = this.status,
        species = this.species,
        type = this.type,
        gender = this.gender,
        image = this.image,
        url = this.url,
        created = this.created,
        isFavorite = this.isFavorite
    )
}