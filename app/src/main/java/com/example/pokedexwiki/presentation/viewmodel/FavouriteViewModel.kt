package com.example.pokedexwiki.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.pokedexwiki.domain.interactor.PokemonInteractor
import com.example.pokedexwiki.domain.models.PokemonDomain
import com.example.pokedexwiki.presentation.base.BaseViewModel
import javax.inject.Inject

class FavouriteViewModel @Inject constructor(
    private val interactor: PokemonInteractor
): BaseViewModel() {

    private var pokemonList: MutableLiveData<List<PokemonDomain>> = MutableLiveData()

    fun getPokemonList(): MutableLiveData<List<PokemonDomain>> {
        return pokemonList
    }

    fun loadData() {
        interactor.
    }
}