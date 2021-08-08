package com.b2w.starwars.exception;

public class StarWarsException extends RuntimeException {

	private static final long serialVersionUID = 1468573725215096436L;

	public StarWarsException(String exception) {
		super(exception);
	}
}
