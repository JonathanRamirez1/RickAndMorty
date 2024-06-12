package com.ramirez.rickandmorty.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ramirez.rickandmorty.R
import com.ramirez.rickandmorty.databinding.FragmentDetailCharacterBinding

class DetailCharacterFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentDetailCharacterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailCharacterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setContentCharacter()
    }

    private fun setContentCharacter() {
        arguments?.let { bundle ->
            binding.apply {
                val name = bundle.getString("name")
                val status = bundle.getString("status")
                val species = bundle.getString("species")
                val image = bundle.getString("image")
                characterName.text = name
                characterStatus.text = "Status: $status"
                characterSpecies.text = "Species: $species"
                characterImage.load(image) {
                    placeholder(R.drawable.ic_access_time)
                    error(R.drawable.ic_load_error)
                    fallback(R.drawable.ic_fallback)
                }
                closeButton.setOnClickListener { dismiss() }
            }
        }
    }
}
