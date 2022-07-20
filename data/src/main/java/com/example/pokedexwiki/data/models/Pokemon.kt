package com.example.pokedexwiki.data.models

import com.example.pokedexwiki.domain.models.PokemonDomain
import com.google.gson.annotations.SerializedName

// https://pokeapi.co/api/v2/pokemon/1
data class Pokemon(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("height") val height: Int,
    @SerializedName("weight") val weight: Int,
    @SerializedName("base_experience") val baseExperience: Int,
    @SerializedName("sprites") val sprites: OtherSprites
) {
    fun toPokemonEntity() = PokemonEntity(id, name, height, weight, baseExperience, sprites.otherSprites.imageUrl.link)
    fun toPokemonDomain() = PokemonDomain(id, name, height, weight, baseExperience, sprites.otherSprites.imageUrl.link)
}

data class OtherSprites(
    @SerializedName("other") val otherSprites: SpritesObject
)

data class SpritesObject(
    @SerializedName("home") val imageUrl: Sprite
)

data class Sprite (
    @SerializedName("front_default") val link: String
)