package com.example.pokedexwiki.data.di

import com.example.pokedexwiki.data.repository.PokemonRepositoryImpl
import com.example.pokedexwiki.data.source.local.dao.PokemonDao
import com.example.pokedexwiki.data.source.remote.PokemonAPIService
import com.example.pokedexwiki.di.DomainDeps
import dagger.Component

@Component(
    modules = [
        NetworkModule::class,
        DatabaseModule::class,
        RepositoryModule::class
    ],
    dependencies = [
        ContextDep::class
    ]
)
@DataScope
interface DataComponent: DomainDeps {

    override fun pokemonRepository(): PokemonRepositoryImpl

    fun getPokemonApiService(): PokemonAPIService
    fun getPokemonDao(): PokemonDao

    @Component.Builder
    interface Builder {

        fun dep(dep: ContextDep): Builder

        fun build(): DataComponent
    }

}