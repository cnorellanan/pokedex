package com.corellana.pokedex.api;

import com.corellana.pokedex.model.PokemonListTO;
import com.corellana.pokedex.exceptions.PokedexException;
import com.corellana.pokedex.model.PokemonDetailsTO;
import com.corellana.pokedex.service.PokedexService;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
public class PokemonApiController implements PokemonApi {

	private final PokedexService pokedexService;

	@Autowired
	public PokemonApiController(PokedexService pokedexService) {
		this.pokedexService = pokedexService;
	}

	@Override
	public ResponseEntity<PokemonListTO> pokedex(@Valid Integer limit, @Valid Integer offset) throws PokedexException {
		PokemonListTO pokemonList = pokedexService.getPokemonList(limit, offset);
		return ResponseEntity.ok(pokemonList);
	}

	@Override
	public ResponseEntity<PokemonDetailsTO> pokemon(Integer id) throws PokedexException {

		PokemonDetailsTO pokemonDetails = pokedexService.getPokemonDetails(id);
		return ResponseEntity.ok(pokemonDetails);
	}

}
