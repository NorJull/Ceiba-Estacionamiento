package com.vamosaprogramar.CeibaEstacionamiento.utility;


import java.time.DayOfWeek;
import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.vamosaprogramar.CeibaEstacionamiento.GeneralConstants;
@Component
public class ParkingTicketUtilityImpl implements ParkingTickectUtility{

;
	@Override
	public  boolean validateNumberOfConcurrentVehicles(String vehicleType, int numberConcurrentVehicles) {
		
		if((vehicleType.equals(GeneralConstants.MOTORCYCLE) &&  
				numberConcurrentVehicles == GeneralConstants.MAX_NUMBER_CONCURRENT_MOTO)) {
			return false;
		}
		
		if((vehicleType.equals(GeneralConstants.CAR) &&  
				numberConcurrentVehicles == GeneralConstants.MAX_NUMBER_CONCURRENT_CAR)) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public boolean validatePlateStartWithA(String vehiclePlate) {
		if(vehiclePlate.startsWith("A")) {
			DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
			return (dayOfWeek != DayOfWeek.SUNDAY) || (dayOfWeek != DayOfWeek.MONDAY); 
			
		}
		return true;
	}
	
}
