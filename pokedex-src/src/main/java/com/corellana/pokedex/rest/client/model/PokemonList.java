package com.corellana.pokedex.rest.client.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PokemonList implements Serializable {
	
	private static final long serialVersionUID = 2524855291537936744L;

	private List<Map<String, String>> results;
	
	private Integer count; 
}
