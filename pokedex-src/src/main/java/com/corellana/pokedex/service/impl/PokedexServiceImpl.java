package com.corellana.pokedex.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corellana.pokedex.exceptions.PokedexException;
import com.corellana.pokedex.model.AbilityTO;
import com.corellana.pokedex.model.PokemonDetailsTO;
import com.corellana.pokedex.model.PokemonListTO;
import com.corellana.pokedex.model.PokemonTO;
import com.corellana.pokedex.rest.client.PokeApiClientService;
import com.corellana.pokedex.rest.client.model.Chain;
import com.corellana.pokedex.rest.client.model.EvolutionChain;
import com.corellana.pokedex.rest.client.model.FlavorText;
import com.corellana.pokedex.rest.client.model.PokemonDetails;
import com.corellana.pokedex.rest.client.model.PokemonInfo;
import com.corellana.pokedex.rest.client.model.PokemonList;
import com.corellana.pokedex.service.PokedexService;

@Service
public class PokedexServiceImpl implements PokedexService {

	private final PokeApiClientService pokeApiClient;

	@Autowired
	public PokedexServiceImpl(final PokeApiClientService pokeApiClient) {
		this.pokeApiClient = pokeApiClient;
	}

	@Override
	public PokemonListTO getPokemonList(Integer limit, Integer offset) throws PokedexException {
		PokemonList pokemonList = pokeApiClient.getPokemonList(limit, offset);
		return processPokemonList(pokemonList, limit, offset);
	}

	@Override
	public PokemonDetailsTO getPokemonDetails(Integer id) throws PokedexException {
		PokemonDetails pokemonDetails = pokeApiClient.getPokemonDetail(id);
		return processPokemonDetails(pokemonDetails);	
	}

	private PokemonListTO processPokemonList(PokemonList pokemonList, Integer limit, Integer offset) throws PokedexException {
		PokemonListTO response = new PokemonListTO();
		response.setLimit(limit);
		response.setOffset(offset);
		response.setTotal(pokemonList.getCount());
		List<Map<String, String>> result = pokemonList.getResults();
		if(result != null && !result.isEmpty()) {
			List<String> pokemonNames = result.stream()
					.map(r -> r.get("name"))
					.collect(Collectors.toList());
			List<PokemonTO> pokemonResultList = getPokemonInfoListByNames(pokemonNames);
			response.setResults(pokemonResultList);
		}
		return response;
	}

	private PokemonTO processPokemonInfo(PokemonInfo pokemon) {
		PokemonTO pokemonResult = new PokemonTO();
		pokemonResult.setId(pokemon.getId());
		pokemonResult.setName(pokemon.getName());
		pokemonResult.setWeight(pokemon.getWeight());

		pokemonResult.setImage((String) pokemon.getSprites().get("front_default"));
		pokemonResult.setType(pokemon.getTypes().stream()
				.map(t -> t.getType().get("name"))
				.collect(Collectors.toList()));
		pokemonResult.setAbilities(pokemon.getAbilities().stream()
				.map(a -> new AbilityTO(a.getAbility().get("name"), a.getIsHidden()))
				.collect(Collectors.toList()));
		return pokemonResult;
	}

	private PokemonDetailsTO processPokemonDetails(PokemonDetails pokemonDetails) throws PokedexException {
		// Get Varieties
		List<String> varietiesNames = pokemonDetails.getVarieties().stream()
				.filter(v -> !v.getIsDefault())
				.map(v -> v.getPokemon().get("name"))
				.collect(Collectors.toList());

		List<PokemonTO> pokemonVarieties = getPokemonInfoListByNames(varietiesNames);

		// Get Evolution Chain
		Integer evolutionChainId = extractIdFromUrl(pokemonDetails.getEvolutionChain().get("url"));
		List<String> evolutionNames = getEvolutionNames(evolutionChainId);

		List<PokemonTO> evolutions = getPokemonInfoListByNames(evolutionNames);

		// Get Name
		Optional<PokemonTO> opPokemon = evolutions.stream().filter(e -> e.getId().equals(pokemonDetails.getId())).findFirst();
		String name = opPokemon.isPresent() ? opPokemon.get().getName() : "";

		// Get Description
		String description = pokemonDetails.getFlavorTextEntries().stream()
				.filter(f -> f.getLanguage().get("name").equals("en"))
				.reduce((first, second) -> second).orElse(new FlavorText()).getText();

		PokemonDetailsTO pokemonDetailsTO = new PokemonDetailsTO();
		pokemonDetailsTO.setId(pokemonDetails.getId());
		pokemonDetailsTO.setName(name);
		pokemonDetailsTO.setVarieties(pokemonVarieties);
		pokemonDetailsTO.setEvolutionChain(evolutions);
		pokemonDetailsTO.setDescription(description);

		return pokemonDetailsTO;
	}

	private List<PokemonTO> getPokemonInfoListByNames(List<String> names) throws PokedexException {
		List<PokemonTO> pokemonInfoList = new ArrayList<>();
		for(String name : names) {
			PokemonInfo pokemon = pokeApiClient.getPokemonInfo(name);
			pokemonInfoList.add(processPokemonInfo(pokemon));
		}
		Collections.sort(pokemonInfoList, Comparator.comparing(PokemonTO::getId));
		return pokemonInfoList;
	}

	private List<String> getEvolutionNames(Integer evolutionChainId) throws PokedexException {
		EvolutionChain evolutionChain = pokeApiClient.getEvolutionChain(evolutionChainId);
		return extractNamesFromChain(evolutionChain.getChain());
	}

	private List<String> extractNamesFromChain(Chain chain) {
		List<String> names = new ArrayList<>();
		if(chain.getEvolvesTo() != null && !chain.getEvolvesTo().isEmpty()) {
			chain.getEvolvesTo().forEach(c -> names.addAll(extractNamesFromChain(c)));
		}
		names.add(chain.getSpecies().get("name"));
		return names;
	}

	private Integer extractIdFromUrl(String url) {
		String[] urlSplit = url.split("/");
		String stringId = urlSplit[urlSplit.length - 1];
		return Integer.valueOf(stringId);
	}

}
