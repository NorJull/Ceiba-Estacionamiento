package com.vamosaprogramar.CeibaEstacionamiento.exception;

public class OverNumberVehiclesException extends Exception{

	public OverNumberVehiclesException(){
		super("Se sobrepas� el n�mero de vehiculos concurrentes parqueados");
	}
}
