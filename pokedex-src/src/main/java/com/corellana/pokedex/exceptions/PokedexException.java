package com.corellana.pokedex.exceptions;

import lombok.Getter;

@Getter
public class PokedexException extends Exception {

	private static final long serialVersionUID = 3536884571107217080L;
	
	private final int code;
	
	public PokedexException (int code, String msg) {
        super(msg);
        this.code = code;
    }
}
