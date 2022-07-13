package com.example.pokedexwiki.presentation.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedexwiki.data.models.Pokemon
import com.example.pokedexwiki.databinding.FragmentSearchBinding
import com.example.pokedexwiki.presentation.adapter.PokemonAdapter
import com.example.pokedexwiki.presentation.viewmodel.SearchViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class SearchFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: SearchViewModel

    lateinit var binding: FragmentSearchBinding

    lateinit var adapter: PokemonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SearchViewModel::class.java)
        adapter = PokemonAdapter(this.context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater)
        return binding.root
    }

    //first try bulbasaur
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRc(context)
        binding.searchBtn.setOnClickListener {
            val text = binding.searchEt.text.toString()
            viewModel.fetchPokemon(text)
        }
    }

    @SuppressLint("UseRequireInsteadOfGet")
    private fun initRc(context: Context?) {
        viewModel.pokemon?.observe(viewLifecycleOwner) { pokemon ->
            binding.apply {
                rcView.layoutManager = GridLayoutManager(this@SearchFragment.context, 1)
                rcView.adapter = adapter
                adapter.changePokemon(pokemon)
            }
        }
    }
}