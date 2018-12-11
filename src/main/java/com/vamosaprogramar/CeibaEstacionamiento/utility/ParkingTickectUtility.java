package com.vamosaprogramar.CeibaEstacionamiento.utility;

import java.time.LocalDateTime;

public interface ParkingTickectUtility {

	public  boolean validateNumberOfConcurrentVehicles(String vehicleType, int numberConcurrentVehicles);

	boolean validatePlateStartWithA(String vehiclePlate);

	long hoursBetweenTwoDates(LocalDateTime startDate, LocalDateTime finishDate);

	double calculateTotalToPay(String vehicleType, long hours, int cylinderCapacity);

	boolean validateMotoWithCylinderCapacityOver500(int vehicleCylinderCapacity);
}
