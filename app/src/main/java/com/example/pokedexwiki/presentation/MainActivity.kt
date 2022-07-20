package com.example.pokedexwiki.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pokedexwiki.databinding.ActivityMainBinding

// TODO #1: Fetch pokemon count and save it to database:
//          - Create view model
//          - Check if we have count in database and save count if we haven't

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}