package com.example.pokedexwiki.di

import android.app.Application
import com.example.pokedexwiki.app.App
import com.example.pokedexwiki.data.di.ContextDep
import com.example.pokedexwiki.data.di.DataComponent
import com.example.pokedexwiki.di.annotation.ApplicationScope
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@Component(modules = [
    AndroidInjectionModule::class,
    ViewModelModule::class,
    ActivityBindingModule::class,
    FragmentBindingModule::class
], dependencies = [
    DataComponent::class, DomainComponent::class
])
@ApplicationScope
interface ApplicationComponent: AndroidInjector<App>, ContextDep {

    override fun application(): Application

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun dataComponent(dataComponent: DataComponent): Builder
        fun domainComponent(domainComponent: DomainComponent): Builder

        fun build(): ApplicationComponent
    }
}