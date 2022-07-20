package com.example.pokedexwiki.domain.models

data class PokemonDomain(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val baseExperience: Int,
    val imageUrl: String
)
