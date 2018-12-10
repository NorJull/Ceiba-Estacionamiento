package com.vamosaprogramar.CeibaEstacionamiento.exception;

public class OverNumberMotosException extends Exception {

	public OverNumberMotosException(){
		super("Se sobrepasó el número de motos concurrentes parqueadas");
	}
}
