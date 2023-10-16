package com.restrao.items.exceptions;

public class ItemIdNotFound extends Exception
{

	private static final long serialVersionUID = 1L;

	public ItemIdNotFound() {
		super();
	}
	
	public ItemIdNotFound(String message) {
		super(message);
	}
}
