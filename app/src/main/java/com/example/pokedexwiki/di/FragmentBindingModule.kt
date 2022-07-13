package com.example.pokedexwiki.di

import com.example.pokedexwiki.presentation.fragments.SearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {
    @ContributesAndroidInjector
    abstract fun bindSearchFragment(): SearchFragment
}