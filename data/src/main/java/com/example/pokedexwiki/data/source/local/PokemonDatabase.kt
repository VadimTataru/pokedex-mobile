package com.example.pokedexwiki.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pokedexwiki.data.models.PokemonEntity
import com.example.pokedexwiki.data.source.local.dao.PokemonDao

@Database(entities = [PokemonEntity::class], version = 2)
abstract class PokemonDatabase: RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao

    /*companion object {
        private var dbInstance: PokemonDatabase? = null

        fun getPokemonDatabaseInstance(context: Context): PokemonDatabase {
            if(dbInstance == null) {
                dbInstance = Room.databaseBuilder(
                    context,
                    PokemonDatabase::class.java,
                    "pokemonDb"
                )
                    .allowMainThreadQueries()
                    .build()
            }

            return dbInstance!!
        }
    }*/
}