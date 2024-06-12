package com.ramirez.rickandmorty.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramirez.rickandmorty.domain.models.Character
import com.ramirez.rickandmorty.domain.repositories.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val repository: CharacterRepository
) : ViewModel() {

    private val _characters = MutableStateFlow<List<Character>>(emptyList())
    val characters: StateFlow<List<Character>> get() = _characters.asStateFlow()

    private val _favoriteCharacters = MutableStateFlow<List<Character>>(emptyList())
    val favoriteCharacters: StateFlow<List<Character>> get() = _favoriteCharacters.asStateFlow()

    init {
        fetchCharacters()
    }

    private fun fetchCharacters() = viewModelScope.launch {
        if (repository.shouldFetchFromRemote()) {
            repository.fetchAndStoreCharacters()
        }
        repository.getAllCharacters().collect { charactersList ->
            _characters.value = charactersList
            updateFavoriteCharacters()
        }
    }

    fun toggleFavorite(character: Character) = viewModelScope.launch {
        val newFavoriteStatus = !character.isFavorite
        repository.updateFavoriteStatus(character.id, newFavoriteStatus)
        updateFavoriteCharacters()
    }

    private fun updateFavoriteCharacters() = viewModelScope.launch {
        val favoriteList = _characters.value.filter { it.isFavorite }
        _favoriteCharacters.value = favoriteList
    }
}