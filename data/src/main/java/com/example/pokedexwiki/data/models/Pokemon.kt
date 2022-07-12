package com.example.pokedexwiki.data.models

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

// TODO: Fix image url
// https://pokeapi.co/api/v2/pokemon/1
data class Pokemon(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("height") val height: Int,
    @SerializedName("weight") val weight: Int,
    @SerializedName("base_experience") val baseExperience: Int,
    @SerializedName("sprites") val imageUrls: JsonObject
)