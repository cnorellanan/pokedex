package com.corellana.pokedex.rest.client;

import com.corellana.pokedex.exceptions.PokedexException;
import com.corellana.pokedex.rest.client.model.EvolutionChain;
import com.corellana.pokedex.rest.client.model.PokemonDetails;
import com.corellana.pokedex.rest.client.model.PokemonInfo;
import com.corellana.pokedex.rest.client.model.PokemonList;

public interface PokeApiClientService {
	
	PokemonList getPokemonList(Integer limit, Integer offset) throws PokedexException;
	
	PokemonInfo getPokemonInfo(Integer id) throws PokedexException;
	
	PokemonInfo getPokemonInfo(String name) throws PokedexException;
	
	PokemonDetails getPokemonDetail(Integer id) throws PokedexException;
	
	EvolutionChain getEvolutionChain(Integer id) throws PokedexException;

}
