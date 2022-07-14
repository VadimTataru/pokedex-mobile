package com.example.pokedexwiki.data.repository

import android.annotation.SuppressLint
import android.util.Log
import com.example.pokedexwiki.data.models.PokemonEntity
import com.example.pokedexwiki.data.source.local.PokemonDatabase
import com.example.pokedexwiki.data.source.remote.PokemonAPIService
import com.example.pokedexwiki.domain.models.PokemonDomain
import com.example.pokedexwiki.domain.repository.PokemonRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PokemonRepositoryImpl
    @Inject constructor(
        private val service: PokemonAPIService,
        private val database: PokemonDatabase
    ): PokemonRepository {

    @SuppressLint("CheckResult")
    override fun getPokemonByName(input: String): Single<PokemonDomain> {
        var result: Single<PokemonDomain>? = null
        service.getPokemonByName(input).observeOn(Schedulers.computation())
            .subscribe({
                result = Single.just(it.toPokemonDomain())
            }, {
                Log.d("just_checking", it.localizedMessage)
            })
        return result!!
    }

    @SuppressLint("CheckResult")
    override fun getPokemonById(id: Int): Single<PokemonDomain> {
        var result: Single<PokemonDomain>? = null
        service.getPokemonById(id).observeOn(Schedulers.computation())
            .subscribe({
                result = Single.just(it.toPokemonDomain())
            }, {
                Log.d("just_checking", it.localizedMessage)
            })
        return result!!
    }

    override fun addPokemon(pokemonDomain: PokemonDomain) {
        database.pokemonDao().addPokemon(
            PokemonEntity(
            pokemonDomain.id,
            pokemonDomain.name,
            pokemonDomain.height,
            pokemonDomain.weight,
            pokemonDomain.baseExperience,
            pokemonDomain.imageUrl
        ))
    }

    override fun deletePokemon(pokemonDomain: PokemonDomain) {
        database.pokemonDao().deletePokemon(PokemonEntity(
            pokemonDomain.id,
            pokemonDomain.name,
            pokemonDomain.height,
            pokemonDomain.weight,
            pokemonDomain.baseExperience,
            pokemonDomain.imageUrl
        ))
    }
}
