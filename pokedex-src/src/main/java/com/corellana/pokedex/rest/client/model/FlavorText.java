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
public class FlavorText implements Serializable {
	
	private static final long serialVersionUID = 1998674858217062851L;

	@JsonProperty("flavor_text")
	private String text;
	
	private Map<String, String> language;
	
	private Map<String, String> version;
	
}
