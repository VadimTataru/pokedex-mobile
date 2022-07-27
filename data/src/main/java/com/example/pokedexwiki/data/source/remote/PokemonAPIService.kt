package com.example.pokedexwiki.data.source.remote

import com.example.pokedexwiki.data.models.Pokemon
import com.example.pokedexwiki.data.models.PokemonCount
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface PokemonAPIService {
    @GET("pokemon/{pokemonName}")
    @Headers("Content-Type: application/json")
    fun getPokemonByName(@Path("pokemonName") pokemonName: String): Single<Pokemon>

    @GET("pokemon/{pokemonId}")
    @Headers("Content-Type: application/json")
    fun getPokemonById(@Path("pokemonId") pokemonId: Int): Single<Pokemon>

    @GET("pokemon")
    fun getPokemonCount(): Single<PokemonCount>
}