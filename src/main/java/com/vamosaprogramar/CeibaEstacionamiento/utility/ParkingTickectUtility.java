package com.vamosaprogramar.CeibaEstacionamiento.utility;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface ParkingTickectUtility {

	
	long hoursBetweenTwoDates(LocalDateTime startDate, LocalDateTime finishDate);

	void setLocalDate(LocalDate localDate);
}
