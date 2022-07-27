package com.example.pokedexwiki.domain.interactor

import com.example.pokedexwiki.domain.models.PokemonDomain
import com.example.pokedexwiki.domain.repository.PokemonRepository
import io.reactivex.Single
import javax.inject.Inject

class PokemonInteractor @Inject constructor(
    private val repository: PokemonRepository
){
    fun getPokemonByName(name: String): Single<PokemonDomain> {
        return repository.getPokemonByName(name)
    }

    fun getPokemonById(id: Int): Single<PokemonDomain> {
        return repository.getPokemonById(id)
    }

    fun addToFavourite(pokemonDomain: PokemonDomain) {
        repository.addPokemon(pokemonDomain)
    }

    fun deleteFromFavourite(pokemonDomain: PokemonDomain) {
        repository.deletePokemon(pokemonDomain)
    }

    fun checkFavourite(name: String): Boolean {
        return repository.checkFavourite(name)
    }

    fun getPokemonListFromDb(): List<PokemonDomain> {
        return repository.getPokemonListFromDb()
    }
}