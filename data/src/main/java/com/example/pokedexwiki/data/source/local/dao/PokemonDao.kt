package com.example.pokedexwiki.data.source.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.pokedexwiki.data.models.PokemonEntity

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemonentity")
    fun getAll(): List<PokemonEntity>

    @Insert
    fun addPokemon(pokemon: PokemonEntity)

    @Delete
    fun deletePokemon(pokemon: PokemonEntity)
}