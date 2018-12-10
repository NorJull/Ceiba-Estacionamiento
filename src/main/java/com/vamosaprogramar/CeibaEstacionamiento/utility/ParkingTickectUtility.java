package com.vamosaprogramar.CeibaEstacionamiento.utility;

public interface ParkingTickectUtility {

	public  boolean validateNumberOfConcurrentVehicles(String vehicleType, int numberConcurrentVehicles);

	boolean validatePlateStartWithA(String vehiclePlate);
}
