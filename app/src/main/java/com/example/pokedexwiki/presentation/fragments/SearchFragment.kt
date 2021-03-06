package com.example.pokedexwiki.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.pokedexwiki.data.models.OtherSprites
import com.example.pokedexwiki.data.models.Pokemon
import com.example.pokedexwiki.data.models.Sprite
import com.example.pokedexwiki.data.models.SpritesObject
import com.example.pokedexwiki.databinding.FragmentSearchBinding
import com.example.pokedexwiki.presentation.adapter.PokemonAdapter
import com.example.pokedexwiki.presentation.delegates.FavouriteStateDelegate
import com.example.pokedexwiki.presentation.viewmodel.SearchViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class SearchFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: SearchViewModel

    private lateinit var binding: FragmentSearchBinding

    private lateinit var pokemonAdapter: PokemonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SearchViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater)
        initRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rcView.visibility = View.GONE
        initViews()
    }

    private fun initViews() {
        binding.searchBtn.setOnClickListener {
            binding.rcView.visibility = View.VISIBLE
            val text = binding.searchEt.text.toString().lowercase()
            loadData(text)
        }
    }

    private fun initRecyclerView() {
        binding.rcView.apply {
            pokemonAdapter = PokemonAdapter()
            pokemonAdapter.attachDelegate(object: FavouriteStateDelegate {
                override fun addFavourite(pokemon: Pokemon): Boolean {
                    viewModel.addPokemon(pokemon)
                    return true
                }
                override fun deleteFavourite(pokemon: Pokemon): Boolean {
                    viewModel.deletePokemon(pokemon)
                    return false
                }

                override fun checkFavouriteState(pokemon: Pokemon): Boolean {
                    return viewModel.checkFavouriteState(pokemon)
                }
            })
            adapter = pokemonAdapter
        }
    }

    private fun loadData(input: String) {
        viewModel.getPokemonSingle().observe(viewLifecycleOwner){
            if (it != null) {
                val pokemon = Pokemon(it.id, it.name, it.height, it.weight, it.baseExperience, OtherSprites(
                    SpritesObject(Sprite(it.imageUrl))
                ))
                pokemonAdapter.changePokemon(pokemon)
            } else {
                Toast.makeText(this@SearchFragment.requireContext(), "Error in fetching data", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.fetchPokemon(input)
    }
}