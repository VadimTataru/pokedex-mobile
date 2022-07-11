package com.example.pokedexwiki.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.pokedexwiki.R
import com.example.pokedexwiki.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {
    lateinit var binding: FragmentMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btnSearch.setOnClickListener {
                findNavController().navigate(R.id.action_menuFragment_to_searchFragment)
            }

            btnRandom.setOnClickListener {
                findNavController().navigate(R.id.action_menuFragment_to_randomPokemonFragment)
            }

            btnFav.setOnClickListener {
                findNavController().navigate(R.id.action_menuFragment_to_favouriteFragment)
            }
        }
    }
}