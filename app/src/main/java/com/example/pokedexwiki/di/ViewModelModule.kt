package com.example.pokedexwiki.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokedexwiki.di.annotation.ViewModelKey
import com.example.pokedexwiki.presentation.base.ViewModelFactory
import com.example.pokedexwiki.presentation.viewmodel.FavouriteViewModel
import com.example.pokedexwiki.presentation.viewmodel.RandomViewModel
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
    internal abstract fun bindSearchViewModel(searchViewModel: SearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RandomViewModel::class)
    internal abstract fun bindRandomViewModel(randomViewModel: RandomViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavouriteViewModel::class)
    internal abstract fun bindFavouriteViewModel(favouriteViewModel: FavouriteViewModel): ViewModel
}