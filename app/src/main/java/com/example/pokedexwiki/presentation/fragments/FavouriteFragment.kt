package com.example.pokedexwiki.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pokedexwiki.R
import com.example.pokedexwiki.databinding.FragmentFavouriteBinding

class FavouriteFragment : Fragment() {

    lateinit var binding: FragmentFavouriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavouriteBinding.inflate(inflater)
        return binding.root
    }
}