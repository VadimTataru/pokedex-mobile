package com.example.pokedexwiki.data.models

import com.google.gson.annotations.SerializedName

data class PokemonCount(
    @SerializedName("count") val count: Int
)