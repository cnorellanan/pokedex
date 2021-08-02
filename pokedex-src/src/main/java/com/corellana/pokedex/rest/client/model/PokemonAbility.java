package com.corellana.pokedex.rest.client.model;

import java.io.Serializable;
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
public class PokemonAbility implements Serializable {
	
	private static final long serialVersionUID = 289008833506646162L;

	private Map<String, String> ability;
	
	@JsonProperty("is_hidden")
	private Boolean isHidden;
}
