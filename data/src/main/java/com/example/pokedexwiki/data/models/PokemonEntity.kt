package com.example.pokedexwiki.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "height") val height: Int,
    @ColumnInfo(name = "weight") val weight: Int,
    @ColumnInfo(name = "base_experience") val baseExperience: Int,
    @ColumnInfo(name = "image_url") val imageUrl: String
)