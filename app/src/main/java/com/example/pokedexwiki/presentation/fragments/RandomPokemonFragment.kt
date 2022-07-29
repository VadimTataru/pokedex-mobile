package com.example.pokedexwiki.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.pokedexwiki.R
import com.example.pokedexwiki.data.models.OtherSprites
import com.example.pokedexwiki.data.models.Pokemon
import com.example.pokedexwiki.data.models.Sprite
import com.example.pokedexwiki.data.models.SpritesObject
import com.example.pokedexwiki.databinding.FragmentRandomPokemonBinding
import com.example.pokedexwiki.presentation.adapter.PokemonAdapter
import com.example.pokedexwiki.presentation.delegates.FavouriteStateDelegate
import com.example.pokedexwiki.presentation.viewmodel.RandomViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class RandomPokemonFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: RandomViewModel

    private lateinit var binding: FragmentRandomPokemonBinding
    private lateinit var pokemonAdapter: PokemonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(RandomViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRandomPokemonBinding.inflate(inflater)
        initRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rcView.visibility = View.GONE
        initViews()
    }

    private fun initViews() {
        binding.apply {
            randomBtn.setOnClickListener {
                rcView.visibility = View.VISIBLE
                loadData()
            }
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

            })
            adapter = pokemonAdapter
        }
    }

    private fun loadData() {
        viewModel.fetchPokemon()
        viewModel.getPokemonSingle().observe(viewLifecycleOwner) {
            if(it != null) {
                val pokemon = Pokemon(it.id, it.name, it.height, it.weight, it.baseExperience,
                    OtherSprites(
                        SpritesObject(
                            Sprite(
                                it.imageUrl)
                        )
                    )
                )
                pokemonAdapter.changePokemon(pokemon)
            } else {
                Toast.makeText(this@RandomPokemonFragment.requireContext(), "Error in fetching data", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setFavouriteState() {
        val result = viewModel.setFavouriteState()
        val toastText = if(result) "Pokemon successfully added!" else "Pokemon deleted!"
        Toast.makeText(this@RandomPokemonFragment.requireContext(), toastText, Toast.LENGTH_SHORT).show()
    }
}