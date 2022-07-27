package com.example.pokedexwiki.di

import com.example.pokedexwiki.presentation.fragments.FavouriteFragment
import com.example.pokedexwiki.presentation.fragments.RandomPokemonFragment
import com.example.pokedexwiki.presentation.fragments.SearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {
    @ContributesAndroidInjector
    abstract fun bindSearchFragment(): SearchFragment

    @ContributesAndroidInjector
    abstract fun bindRandomFragment(): RandomPokemonFragment

    @ContributesAndroidInjector
    abstract fun bindFavouriteFragment(): FavouriteFragment
}