package com.example.pokedexwiki.app

import android.app.Application
import com.example.pokedexwiki.data.di.DaggerDataComponent
import com.example.pokedexwiki.data.di.DataComponent
import com.example.pokedexwiki.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class App: Application(), HasAndroidInjector {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onCreate() {
        super.onCreate()

        val applicationComponent = DaggerApplicationComponent
            .builder()
            .application(this)
            .dataComponent(provideDataComponent())
            .build()

        applicationComponent.inject(this)
    }

    private fun provideDataComponent(): DataComponent {
        return  DaggerDataComponent
            .builder()
            .build()
    }

}