package com.example.pokedexwiki.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.pokedexwiki.data.models.Pokemon
import com.example.pokedexwiki.data.source.remote.PokemonAPIService
import com.example.pokedexwiki.domain.interactor.PokemonInteractor
import com.example.pokedexwiki.domain.models.PokemonDomain
import com.example.pokedexwiki.presentation.base.BaseViewModel
import com.example.pokedexwiki.utils.Constants.CHECK_TAG
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val interactor: PokemonInteractor
) : BaseViewModel() {

    private var pokemon: MutableLiveData<PokemonDomain> = MutableLiveData()
    private var favState: MutableLiveData<Boolean> = MutableLiveData(false)

    fun getPokemonSingle(): MutableLiveData<PokemonDomain> {
        return pokemon
    }

    fun getFavState(): MutableLiveData<Boolean> {
        return favState
    }

    fun fetchPokemon(pokemonName: String) {
        interactor.getPokemonByName(pokemonName)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                pokemon.postValue(it)
            }, {
                pokemon.postValue(null)
                Log.d(CHECK_TAG, it.localizedMessage!!)
            })
            .untilCleared()
    }

    fun deletePokemon(pokemon: Pokemon) {
        interactor.deleteFromFavourite(pokemon.toPokemonDomain())
    }

    fun addPokemon(pokemon: Pokemon) {
        interactor.addToFavourite(pokemon.toPokemonDomain())
    }

    fun checkFavouriteState(pokemon: Pokemon): Boolean {
        return interactor.checkFavourite(pokemon.id)
    }
}