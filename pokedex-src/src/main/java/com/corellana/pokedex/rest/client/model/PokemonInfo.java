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
public class PokemonInfo implements Serializable {

	private static final long serialVersionUID = 528518792171984602L;

	private List<PokemonAbility> abilities;
	
	private Integer id;
	
	private String name;
	
	private Map<String, Object> sprites;
	
	private List<PokemonType> types;
	
	private Integer weight;
	
	@JsonProperty("is_default")
	private Boolean isDefault;

}
