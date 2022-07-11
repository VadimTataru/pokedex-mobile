package com.example.pokedexwiki.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokedexwiki.presentation.base.ViewModelFactory
import com.example.pokedexwiki.presentation.viewmodel.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    //Factory
    @Binds
    internal abstract fun bindFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    //ViewModels
    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    internal  abstract fun bindSearchViewModel(searchViewModel: SearchViewModel): ViewModel
}