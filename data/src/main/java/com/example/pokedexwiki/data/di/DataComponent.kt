package com.example.pokedexwiki.data.di

import android.app.Application
import com.example.pokedexwiki.data.repository.PokemonRepositoryImpl
import com.example.pokedexwiki.data.source.local.dao.PokemonDao
import com.example.pokedexwiki.data.source.remote.PokemonAPIService
import com.example.pokedexwiki.di.DomainDeps
import com.example.pokedexwiki.domain.repository.PokemonRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

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
    //fun getPokemonRepository(): PokemonRepositoryImpl

    @Component.Builder
    interface Builder {

        /*@BindsInstance
        fun application(application: Application): DataComponent.Builder*/
        fun dep(dep: ContextDep): Builder

        fun build(): DataComponent
    }

}