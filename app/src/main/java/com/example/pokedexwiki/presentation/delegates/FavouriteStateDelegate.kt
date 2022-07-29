package com.example.pokedexwiki.presentation.delegates

import com.example.pokedexwiki.data.models.Pokemon

interface FavouriteStateDelegate {
    fun addFavourite(pokemon: Pokemon): Boolean
    fun deleteFavourite(pokemon: Pokemon): Boolean
}