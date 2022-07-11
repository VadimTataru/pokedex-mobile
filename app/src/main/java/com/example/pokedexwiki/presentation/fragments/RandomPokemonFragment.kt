package com.example.pokedexwiki.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pokedexwiki.R
import com.example.pokedexwiki.databinding.FragmentRandomPokemonBinding

class RandomPokemonFragment : Fragment() {

    lateinit var binding: FragmentRandomPokemonBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRandomPokemonBinding.inflate(inflater)
        return binding.root
    }
}