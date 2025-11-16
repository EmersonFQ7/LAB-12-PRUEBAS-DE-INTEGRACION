package com.tecsup.petclinic.exceptions;

/**
 * Excepción personalizada para cuando no se encuentra un Veterinario
 * 
 * @author jgomezm
 *
 */
public class VetNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VetNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}