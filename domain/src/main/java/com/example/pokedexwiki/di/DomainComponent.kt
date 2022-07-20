package com.example.pokedexwiki.di

import com.example.pokedexwiki.domain.repository.PokemonRepository
import dagger.Component

@Component(
    modules = [InteractorModule::class],
    dependencies = [DomainDeps::class]
)
interface DomainComponent {

    fun getRepository(): PokemonRepository

    @Component.Builder
    interface Builder {

        fun deps(deps: DomainDeps): Builder

        fun build(): DomainComponent
    }
}