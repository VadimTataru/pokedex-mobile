package com.example.pokedexwiki.domain.repository

import com.example.pokedexwiki.domain.models.PokemonDomain
import io.reactivex.Single

interface PokemonRepository {
    fun getPokemonByName(input: String): Single<PokemonDomain>
    fun getPokemonById(id: Int): Single<PokemonDomain>
    fun addPokemon(pokemonDomain: PokemonDomain)
    fun deletePokemon(pokemonDomain: PokemonDomain)
    fun checkFavourite(input: String): Boolean
    fun getPokemonListFromDb(): List<PokemonDomain>
}