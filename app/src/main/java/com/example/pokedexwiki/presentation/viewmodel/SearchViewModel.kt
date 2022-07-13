package com.example.pokedexwiki.presentation.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.pokedexwiki.data.models.Pokemon
import com.example.pokedexwiki.data.source.remote.PokemonAPIService
import com.example.pokedexwiki.presentation.base.BaseViewModel
import com.example.pokedexwiki.utils.Constants.CHECK_TAG
import com.google.gson.annotations.SerializedName
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val service: PokemonAPIService
) : BaseViewModel() {

    var pokemon: MutableLiveData<Pokemon>? = null

    @SuppressLint("CheckResult")
    fun fetchPokemon(pokemonName: String) {
        service.getPokemonByName(pokemonName)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                pokemon?.value = it
            }, {
                Log.d(CHECK_TAG, it.localizedMessage!!)
            })
            .untilCleared()
    }


}