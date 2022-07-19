package com.example.pokedexwiki.di.deps

import android.app.Application
import com.example.pokedexwiki.data.di.ContextDep

class ContextDepImpl(private val application: Application): ContextDep {

    override fun application(): Application {
        return application
    }

}