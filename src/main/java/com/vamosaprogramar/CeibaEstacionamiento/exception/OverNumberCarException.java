package com.vamosaprogramar.CeibaEstacionamiento.exception;

public class OverNumberCarException extends Exception {

	public OverNumberCarException(){
		super("Se sobrepas� el n�mero de carros concurrentes parqueados");
	}
}
