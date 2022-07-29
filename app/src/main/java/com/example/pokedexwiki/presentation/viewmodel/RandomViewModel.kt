package com.example.pokedexwiki.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.pokedexwiki.data.models.Pokemon
import com.example.pokedexwiki.domain.interactor.PokemonInteractor
import com.example.pokedexwiki.domain.models.PokemonDomain
import com.example.pokedexwiki.presentation.base.BaseViewModel
import com.example.pokedexwiki.utils.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class RandomViewModel @Inject constructor(
    private val interactor: PokemonInteractor
): BaseViewModel() {

    private var pokemon: MutableLiveData<PokemonDomain> = MutableLiveData()
    private var favState: MutableLiveData<Boolean> = MutableLiveData(false)

    fun getPokemonSingle(): MutableLiveData<PokemonDomain> {
        return pokemon
    }

    fun getFavState(): MutableLiveData<Boolean> {
        return favState
    }

    fun fetchPokemon() {
        val id = Random().nextInt(905)
        Log.d(Constants.CHECK_TAG, "$id")
        interactor.getPokemonById(id)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                pokemon.postValue(it)
            }, {
                pokemon.postValue(null)
                Log.d(Constants.CHECK_TAG, it.localizedMessage!!)
            })
            .untilCleared()
    }

    fun deletePokemon(pokemon: Pokemon) {
        interactor.deleteFromFavourite(pokemon.toPokemonDomain())
    }

    fun addPokemon(pokemon: Pokemon) {
        interactor.addToFavourite(pokemon.toPokemonDomain())
    }
}