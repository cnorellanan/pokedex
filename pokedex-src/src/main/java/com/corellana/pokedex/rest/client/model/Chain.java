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
public class Chain implements Serializable {
	
	private static final long serialVersionUID = 4801441003701567180L;

	@JsonProperty("evolves_to")
	private List<Chain> evolvesTo;
	
	private Map<String, String> species;
}
