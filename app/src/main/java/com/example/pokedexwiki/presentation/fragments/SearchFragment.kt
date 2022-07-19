package com.example.pokedexwiki.presentation.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.VERTICAL
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedexwiki.data.models.OtherSprites
import com.example.pokedexwiki.data.models.Pokemon
import com.example.pokedexwiki.data.models.Sprite
import com.example.pokedexwiki.data.models.SpritesObject
import com.example.pokedexwiki.databinding.FragmentSearchBinding
import com.example.pokedexwiki.domain.models.PokemonDomain
import com.example.pokedexwiki.presentation.adapter.PokemonAdapter
import com.example.pokedexwiki.presentation.viewmodel.SearchViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class SearchFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: SearchViewModel

    lateinit var binding: FragmentSearchBinding

    lateinit var pokemonAdapter: PokemonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SearchViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater)
        initRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rcView.visibility = View.GONE
        binding.searchBtn.setOnClickListener {
            binding.rcView.visibility = View.VISIBLE
            val text = binding.searchEt.text.toString()
            loadData(text)
        }
    }


    private fun initRecyclerView() {
        binding.rcView.apply {
            pokemonAdapter = PokemonAdapter()
            adapter = pokemonAdapter
        }
    }

    private fun loadData(input: String) {
        viewModel.getPokemonSingle().observe(viewLifecycleOwner, Observer<PokemonDomain>{
            if (it != null) {
                val pokemon = Pokemon(it.id, it.name, it.height, it.weight, it.baseExperience, OtherSprites(
                    SpritesObject(Sprite(it.imageUrl))
                ))
                pokemonAdapter.changePokemon(pokemon)
            } else {
                Toast.makeText(this@SearchFragment.requireContext(), "Error in fetching data", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.fetchPokemon(input)
    }

    private fun setFavouriteState() {
        /*if !favourite
        *   */
    }
}