package com.vamosaprogramar.CeibaEstacionamiento.exception;

public class EmptyPlateException extends Exception {

	public EmptyPlateException() {
		super("El valor de la placa no puede ser vacío.");
	}
}
