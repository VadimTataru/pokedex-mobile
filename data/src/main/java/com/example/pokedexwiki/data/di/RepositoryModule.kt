package com.example.pokedexwiki.data.di

import com.example.pokedexwiki.data.repository.PokemonRepositoryImpl
import com.example.pokedexwiki.data.source.local.PokemonDatabase
import com.example.pokedexwiki.data.source.remote.PokemonAPIService
import com.example.pokedexwiki.domain.repository.PokemonRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun providePokemonRepository(
        service: PokemonAPIService,
        database: PokemonDatabase
    ): PokemonRepository {
        return PokemonRepositoryImpl(service, database)
    }
}