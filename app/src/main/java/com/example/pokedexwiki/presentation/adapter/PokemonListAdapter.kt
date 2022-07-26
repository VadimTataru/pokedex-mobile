package com.example.pokedexwiki.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.pokedexwiki.R
import com.example.pokedexwiki.data.models.Pokemon
import com.example.pokedexwiki.databinding.PokemonItemBinding

class PokemonListAdapter: RecyclerView.Adapter<PokemonListAdapter.PokemonListHolder>() {

    private val pokemonList = mutableListOf<Pokemon>()

    class PokemonListHolder(item: View): RecyclerView.ViewHolder(item) {
        private val binding = PokemonItemBinding.bind(item)
        @SuppressLint("SetTextI18n")
        fun bind(pokemon: Pokemon) = with(binding){
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item, parent, false)
        return PokemonListHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonListHolder, position: Int) {
        holder.bind(pokemonList[position])
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addPokemon(pokemon: Pokemon) {
        pokemonList.add(pokemon)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun deletePokemon(pokemon: Pokemon) {
        pokemonList.remove(pokemon)
        notifyDataSetChanged()
    }

}