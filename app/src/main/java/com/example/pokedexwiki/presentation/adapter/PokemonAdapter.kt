package com.example.pokedexwiki.presentation.adapter

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
import java.util.ArrayList

class PokemonAdapter(private val context: Context?): RecyclerView.Adapter<PokemonAdapter.PokemonHolder>() {

    private var pokemon: Pokemon? = null

    class PokemonHolder(item: View): RecyclerView.ViewHolder(item) {
        val binding = PokemonItemBinding.bind(item)
        fun bind(pokemon: Pokemon) = with(binding) {
            pokemonId.text = pokemon.id.toString()
            pokemonHeight.text = pokemon.height.toString()
            pokemonWeight.text = pokemon.weight.toString()
            pokemonName.text = pokemon.name
            //set image with glide
            //GlideIt(pokemon.sprites.imageUrl.url, binding.root.context, pokemonImage)
        }

        private fun GlideIt(url: String, context: Context, imageView: ImageView) {
            Glide
                .with(context)
                .load(url)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item, parent, false)
        return PokemonHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonHolder, position: Int) {
        val pokemon = pokemon ?: return
        if (context != null) {
            GlideIt(pokemon.sprites.imageUrl.url, context, holder.binding.pokemonImage)
        }
        holder.bind(pokemon)
    }

    override fun getItemCount(): Int {
        return 0
    }

    private fun GlideIt(url: String, context: Context, imageView: ImageView) {
        Glide
            .with(context)
            .load(url)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imageView)
    }

    fun changePokemon(pokemon: Pokemon) {
        this.pokemon = pokemon
    }
}