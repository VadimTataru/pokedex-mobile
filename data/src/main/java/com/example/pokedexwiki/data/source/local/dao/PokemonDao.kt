package com.example.pokedexwiki.data.source.local.dao

import androidx.room.*
import com.example.pokedexwiki.data.models.PokemonEntity
import io.reactivex.Single

@Dao
interface PokemonDao {

    @Query("select * from pokemons")
    fun getAll(): Single<List<PokemonEntity>>

    @Query("select * from pokemons where name = :pokemonName")
    fun getByName(pokemonName: String): Single<PokemonEntity?>

    @Query("select * from pokemons where id = :id")
    fun getById(id: Int): Single<PokemonEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPokemon(pokemon: PokemonEntity)

    @Delete
    fun deletePokemon(pokemon: PokemonEntity)
}