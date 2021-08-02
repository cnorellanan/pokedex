package com.corellana.pokedex.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.corellana.pokedex.exceptions.PokedexException;
import com.corellana.pokedex.model.PokemonDetailsTO;
import com.corellana.pokedex.model.PokemonListTO;
import com.corellana.pokedex.rest.client.PokeApiClientService;
import com.corellana.pokedex.rest.client.model.Chain;
import com.corellana.pokedex.rest.client.model.EvolutionChain;
import com.corellana.pokedex.rest.client.model.FlavorText;
import com.corellana.pokedex.rest.client.model.PokemonAbility;
import com.corellana.pokedex.rest.client.model.PokemonDetails;
import com.corellana.pokedex.rest.client.model.PokemonInfo;
import com.corellana.pokedex.rest.client.model.PokemonList;
import com.corellana.pokedex.rest.client.model.PokemonType;
import com.corellana.pokedex.rest.client.model.Variety;
import com.corellana.pokedex.service.impl.PokedexServiceImpl;

@ExtendWith(MockitoExtension.class)
class PokedexServiceTest {
	
	@Mock
	private PokeApiClientService pokeApiClient;
	
	@InjectMocks
	private PokedexServiceImpl pokedexService;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void testGetPokemonList() throws PokedexException {
		String name = "test";
		String sprite = "http://test";
		String ability = "ability";
		String type = "normal";
		Integer id = 1;
		Integer weight = 100;
		
		when(pokeApiClient.getPokemonList(1, 0)).thenReturn(pokemonListOk(name));
		when(pokeApiClient.getPokemonInfo(name)).thenReturn(pokemonInfoOk(name, sprite, ability, type, id, weight));
		
		PokemonListTO result = pokedexService.getPokemonList(1, 0);
		
		System.out.println(result.toString());
		
		assertNotNull(result);
		assertNotNull(result.getResults());
		assertNotNull(result.getResults().get(0));
		
		assertEquals(1, result.getTotal());
		assertEquals(1, result.getResults().get(0).getId());
		assertEquals(sprite, result.getResults().get(0).getImage());
		assertEquals(name, result.getResults().get(0).getName());
		assertEquals(type, result.getResults().get(0).getType().get(0));
		assertEquals(weight, result.getResults().get(0).getWeight());
		assertEquals(ability, result.getResults().get(0).getAbilities().get(0).getName());
	}

	@Test
	void testGetPokemonDetails() throws PokedexException {
		Integer idOk = 1, idOkVariety = 2, idError = 3, idEvolution = 4;
		String evolution = "http://evolutions/1";
		String description = "description";
		String name = "test";
		String evolutionName = "evolution";
		String sprite = "http://test";
		String ability = "ability";
		String type = "normal";
		Integer weight = 100;
		Integer statusError = 404;
		
		when(pokeApiClient.getPokemonDetail(idOk)).thenReturn(pokemonDetailsOk(idOk, evolution, description, name, true));
		when(pokeApiClient.getPokemonDetail(idOkVariety)).thenReturn(pokemonDetailsOk(idOkVariety, evolution, description, name, false));
		when(pokeApiClient.getPokemonDetail(idError)).thenThrow(new PokedexException(statusError, "Not Found"));
		when(pokeApiClient.getPokemonInfo(name)).thenReturn(pokemonInfoOk(name, sprite, ability, type, idOk, weight));
		when(pokeApiClient.getPokemonInfo(evolutionName)).thenReturn(pokemonInfoOk(evolutionName, sprite, ability, type, idEvolution, weight));
		when(pokeApiClient.getEvolutionChain(Mockito.anyInt())).thenReturn(evolutionChainOk());
		
		PokemonDetailsTO resultOk = pokedexService.getPokemonDetails(idOk);
		
		assertNotNull(resultOk);
		assertNotNull(resultOk.getEvolutionChain());
		assertFalse(resultOk.getEvolutionChain().isEmpty());
		assertTrue(resultOk.getVarieties().isEmpty());
		
		assertEquals(idOk, resultOk.getId());
		assertEquals(description, resultOk.getDescription());
		assertEquals(name, resultOk.getName());
		assertEquals(name, resultOk.getEvolutionChain().get(0).getName());
		
		PokemonDetailsTO resultOkVariety = pokedexService.getPokemonDetails(idOkVariety);
		
		assertNotNull(resultOkVariety);
		assertNotNull(resultOkVariety.getEvolutionChain());
		assertFalse(resultOkVariety.getVarieties().isEmpty());
		
		assertEquals(name, resultOkVariety.getVarieties().get(0).getName());
		
		PokedexException ex = assertThrows(PokedexException.class, () -> {pokedexService.getPokemonDetails(idError);});
		assertEquals(statusError, ex.getCode());
	}
	
