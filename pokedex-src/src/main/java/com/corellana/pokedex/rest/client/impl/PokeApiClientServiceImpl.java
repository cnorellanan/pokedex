package com.corellana.pokedex.rest.client.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.corellana.pokedex.exceptions.PokedexException;
import com.corellana.pokedex.rest.client.PokeApiClientService;
import com.corellana.pokedex.rest.client.model.EvolutionChain;
import com.corellana.pokedex.rest.client.model.PokemonDetails;
import com.corellana.pokedex.rest.client.model.PokemonInfo;
import com.corellana.pokedex.rest.client.model.PokemonList;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PokeApiClientServiceImpl implements PokeApiClientService {

	@Value("${pokeapi.url.pokemon}")
	private String urlPokemon;

	@Value("${pokeapi.url.species}")
	private String urlSpecies;

	@Value("${pokeapi.url.evolution}")
	private String urlEvolution;

	private static final String PATH_ID = "/{Id}";
	private static final String PATH_NAME = "/{Name}";

	private final RestTemplate restTemplate;

	@Autowired
	public PokeApiClientServiceImpl(final RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	@Cacheable(value = "listCache")
	public PokemonList getPokemonList(Integer limit, Integer offset) throws PokedexException {

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlSpecies)
				.queryParam("limit", limit)
				.queryParam("offset", offset);

		log.info("Calling: " + builder.toUriString());
		try {
			ResponseEntity<PokemonList> response = restTemplate.getForEntity(builder.toUriString(), PokemonList.class);
			return response.getBody();
		} catch(HttpClientErrorException e) {
			log.error("Api response error: " + e.getRawStatusCode(), e);
			throw new PokedexException(e.getRawStatusCode(), "Api Error");
		} 
	}

	@Override
	@Cacheable(value = "infoCache")
	public PokemonInfo getPokemonInfo(Integer id) throws PokedexException {

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlPokemon)
				.path(PATH_ID);

		log.info("Calling: " + builder.build(id).toString());
		try {
			ResponseEntity<PokemonInfo> response = restTemplate.getForEntity(builder.build(id).toString(), PokemonInfo.class);
			return response.getBody();
		} catch(HttpClientErrorException e) {
			log.error("Api response error: " + e.getRawStatusCode(), e);
			throw new PokedexException(e.getRawStatusCode(), "Api Error");
		}
	}

	@Override
	@Cacheable(value = "infoCache")
	public PokemonInfo getPokemonInfo(String name) throws PokedexException {

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlPokemon)
				.path(PATH_NAME);

		log.info("Calling: " + builder.build(name).toString());
		try {
			ResponseEntity<PokemonInfo> response = restTemplate.getForEntity(builder.build(name).toString(), PokemonInfo.class);
			return response.getBody();
		} catch(HttpClientErrorException e) {
			log.error("Api response error: " + e.getRawStatusCode(), e);
			throw new PokedexException(e.getRawStatusCode(), "Api Error");
		}
	}

	@Override
	@Cacheable(value = "detailsCache")
	public PokemonDetails getPokemonDetail(Integer id) throws PokedexException {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlSpecies)
				.path(PATH_ID);

		log.info("Calling: " + builder.build(id).toString());
		try {
			ResponseEntity<PokemonDetails> response = restTemplate.getForEntity(builder.build(id).toString(), PokemonDetails.class);
			return response.getBody();
		} catch(HttpClientErrorException e) {
			log.error("Api response error: " + e.getRawStatusCode(), e);
			throw new PokedexException(e.getRawStatusCode(), "Api Error");
		}
	}

	@Override
	@Cacheable(value = "evolutionCache")
	public EvolutionChain getEvolutionChain(Integer id) throws PokedexException {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlEvolution)
				.path(PATH_ID);

		log.info("Calling: " + builder.build(id).toString());
		try {
			ResponseEntity<EvolutionChain> response = restTemplate.getForEntity(builder.build(id).toString(), EvolutionChain.class);
			return response.getBody();
		} catch(HttpClientErrorException e) {
			log.error("Api response error: " + e.getRawStatusCode(), e);
			throw new PokedexException(e.getRawStatusCode(), "Api Error");
		}
	}

}
