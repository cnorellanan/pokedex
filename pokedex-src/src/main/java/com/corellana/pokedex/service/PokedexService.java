package com.corellana.pokedex.service;

import com.corellana.pokedex.exceptions.PokedexException;
import com.corellana.pokedex.model.PokemonDetailsTO;
import com.corellana.pokedex.model.PokemonListTO;

public interface PokedexService {

	PokemonListTO getPokemonList(Integer limit, Integer offset) throws PokedexException;
	
	PokemonDetailsTO getPokemonDetails(Integer id) throws PokedexException;
	
}
