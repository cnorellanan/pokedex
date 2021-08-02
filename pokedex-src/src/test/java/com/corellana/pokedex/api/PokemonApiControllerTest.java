package com.corellana.pokedex.api;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.corellana.pokedex.exceptions.PokedexException;
import com.corellana.pokedex.model.PokemonDetailsTO;
import com.corellana.pokedex.model.PokemonListTO;
import com.corellana.pokedex.model.PokemonTO;
import com.corellana.pokedex.service.PokedexService;

@ExtendWith(MockitoExtension.class)
class PokemonApiControllerTest {
	
	@Mock
	private PokedexService pokedexService;
	
	@InjectMocks
	private PokemonApiController pokemonApiController;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void testPokedex() throws PokedexException {
		PokemonListTO pokemonListOk = new PokemonListTO();
		for(int i = 1; i < 20; i++) {
			PokemonTO pokemon = new PokemonTO();
			pokemon.setId(i);
			pokemonListOk.addResultsItem(pokemon);
		}
		
		when(pokedexService.getPokemonList(20, 0)).thenReturn(pokemonListOk);
		when(pokedexService.getPokemonList(100, 100)).thenThrow(new PokedexException(500, "Error"));
		
		ResponseEntity<PokemonListTO> resultOk = pokemonApiController.pokedex(20, 0);
		assertEquals(HttpStatus.OK, resultOk.getStatusCode());
		assertEquals(pokemonListOk, resultOk.getBody());
		
		PokedexException ex = assertThrows(PokedexException.class, () -> {pokemonApiController.pokedex(100, 100);});
		assertEquals(500, ex.getCode());
	}

	@Test
	void testPokemon() throws PokedexException {
		PokemonDetailsTO pokemonDetailsOk = new PokemonDetailsTO();
		pokemonDetailsOk.setId(1);
		
		when(pokedexService.getPokemonDetails(1)).thenReturn(pokemonDetailsOk);
		when(pokedexService.getPokemonDetails(0)).thenThrow(new PokedexException(500, "Error"));
		
		ResponseEntity<PokemonDetailsTO> resultOk = pokemonApiController.pokemon(1);
		assertEquals(HttpStatus.OK, resultOk.getStatusCode());
		assertEquals(pokemonDetailsOk, resultOk.getBody());
		
		PokedexException ex = assertThrows(PokedexException.class, () -> {pokemonApiController.pokemon(0);});
		assertEquals(500, ex.getCode());
	}

}
