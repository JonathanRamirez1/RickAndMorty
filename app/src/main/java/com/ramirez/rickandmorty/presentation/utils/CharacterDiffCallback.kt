package com.ramirez.rickandmorty.presentation.utils

import androidx.recyclerview.widget.DiffUtil
import com.ramirez.rickandmorty.domain.models.Character

class CharacterDiffCallback : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }
}