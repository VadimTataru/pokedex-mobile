package com.example.pokedexwiki.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.pokedexwiki.data.models.Pokemon
import com.example.pokedexwiki.domain.interactor.PokemonInteractor
import com.example.pokedexwiki.domain.models.PokemonDomain
import com.example.pokedexwiki.presentation.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FavouriteViewModel @Inject constructor(
    private val interactor: PokemonInteractor
): BaseViewModel() {

    private var pokemonList: MutableLiveData<List<PokemonDomain>> = MutableLiveData()

    fun getPokemonList(): MutableLiveData<List<PokemonDomain>> {
        return pokemonList
    }

    fun loadData() {
        val list = interactor.getPokemonListFromDb()
        Log.d("just check", "${list.size}")
        pokemonList.postValue(list)
    }

    fun deleteFavourite(pokemon: Pokemon) {
        interactor.deleteFromFavourite(pokemon.toPokemonDomain())
    }
}