package com.vamosaprogramar.CeibaEstacionamiento.exception;

public class OverNumberMotosException extends Exception {

	public OverNumberMotosException(){
		super("Se sobrepas� el n�mero de motos concurrentes parqueadas");
	}
}
