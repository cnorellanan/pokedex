package com.corellana.pokedex.rest.client.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EvolutionChain implements Serializable {
	
	private static final long serialVersionUID = -6365564769819174985L;

	private Chain chain;
	
	private Integer id;
}
