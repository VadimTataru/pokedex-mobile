package com.example.pokedexwiki.data.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.pokedexwiki.data.source.local.PokemonDatabase
import com.example.pokedexwiki.data.source.local.dao.PokemonDao
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @DataScope
    fun providePokemonDbInstance(application: Application): PokemonDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            PokemonDatabase::class.java,
            "pokemonDb"
        )
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @DataScope
    fun providePokemonDao(pokemonDatabase: PokemonDatabase): PokemonDao {
        return pokemonDatabase.pokemonDao()
    }
}