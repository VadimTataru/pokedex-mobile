package com.example.pokedexwiki.data.source.remote

import com.example.pokedexwiki.data.models.Pokemon
import io.reactivex.Single
import retrofit2.http.GET

interface PokemonAPIService {
    // TODO: Изучить документацию к API
    @GET("pokemon.json")
    fun getPokemons(): Single<List<Pokemon>>
}