package com.example.pokedexwiki.di

import com.example.pokedexwiki.domain.interactor.PokemonInteractor
import com.example.pokedexwiki.domain.repository.PokemonRepository
import dagger.Module
import dagger.Provides

@Module
class InteractorModule {
    @Provides
    fun providePokemonInteractor(pokemonRepository: PokemonRepository): PokemonInteractor {
        return PokemonInteractor(pokemonRepository)
    }
}