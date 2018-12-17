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

	private LocalDate localDate = LocalDate.now();

	@Override
	public void setLocalDate(LocalDate localDate) {
		this.localDate = localDate;
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



}
