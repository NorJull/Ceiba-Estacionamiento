package com.vamosaprogramar.CeibaEstacionamiento;

public class GeneralConstants {

	// Indica que el vehiculo es una moto [C = Carro]
	public static final String CAR = "C";
	// Indica que el vehiculo es una moto [M = Moto]
	public static final String MOTORCYCLE = "M";
	// Indica que el tiquete de parqueo se registró [E =Entrada]
	public static final String TICKET_REGISTERED = "E";
	// Indica que se registro la salida del tiquete de parqueo [S = Salida]
	public static final String TICKET_CHECKOUT_REGISTERED = "S";
	//Maximo número de motos concurrentes
	public static final int MAX_NUMBER_CONCURRENT_MOTO = 10;
	//Maximo número de carros concurrentes
	public static final int MAX_NUMBER_CONCURRENT_CAR = 20;
	// Valor por hora para carros
	public static final double CAR_HOUR_VALUE = 1000.0;
	// Valor por día para carro
	public static final double CAR_DAY_VALUE = 8000.0;
	// Valor por hora para moto
	public static final double MOTORCYCLE_HOUR_VALUE = 500.0;
	// Valor por dia para moto
	public static final double MOTORCYCLE_DAY_VALUE = 4000.0;
	// Cilindraje exedente para motos
	public static final int CYLINDER_ALLOWED_MOTO = 500;
	// Las motos que tengan un cilindraje mayor al de la contante CYLINDER_ALLOWED_MOTO
	public static final double VALUE_TO_PAY_EXCESSED_BY_CYLINDER = 2000.0;
	//Número de horas para empezar a cobrar por día
	public static final int HOURS_TO_BE_DAY = 9;
}
