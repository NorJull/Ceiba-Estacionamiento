package com.vamosaprogramar.CeibaEstacionamiento.entity;

import static com.vamosaprogramar.CeibaEstacionamiento.GeneralConstants.CAR_DAY_VALUE;
import static com.vamosaprogramar.CeibaEstacionamiento.GeneralConstants.CAR_HOUR_VALUE;
import static com.vamosaprogramar.CeibaEstacionamiento.GeneralConstants.HOURS_TO_BE_DAY;
import static com.vamosaprogramar.CeibaEstacionamiento.GeneralConstants.MAX_NUMBER_CONCURRENT_CAR;

import javax.persistence.Entity;

@Entity
public class CarParkingTicket extends ParkingTicket {

	@Override
	public boolean validateConcurrentVehicles(int numberConcurrentVehicles) {

		if (numberConcurrentVehicles >= MAX_NUMBER_CONCURRENT_CAR)
			return true;

		return false;
	}

	@Override
	public double calculateTotalToPay(long hours) {
		double totalToPay = 0;

		if (hours < HOURS_TO_BE_DAY) {
			totalToPay = hours * CAR_HOUR_VALUE;

		} else if (hours >= HOURS_TO_BE_DAY && hours <= 24) {
			totalToPay = CAR_DAY_VALUE;
		} else {
			long days = hours / 24;
			long hoursLeft = hours % 24;

			if (hoursLeft > HOURS_TO_BE_DAY) {
				days++;
				hoursLeft = hoursLeft - HOURS_TO_BE_DAY;
			}

			totalToPay = (days * CAR_DAY_VALUE) + (hoursLeft * CAR_HOUR_VALUE);
		}

		return totalToPay;
	}
}
