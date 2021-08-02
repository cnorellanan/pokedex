package com.corellana.pokedex.rest.client.model;

import java.io.Serializable;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PokemonType implements Serializable {
	
	private static final long serialVersionUID = -2035907751286655360L;
	
	private Map<String, String> type;
	
}
