package com.vamosaprogramar.CeibaEstacionamiento.exception;

public class OverNumberVehiclesException extends Exception{

	public OverNumberVehiclesException(){
		super("Se sobrepasó el número de vehiculos concurrentes parqueados");
	}
}
