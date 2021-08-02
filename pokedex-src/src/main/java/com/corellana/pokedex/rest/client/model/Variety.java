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
public class Variety implements Serializable {

	private static final long serialVersionUID = 1566035684687539336L;

	@JsonProperty("is_default")
	private Boolean isDefault;
	
	private Map<String, String> pokemon;
}
