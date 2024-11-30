package com.ticlavilca.abigail.poketinder.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ticlavilca.abigail.poketinder.data.database.entities.MyPokemonEntity
import com.ticlavilca.abigail.poketinder.databinding.ItemPokemonSavedBinding

class MyPokemonsAdapater(val list: List<MyPokemonEntity>): RecyclerView.Adapter<MyPokemonsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPokemonsHolder {
        val view = ItemPokemonSavedBinding.inflate(LayoutInflater.from(parent.context),parent ,false)
        return MyPokemonsHolder(view.root)
    }

    override fun onBindViewHolder(holder: MyPokemonsHolder, position: Int) {
        val pokemon = list[position]
        holder.bind(pokemon)
    }

    override fun getItemCount(): Int = list.size
}
