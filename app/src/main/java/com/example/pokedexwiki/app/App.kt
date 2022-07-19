package com.example.pokedexwiki.app

import android.app.Application
import com.example.pokedexwiki.data.di.ContextDep
import com.example.pokedexwiki.data.di.DaggerDataComponent
import com.example.pokedexwiki.data.di.DataComponent
import com.example.pokedexwiki.di.ApplicationComponent
import com.example.pokedexwiki.di.DaggerApplicationComponent
import com.example.pokedexwiki.di.DaggerDomainComponent
import com.example.pokedexwiki.di.DomainComponent
import com.example.pokedexwiki.di.deps.ContextDepImpl
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class App: Application(), HasAndroidInjector {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    lateinit var contextDep: ContextDep
    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onCreate() {
        super.onCreate()

        contextDep = ContextDepImpl(this)

        val applicationComponent = DaggerApplicationComponent
            .builder()
            .application(contextDep.application())
            .dataComponent(provideDataComponent())
            .domainComponent(provideDomainComponent())
            .build()

        applicationComponent.inject(this)
    }

    private fun provideDataComponent(): DataComponent {
        return  DaggerDataComponent
            .builder()
            .dep(contextDep)
            .build()
    }

    private fun provideDomainComponent(): DomainComponent {
        return DaggerDomainComponent
            .builder()
            .deps(provideDataComponent())
            .build()
    }

}