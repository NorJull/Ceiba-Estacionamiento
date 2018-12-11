package com.vamosaprogramar.CeibaEstacionamiento.utility;

import static com.vamosaprogramar.CeibaEstacionamiento.GeneralConstants.CAR;
import static com.vamosaprogramar.CeibaEstacionamiento.GeneralConstants.CAR_DAY_VALUE;
import static com.vamosaprogramar.CeibaEstacionamiento.GeneralConstants.CAR_HOUR_VALUE;
import static com.vamosaprogramar.CeibaEstacionamiento.GeneralConstants.HOURS_TO_BE_DAY;
import static com.vamosaprogramar.CeibaEstacionamiento.GeneralConstants.MOTORCYCLE_DAY_VALUE;
import static com.vamosaprogramar.CeibaEstacionamiento.GeneralConstants.MOTORCYCLE_HOUR_VALUE;
import static com.vamosaprogramar.CeibaEstacionamiento.GeneralConstants.VALUE_TO_PAY_EXCESSED_BY_CYLINDER;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Component;

import com.vamosaprogramar.CeibaEstacionamiento.GeneralConstants;

@Component
public class ParkingTicketUtilityImpl implements ParkingTickectUtility {

	@Override
	public boolean validateNumberOfConcurrentVehicles(String vehicleType, int numberConcurrentVehicles) {

		if ((vehicleType.equals(GeneralConstants.MOTORCYCLE)
				&& numberConcurrentVehicles >= GeneralConstants.MAX_NUMBER_CONCURRENT_MOTO)) {
			return false;
		}

		if ((vehicleType.equals(GeneralConstants.CAR)
				&& numberConcurrentVehicles >= GeneralConstants.MAX_NUMBER_CONCURRENT_CAR)) {
			return false;
		}

		return true;
	}

	@Override
	public boolean validatePlateStartWithA(String vehiclePlate) {
		if (vehiclePlate.startsWith("A")) {
			DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();

			if (dayOfWeek == DayOfWeek.SUNDAY || dayOfWeek == DayOfWeek.MONDAY)
				return true;
			else
				return false;

		}
		return true;
	}

	@Override
	public boolean validateMotoWithCylinderCapacityOver500(int vehicleCylinderCapacity) {

		if (vehicleCylinderCapacity > GeneralConstants.CYLINDER_ALLOWED_MOTO) {

			return true;
		}

		return false;
	}

	@Override
	public long hoursBetweenTwoDates(LocalDateTime startDate, LocalDateTime finishDate) {

		long hours = ChronoUnit.HOURS.between(startDate, finishDate);

		long minutes = ChronoUnit.MINUTES.between(startDate, finishDate);

		minutes = minutes - (hours * 60);

		if (minutes > 0)
			hours++;

		return hours;
	}

	@Override
	public double calculateTotalToPay(String vehicleType, long hours, int cylinderCapacity) {

		double totalToPay = 0;

		if (vehicleType.equals(CAR)) {
			// Camino para carros

			if (hours < HOURS_TO_BE_DAY) {
				totalToPay = hours * CAR_HOUR_VALUE;
			} else {
				long days = hours / 24;
				long hoursLeft = hours % 24;

				if (hoursLeft > HOURS_TO_BE_DAY) {
					days++;
					hoursLeft = hoursLeft - HOURS_TO_BE_DAY;
				}

				totalToPay = (days * CAR_DAY_VALUE) + (hoursLeft * CAR_HOUR_VALUE);
			}

		} else {
			// Camino para motos

			// Las motos que tengan un cilindraje mayor a 500 CC pagan 2000 de mas al valor
			// total.

			if (validateMotoWithCylinderCapacityOver500(cylinderCapacity)) {
				totalToPay = VALUE_TO_PAY_EXCESSED_BY_CYLINDER;
			}

			if (hours < HOURS_TO_BE_DAY) {
				totalToPay = totalToPay + hours * MOTORCYCLE_HOUR_VALUE;
			} else {
				long days = hours / 24;
				long hoursLeft = hours % 24;

				if (hoursLeft > HOURS_TO_BE_DAY) {
					days++;
					hoursLeft = hoursLeft - HOURS_TO_BE_DAY;
				}

				totalToPay = (days * MOTORCYCLE_DAY_VALUE) + (hoursLeft * MOTORCYCLE_HOUR_VALUE);
			}

		}

		return totalToPay;
	}

}
