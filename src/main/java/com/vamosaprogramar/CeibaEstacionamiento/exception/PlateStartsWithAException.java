package com.vamosaprogramar.CeibaEstacionamiento.exception;

public class PlateStartsWithAException extends Exception {

	public PlateStartsWithAException(){
		super("No esta autorizado a ingresar [Las placas que inician por la letra \"A\" solo pueden ingresar al parqueadero los días Domingo y Lunes]");
	}
}
