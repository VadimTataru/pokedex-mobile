package com.example.pokedexwiki.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.pokedexwiki.R
import com.example.pokedexwiki.data.models.OtherSprites
import com.example.pokedexwiki.data.models.Pokemon
import com.example.pokedexwiki.data.models.Sprite
import com.example.pokedexwiki.data.models.SpritesObject
import com.example.pokedexwiki.databinding.FragmentFavouriteBinding
import com.example.pokedexwiki.presentation.adapter.PokemonListAdapter
import com.example.pokedexwiki.presentation.viewmodel.FavouriteViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class FavouriteFragment : Fragment() {
    @Inject
    private lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: FavouriteViewModel
    private lateinit var binding: FragmentFavouriteBinding
    private lateinit var pokemonListAdapter: PokemonListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(FavouriteViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavouriteBinding.inflate(inflater)
        initRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
    }

    private fun initRecyclerView() {
        binding.rcView.apply {
            pokemonListAdapter = PokemonListAdapter()
            adapter = pokemonListAdapter
        }
    }

    private fun loadData() {
        viewModel.loadData()
        viewModel.getPokemonList().observe(viewLifecycleOwner) {
            if(it.isNotEmpty()) {
                for (p in it) {
                    val pokemon = Pokemon(p.id, p.name, p.height, p.weight, p.baseExperience,
                        OtherSprites(
                            SpritesObject(
                                Sprite(
                                    p.imageUrl)
                            )
                        )
                    )
                    pokemonListAdapter.addPokemon(pokemon)
                }
            } else {
                Toast.makeText(this@FavouriteFragment.requireContext(), "Error in fetching data", Toast.LENGTH_SHORT).show()
            }
        }
    }
}