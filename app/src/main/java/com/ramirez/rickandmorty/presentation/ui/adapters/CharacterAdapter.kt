package com.ramirez.rickandmorty.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.ramirez.rickandmorty.R
import com.ramirez.rickandmorty.databinding.ItemCharacterBinding
import com.ramirez.rickandmorty.domain.models.Character
import com.ramirez.rickandmorty.presentation.utils.CharacterDiffCallback

class CharacterAdapter(
    private val onFavoriteClick: (Character) -> Unit,
    private val onCharacterClickListener: OnCharacterClickListener
) : ListAdapter<Character, CharacterAdapter.CharacterViewHolder>(CharacterDiffCallback()) {

    private var favoriteCharacters: Set<Int> = emptySet()
    private var countClickFavorite = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding =
            ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = getItem(position)
        val isFavorite = favoriteCharacters.contains(character.id)
        holder.bind(character, isFavorite, onFavoriteClick)
        holder.itemView.setOnClickListener {
            onCharacterClickListener.onCharacterClick(character)
        }
    }

    fun setFavorites(favoriteIds: Set<Int>) {
        favoriteCharacters = favoriteIds
    }

    inner class CharacterViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(character: Character, isFavorite: Boolean, onFavoriteClick: (Character) -> Unit) {
            binding.apply {
                characterImage.load(character.image) {
                    placeholder(R.drawable.ic_access_time)
                    error(R.drawable.ic_load_error)
                    fallback(R.drawable.ic_fallback)
                }
                characterImage.scaleType = ImageView.ScaleType.CENTER_CROP
                characterName.text = character.name
                characterSpecies.text = character.species

                favoriteIcon.setOnClickListener {
                    countClickFavorite++
                    onFavoriteClick(character)
                    if (countClickFavorite == 1) {
                        character.isFavorite = true
                    } else if (countClickFavorite == 2) {
                        character.isFavorite = false
                        countClickFavorite = 0
                    }
                    favoriteIcon.setImageResource(
                        if (character.isFavorite) R.drawable.ic_favorite else R.drawable.ic_favorite_border
                    )
                }
                favoriteIcon.setImageResource(
                    if (character.isFavorite) R.drawable.ic_favorite else R.drawable.ic_favorite_border
                )
            }
        }
    }

    interface OnCharacterClickListener {
        fun onCharacterClick(character: Character)
    }
}