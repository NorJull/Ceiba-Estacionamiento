package com.vamosaprogramar.CeibaEstacionamiento.unit;

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
import static org.mockito.Mockito.mock;
import static com.vamosaprogramar.CeibaEstacionamiento.GeneralConstants.CAR;
import static com.vamosaprogramar.CeibaEstacionamiento.GeneralConstants.MOTORCYCLE;

import com.vamosaprogramar.CeibaEstacionamiento.utility.ParkingTickectUtility;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
public class ParkingTicketUtilityTest {

	@Autowired
	ParkingTickectUtility parkingTickectUtility;
	

	@Test
	public void validateNumberOfConcurrentVehiclesForMotos_thenTrue() {
		String vehicleType = MOTORCYCLE;
		int    numberConcurrentVehicles = 6;
		
		boolean actual = parkingTickectUtility
				.validateNumberOfConcurrentVehicles
				(vehicleType, numberConcurrentVehicles);
	
	    assertTrue(actual);
	}
	
	@Test
	public void validateNumberOfConcurrentVehiclesForMotos_thenFalse() {
		String vehicleType = MOTORCYCLE;
		int    numberConcurrentVehicles = 12;
		
		boolean actual = parkingTickectUtility
				.validateNumberOfConcurrentVehicles
				(vehicleType, numberConcurrentVehicles);
	
	    assertFalse(actual);
	}
	
	@Test
	public void validateNumberOfConcurrentVehiclesForCars_thenTrue() {
		String vehicleType = CAR;
		int    numberConcurrentVehicles = 6;
		
		boolean actual = parkingTickectUtility
				.validateNumberOfConcurrentVehicles
				(vehicleType, numberConcurrentVehicles);
	
	    assertTrue(actual);
	}
	
	@Test
	public void validateNumberOfConcurrentVehiclesForCars_thenFalse() {
		String vehicleType = CAR;
		int    numberConcurrentVehicles = 22;
		
		boolean actual = parkingTickectUtility
				.validateNumberOfConcurrentVehicles
				(vehicleType, numberConcurrentVehicles);
	
	    assertFalse(actual);
	}
	
	@Test
	public void validatePlateStartWithA_PlateStartWithA_thenTrue() {
		
		String vehiclePlate = "ABCDRIO";
		
		LocalDate localDate = LocalDate.of(2018, Month.DECEMBER, 9);
		
		parkingTickectUtility.setLocalDate(localDate);
		
		boolean actual = parkingTickectUtility
				.validatePlateStartWithA(vehiclePlate);
		
		assertTrue(actual);
	}
	
	@Test
	public void validatePlateStartWithA_PlateStartWithA_thenFalse() {
		
		String vehiclePlate = "ABCDRIO";
		
		LocalDate localDate = LocalDate.of(2018, Month.DECEMBER, 12);
		
		parkingTickectUtility.setLocalDate(localDate);
		
		boolean actual = parkingTickectUtility
				.validatePlateStartWithA(vehiclePlate);
		
		assertFalse(actual);
	}
	
	@Test
	public void validatePlateStartWithA_PlateStartWithX_thenTrue() {
		
		String vehiclePlate = "XBCDRIO";
		
		LocalDate localDate = LocalDate.of(2018, Month.DECEMBER, 9);
		
		parkingTickectUtility.setLocalDate(localDate);
		
		boolean actual = parkingTickectUtility
				.validatePlateStartWithA(vehiclePlate);
		
		assertTrue(actual);
	}
	
	@Test
	public void validateMotoWithCylinderCapacityOver500_ThenTrue() {
		int vehicleCylinderCapacity = 600;
		
		boolean actual = parkingTickectUtility
				.validateMotoWithCylinderCapacityOver500
				(vehicleCylinderCapacity);
		
		assertTrue(actual);
		
	}
	
	@Test
	public void validateMotoWithCylinderCapacityOver500_ThenFalse() {
		int vehicleCylinderCapacity = 400;
		
		boolean actual = parkingTickectUtility
				.validateMotoWithCylinderCapacityOver500
				(vehicleCylinderCapacity);
		
		assertFalse(actual);
		
	}
	
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
	
	@Test
	public void calculateTotalToPay_CarWith27hours_Then11000() {
		
		String vehicleType = CAR;
		long hours = 27;
		int cylinderCapacity = 0;
		
		double actual = parkingTickectUtility
				.calculateTotalToPay(vehicleType, hours, cylinderCapacity);
		
		assertEquals(11000.0, actual,0.0);
	}
	
/*	@Test
	public void calculateTotalToPay_MotoWith10hoursAnd600Cyl_Then6000() {
		
		String vehicleType = MOTORCYCLE;
		long hours = 10;
		int cylinderCapacity = 600;
		
		double actual = parkingTickectUtility
				.calculateTotalToPay(vehicleType, hours, cylinderCapacity);
		
		System.out.println("::::::::::"+actual);
		assertEquals(6000.0, actual,0.0);
	}
	*/
}
