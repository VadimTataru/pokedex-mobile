package com.example.pokedexwiki.presentation.viewmodel

import android.annotation.SuppressLint
import android.util.Log
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

    @SuppressLint("CheckResult")
    fun fetchPokemon(pokemonName: String) {
        service.getPokemonByName(pokemonName)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d(CHECK_TAG, "${it.imageUrls["front_default"]}")
            }, {
                Log.d(CHECK_TAG, it.localizedMessage!!)
            })
            .untilCleared()
    }


}