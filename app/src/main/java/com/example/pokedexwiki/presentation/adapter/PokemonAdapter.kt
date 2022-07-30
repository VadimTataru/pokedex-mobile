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
import com.example.pokedexwiki.presentation.delegates.FavouriteStateDelegate
import java.util.*

class PokemonAdapter: RecyclerView.Adapter<PokemonAdapter.PokemonHolder>() {

    private var pokemon: Pokemon? = null
    private var delegate: FavouriteStateDelegate? = null

    class PokemonHolder(item: View, private val delegate: FavouriteStateDelegate?): RecyclerView.ViewHolder(item) {
        private val binding = PokemonItemBinding.bind(item)
        var isPokemonSaved: Boolean = false

        @SuppressLint("SetTextI18n")
        fun bind(pokemon: Pokemon) = with(binding) {
            setStartState(pokemon)
            pokemonId.text = "# ${pokemon.id}"
            pokemonHeight.text = "Height: ${pokemon.height}"
            pokemonWeight.text = "Weight: ${pokemon.weight}"
            pokemonName.text = pokemon.name.uppercase()

            favBtn.setOnClickListener {
                setState(pokemon)
            }

            val link = pokemon.sprites.otherSprites.imageUrl.link
            Glide.with(pokemonImage)
                .load(link)
                .circleCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(pokemonImage)
        }

        private fun setStartState(pokemon: Pokemon) {
            isPokemonSaved = delegate?.checkFavouriteState(pokemon)!!
            binding.favBtn.setImageResource(
                if(isPokemonSaved)
                    R.drawable.ic_baseline_favorite_24
                else
                    R.drawable.ic_baseline_favorite_border_24)


        /*isPokemonSaved = delegate?.checkFavouriteState(pokemon)!!
            if(isPokemonSaved)
                binding.favBtn.setImageResource(R.drawable.ic_baseline_favorite_24)
            else
                binding.favBtn.setImageResource(R.drawable.ic_baseline_favorite_border_24)*/
        }

        private fun setState(pokemon: Pokemon) {
            if(!isPokemonSaved) {
                binding.favBtn.setImageResource(R.drawable.ic_baseline_favorite_24)
                delegate?.addFavourite(pokemon)
            } else {
                binding.favBtn.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                delegate?.deleteFavourite(pokemon)
            }
            isPokemonSaved = !isPokemonSaved
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item, parent, false)
        return PokemonHolder(view, delegate)
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

    fun attachDelegate(delegate: FavouriteStateDelegate) {
        this.delegate = delegate
    }
}