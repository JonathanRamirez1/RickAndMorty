package com.ramirez.rickandmorty.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ramirez.rickandmorty.databinding.FragmentCharacterBinding
import com.ramirez.rickandmorty.domain.models.Character
import com.ramirez.rickandmorty.presentation.ui.adapters.CharacterAdapter
import com.ramirez.rickandmorty.presentation.viewmodels.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterFragment : Fragment(), CharacterAdapter.OnCharacterClickListener {

    private val viewModel: CharacterViewModel by viewModels()
    private lateinit var binding: FragmentCharacterBinding
    private lateinit var characterAdapter: CharacterAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setCharacterAdapter()
        setObservers()
    }

    private fun setCharacterAdapter() {
        characterAdapter = CharacterAdapter(
            onFavoriteClick = { character -> viewModel.toggleFavorite(character) },
            onCharacterClickListener = this
        )

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = characterAdapter
        }
    }

    private fun setObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.characters.collect { characters ->
                characterAdapter.submitList(characters)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.favoriteCharacters.collect { favorites ->
                characterAdapter.setFavorites(favorites.map { it.id }.toSet())
            }
        }
    }

    override fun onCharacterClick(character: Character) {
        val detailCharacterFragment = DetailCharacterFragment().apply {
            arguments = Bundle().apply {
                putString("name", character.name)
                putString("status", character.status)
                putString("species", character.species)
                putString("image", character.image)
            }
        }
        detailCharacterFragment.show(parentFragmentManager, detailCharacterFragment.tag)
    }
}