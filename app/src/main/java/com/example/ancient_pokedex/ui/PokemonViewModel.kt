package com.example.ancient_pokedex.ui

import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.ancient_pokedex.paging.PokemonPagingSource
import com.example.ancient_pokedex.interfaces.PokemonService
import com.example.ancient_pokedex.models.EggGroup
import com.example.ancient_pokedex.models.PokemonData
import com.example.ancient_pokedex.models.PokemonSpeciesData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel()
class PokemonViewModel
@Inject constructor(private val pokemonService: PokemonService): ViewModel(){
    val listData = Pager(PagingConfig(pageSize = 10)){
        PokemonPagingSource(pokemonService)
    }.flow
    var positionNum : String? = null
    var clickedPokemonData : PokemonData? = null
    var clickedPokemonSpeciesData : PokemonSpeciesData? = null
    val eggGroup : Array<EggGroup>? = clickedPokemonSpeciesData?.eggGroups?.toTypedArray()


    suspend fun getPokemonData(pokemonID : Int) {
        clickedPokemonData = pokemonService.getPokemonData(pokemonID.toString()).body()
    }

    suspend fun getPokemonSpeciesData(pokemonID : Int) {
        clickedPokemonSpeciesData = pokemonService.getPokemonSpeciesData(pokemonID.toString()).body()
    }

}