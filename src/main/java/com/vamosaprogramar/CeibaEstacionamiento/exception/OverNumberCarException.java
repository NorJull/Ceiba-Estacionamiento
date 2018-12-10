package com.vamosaprogramar.CeibaEstacionamiento.exception;

public class OverNumberCarException extends Exception {

	public OverNumberCarException(){
		super("Se sobrepasó el número de carros concurrentes parqueados");
	}
}
