package com.corellana.pokedex.rest.client.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PokemonDetails implements Serializable {
	
	private static final long serialVersionUID = -2575107512704713381L;

	@JsonProperty("evolution_chain")
	private Map<String,String> evolutionChain;
	
	@JsonProperty("flavor_text_entries")
	private List<FlavorText> flavorTextEntries;
	
	private Integer id;
	
	private List<Variety> varieties;
	
}
