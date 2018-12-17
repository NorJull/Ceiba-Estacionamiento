package com.vamosaprogramar.CeibaEstacionamiento.unit;

import static com.vamosaprogramar.CeibaEstacionamiento.GeneralConstants.CAR;
import static com.vamosaprogramar.CeibaEstacionamiento.GeneralConstants.MOTORCYCLE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.vamosaprogramar.CeibaEstacionamiento.utility.ParkingTickectUtility;

import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
public class ParkingTicketUtilityTest {

	@Autowired
	ParkingTickectUtility parkingTickectUtility;
	
	
	@Test
	public void hoursBetweenTwoDates_5minDuration_Then1hours() {
		
		LocalDateTime localDateTime = LocalDateTime.now();
			
		long actual = parkingTickectUtility
				.hoursBetweenTwoDates(localDateTime, localDateTime.plusMinutes(5));
		
		assertEquals(1, actual);
		
	}
	
	@Test
	public void hoursBetweenTwoDates_3hoursDuration_Then3hours() {
		
		LocalDateTime localDateTime = LocalDateTime.now();
			
		long actual = parkingTickectUtility
				.hoursBetweenTwoDates
				(localDateTime, localDateTime.plusHours(3));
		
		assertEquals(3, actual);
		
	}
	
	@Test
	public void hoursBetweenTwoDates_3hoursAnd5MinDuration_Then4hours() {
		
		LocalDateTime localDateTime = LocalDateTime.now();
			
		long actual = parkingTickectUtility
				.hoursBetweenTwoDates
				(localDateTime, localDateTime.plusHours(3).plusMinutes(5));
		
		assertEquals(4, actual);
		
	}
	

	@Test
	public void hoursBetweenTwoDates_1dayDuration_Then24hours() {
		
		LocalDateTime localDateTime = LocalDateTime.now();
			
		long actual = parkingTickectUtility
				.hoursBetweenTwoDates
				(localDateTime, localDateTime.plusDays(1));
		
		assertEquals(24, actual);
		
	}
	
	@Test
	public void hoursBetweenTwoDates_1dayAnd5minDuration_Then25hours() {
		
		LocalDateTime localDateTime = LocalDateTime.now();
			
		long actual = parkingTickectUtility
				.hoursBetweenTwoDates
				(localDateTime, localDateTime.plusDays(1).plusMinutes(5));
		
		assertEquals(25, actual);
		
	}
	
	
}
