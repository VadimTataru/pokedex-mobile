package com.example.pokedexwiki.presentation.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.pokedexwiki.R
import com.example.pokedexwiki.data.models.Pokemon
import com.example.pokedexwiki.databinding.PokemonItemBinding
import java.util.*

class PokemonAdapter: RecyclerView.Adapter<PokemonAdapter.PokemonHolder>() {

    private var pokemon: Pokemon? = null

    class PokemonHolder(item: View): RecyclerView.ViewHolder(item) {
        private val binding = PokemonItemBinding.bind(item)
        @SuppressLint("SetTextI18n")
        fun bind(pokemon: Pokemon) = with(binding) {
            pokemonId.text = "# ${pokemon.id}"
            pokemonHeight.text = "Height: ${pokemon.height}"
            pokemonWeight.text = "Weight: ${pokemon.weight}"
            pokemonName.text = pokemon.name.uppercase()

            val link = pokemon.sprites.otherSprites.imageUrl.link
            Glide.with(pokemonImage)
                .load(link)
                .circleCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(pokemonImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item, parent, false)
        return PokemonHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonHolder, position: Int) {
        if(pokemon != null) holder.bind(pokemon!!)
    }

    override fun getItemCount(): Int {
        return 1
    }

    @SuppressLint("NotifyDataSetChanged")
    fun changePokemon(pokemon: Pokemon) {
        this.pokemon = pokemon
        notifyDataSetChanged()
    }
}