	private PokemonList pokemonListOk(String name) {
		PokemonList pokemonListOk = new PokemonList();
		pokemonListOk.setCount(1);
		Map<String, String> resultMapOk = new HashMap<String, String>();
		resultMapOk.put("name", name);
		List<Map<String, String>> resultListOk = new ArrayList<Map<String, String>>();
		resultListOk.add(resultMapOk);
		pokemonListOk.setResults(resultListOk);
		
		return pokemonListOk;
	}
	
	private PokemonInfo pokemonInfoOk(String name, String sprite, String ability, String type, int id, int weight) {
		PokemonInfo pokemonInfoOk = new PokemonInfo();
		pokemonInfoOk.setId(id);
		pokemonInfoOk.setName(name);
		pokemonInfoOk.setIsDefault(true);
		pokemonInfoOk.setWeight(weight);
		
		Map<String,Object> sprites = new HashMap<String, Object>();
		sprites.put("front_default", sprite);
		
		pokemonInfoOk.setSprites(sprites);
		
		PokemonAbility abilityOk = new PokemonAbility();
		Map<String, String> abilityMapOk = new HashMap<String, String>();
		abilityMapOk.put("name", ability);
		abilityOk.setAbility(abilityMapOk);
		abilityOk.setIsHidden(false);
		List<PokemonAbility> abilityListOk = new ArrayList<PokemonAbility>();
		abilityListOk.add(abilityOk);
		
		pokemonInfoOk.setAbilities(abilityListOk);
		
		PokemonType typeOk = new PokemonType();
		Map<String, String> typeMapOk = new HashMap<String, String>();
		typeMapOk.put("name", type);
		typeOk.setType(typeMapOk);
		List<PokemonType> typeListOk = new ArrayList<PokemonType>();
		typeListOk.add(typeOk);
		
		pokemonInfoOk.setTypes(typeListOk);
		return pokemonInfoOk;
	}
	
	private PokemonDetails pokemonDetailsOk(int id, String evolution, String description, String varietyName, boolean defaultVariety) {
		PokemonDetails pokemonDetailsOk = new PokemonDetails();
		pokemonDetailsOk.setId(id);
		
		Map<String, String> evolutionChainOk = new HashMap<String, String>();
		evolutionChainOk.put("url", evolution);
		pokemonDetailsOk.setEvolutionChain(evolutionChainOk);
		
		FlavorText textOk = new FlavorText();
		textOk.setText(description);
		Map<String, String> languageOk = new HashMap<String, String>();
		languageOk.put("name", "en");
		textOk.setLanguage(languageOk);
		List<FlavorText> textListOk = new ArrayList<FlavorText>();
		textListOk.add(textOk);
		pokemonDetailsOk.setFlavorTextEntries(textListOk);
		
		Variety varietyOk = new Variety();
		varietyOk.setIsDefault(defaultVariety);
		Map<String, String> pokemonVariety = new HashMap<String, String>();
		pokemonVariety.put("name", varietyName);
		varietyOk.setPokemon(pokemonVariety);
		List<Variety> pokemonVarietyList = new ArrayList<Variety>();
		pokemonVarietyList.add(varietyOk);
		pokemonDetailsOk.setVarieties(pokemonVarietyList);
		
		return pokemonDetailsOk;
	}
	
	private EvolutionChain evolutionChainOk() {
		EvolutionChain evolutionChainOk = new EvolutionChain();
		evolutionChainOk.setId(1);
		Chain chainOk = new Chain();
		Map<String, String> speciesOk = new HashMap<String, String>();
		speciesOk.put("name", "test");
		chainOk.setSpecies(speciesOk);
		Chain chainEvolutionOk = new Chain();
		Map<String, String> speciesEvolutionOk = new HashMap<String, String>();
		speciesEvolutionOk.put("name", "evolution");
		chainEvolutionOk.setSpecies(speciesEvolutionOk);
		List<Chain> chainListOk = new ArrayList<Chain>();
		chainListOk.add(chainEvolutionOk);
		chainOk.setEvolvesTo(chainListOk);
		evolutionChainOk.setChain(chainOk);
		
		return evolutionChainOk;
	}

}
