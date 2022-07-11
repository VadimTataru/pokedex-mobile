package com.example.pokedexwiki.data.di

import com.example.pokedexwiki.data.source.remote.PokemonAPIService
import dagger.Component

@Component(modules = [NetworkModule::class])
interface DataComponent {

    fun getPokemonApiService(): PokemonAPIService

    @Component.Builder
    interface Builder {
        fun build(): DataComponent
    }

}