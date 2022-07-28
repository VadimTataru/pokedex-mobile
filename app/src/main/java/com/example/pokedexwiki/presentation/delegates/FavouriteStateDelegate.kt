package com.example.pokedexwiki.presentation.delegates

import com.example.pokedexwiki.data.models.Pokemon

interface FavouriteStateDelegate {
    fun addFavourite(pokemon: Pokemon)
    fun deleteFavourite(pokemon: Pokemon)
}