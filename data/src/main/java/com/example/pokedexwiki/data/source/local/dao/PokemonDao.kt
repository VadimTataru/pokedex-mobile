package com.example.pokedexwiki.data.source.local.dao

import androidx.room.*
import com.example.pokedexwiki.data.models.PokemonEntity
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface PokemonDao {

    @Query("select * from pokemons")
    fun getAll(): List<PokemonEntity>?

    @Query("select * from pokemons where name = :pokemonName")
    fun getByName(pokemonName: String): PokemonEntity?

    @Query("select * from pokemons where id = :id")
    fun getById(id: Int): PokemonEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPokemon(pokemon: PokemonEntity)

    @Delete
    fun deletePokemon(pokemon: PokemonEntity)
}