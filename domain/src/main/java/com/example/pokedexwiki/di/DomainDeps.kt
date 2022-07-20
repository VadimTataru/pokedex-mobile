package com.example.pokedexwiki.di

import com.example.pokedexwiki.domain.repository.PokemonRepository
import kotlin.properties.Delegates

interface DomainDeps {
    fun pokemonRepository(): PokemonRepository
}

/*
interface DomainDepsProvider {
    val deps: DomainDeps

    companion object: DomainDepsProvider by DomainDepsStore
}

object DomainDepsStore: DomainDepsProvider {
    override var deps: DomainDeps by Delegates.notNull()
}*/
