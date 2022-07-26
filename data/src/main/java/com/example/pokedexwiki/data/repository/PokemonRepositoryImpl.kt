package com.example.pokedexwiki.data.repository

import android.annotation.SuppressLint
import android.util.Log
import com.example.pokedexwiki.data.models.PokemonEntity
import com.example.pokedexwiki.data.source.local.PokemonDatabase
import com.example.pokedexwiki.data.source.remote.PokemonAPIService
import com.example.pokedexwiki.domain.models.PokemonDomain
import com.example.pokedexwiki.domain.repository.PokemonRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PokemonRepositoryImpl
    @Inject constructor(
        private val service: PokemonAPIService,
        private val database: PokemonDatabase
    ): PokemonRepository {

    @SuppressLint("CheckResult")
    override fun getPokemonByName(input: String): Single<PokemonDomain> {
        val result: Single<PokemonDomain> = service.getPokemonByName(input)
            .flatMap{
                Single.just(it.toPokemonDomain())
            }
        return result
    }

    @SuppressLint("CheckResult")
    override fun getPokemonById(id: Int): Single<PokemonDomain> {
        val result: Single<PokemonDomain> = service.getPokemonById(id)
            .flatMap {
                Single.just(it.toPokemonDomain())
            }
        return result
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

    override fun checkFavourite(input: String): Boolean {
        var result: Boolean = false
        val dispose =  database.pokemonDao().getByName(input)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                result = it != null
            }, {

            })
        dispose.dispose()
        return result
    }

    override fun getPokemonListFromDb(): Single<List<PokemonDomain>> {
        TODO("Not yet implemented")
    }


}
