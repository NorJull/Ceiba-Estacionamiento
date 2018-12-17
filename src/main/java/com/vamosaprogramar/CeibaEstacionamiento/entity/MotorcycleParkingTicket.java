package com.vamosaprogramar.CeibaEstacionamiento.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.vamosaprogramar.CeibaEstacionamiento.GeneralConstants;

import static com.vamosaprogramar.CeibaEstacionamiento.GeneralConstants.MOTORCYCLE;
import static com.vamosaprogramar.CeibaEstacionamiento.GeneralConstants.MOTORCYCLE_DAY_VALUE;
import static com.vamosaprogramar.CeibaEstacionamiento.GeneralConstants.MOTORCYCLE_HOUR_VALUE;
import static com.vamosaprogramar.CeibaEstacionamiento.GeneralConstants.VALUE_TO_PAY_EXCESSED_BY_CYLINDER;
import static com.vamosaprogramar.CeibaEstacionamiento.GeneralConstants.HOURS_TO_BE_DAY;
import static com.vamosaprogramar.CeibaEstacionamiento.GeneralConstants.MAX_NUMBER_CONCURRENT_MOTO;

@Entity
public class MotorcycleParkingTicket extends ParkingTicket {

	@Override
	public boolean validateConcurrentVehicles(int numberConcurrentVehicles) {

		if (numberConcurrentVehicles >= MAX_NUMBER_CONCURRENT_MOTO)
			return true;

		return false;
	}

	@Override
	public double calculateTotalToPay(long hours) {
		double totalToPay = 0;

		if (vehicleCylinderCapacity > GeneralConstants.CYLINDER_ALLOWED_MOTO) {
			totalToPay = VALUE_TO_PAY_EXCESSED_BY_CYLINDER;

		}

		if (hours < HOURS_TO_BE_DAY) {
			totalToPay = totalToPay + hours * MOTORCYCLE_HOUR_VALUE;

		} else if (hours >= HOURS_TO_BE_DAY && hours <= 24) {

			totalToPay = totalToPay + MOTORCYCLE_DAY_VALUE;

		} else {

			long days = hours / 24;
			long hoursLeft = hours % 24;

			if (hoursLeft > HOURS_TO_BE_DAY) {
				days++;
				hoursLeft = hoursLeft - HOURS_TO_BE_DAY;
			}

			totalToPay = totalToPay + (days * MOTORCYCLE_DAY_VALUE) + (hoursLeft * MOTORCYCLE_HOUR_VALUE);
		}

		return totalToPay;
	}

}
