package com.vamosaprogramar.CeibaEstacionamiento.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import static com.vamosaprogramar.CeibaEstacionamiento.GeneralConstants.MOTORCYCLE_HOUR_VALUE;
import static com.vamosaprogramar.CeibaEstacionamiento.GeneralConstants.MOTORCYCLE_DAY_VALUE;
import static com.vamosaprogramar.CeibaEstacionamiento.GeneralConstants.CAR_HOUR_VALUE;
import static com.vamosaprogramar.CeibaEstacionamiento.GeneralConstants.CAR_DAY_VALUE;
import static com.vamosaprogramar.CeibaEstacionamiento.GeneralConstants.MAX_NUMBER_CONCURRENT_MOTO;
import static com.vamosaprogramar.CeibaEstacionamiento.GeneralConstants.MAX_NUMBER_CONCURRENT_CAR;
import com.vamosaprogramar.CeibaEstacionamiento.entity.CarParkingTicket;
import com.vamosaprogramar.CeibaEstacionamiento.entity.MotorcycleParkingTicket;
import com.vamosaprogramar.CeibaEstacionamiento.entity.ParkingTicket;


public class ParkingTicketTest {
	
	private ParkingTicket parkingTicket;
	
	@Test
	public void givenA21Cars_whenValidateConcurrentVehicles_thenTrue() {
		parkingTicket = new CarParkingTicket();
		
		boolean actual = parkingTicket.validateConcurrentVehicles(MAX_NUMBER_CONCURRENT_CAR+1);
		
		assertTrue(actual);
	}

	@Test
	public void givenA19Cars_whenValidateConcurrentVehicles_thenTrue() {
		parkingTicket = new CarParkingTicket();
		
		boolean actual = parkingTicket.validateConcurrentVehicles(MAX_NUMBER_CONCURRENT_CAR-1);
		
		assertFalse(actual);
	}
	
	@Test
	public void givenA11Motos_whenValidateConcurrentVehicles_thenTrue() {
		parkingTicket = new MotorcycleParkingTicket();
		
		boolean actual = parkingTicket.validateConcurrentVehicles(MAX_NUMBER_CONCURRENT_MOTO+1);
		
		assertTrue(actual);
	}

	@Test
	public void givenA9Motos_whenValidateConcurrentVehicles_thenTrue() {
		parkingTicket = new MotorcycleParkingTicket();
		
		boolean actual = parkingTicket.validateConcurrentVehicles(MAX_NUMBER_CONCURRENT_MOTO-1);
		
		assertFalse(actual);
	}
	
	
	@Test
	public void given6hourAndACar_whenCalculateTotalToPay_ThenReturnDouble() {
		parkingTicket = new CarParkingTicket();
		int hours = 5;
		double expected = CAR_HOUR_VALUE * hours;
		
		double actual = parkingTicket.calculateTotalToPay(hours);
		
		assertEquals(expected, actual,0.0);
	}
	
	@Test
	public void given9hourAndACar_whenCalculateTotalToPay_ThenReturnDouble() {
		parkingTicket = new CarParkingTicket();
		int hours = 9;
		double expected = CAR_DAY_VALUE;
		
		double actual = parkingTicket.calculateTotalToPay(hours);
		
		assertEquals(expected, actual,0.0);
	}
	
	@Test
	public void given15hourAndACar_whenCalculateTotalToPay_ThenReturnDouble() {
		parkingTicket = new CarParkingTicket();
		int hours = 15;
		double expected = CAR_DAY_VALUE;
		
		double actual = parkingTicket.calculateTotalToPay(hours);
		
		assertEquals(expected, actual,0.0);
	}
	
	@Test
	public void given25hourAndACar_whenCalculateTotalToPay_ThenReturnDouble() {
		parkingTicket = new CarParkingTicket();
		int hours = 25;
		int days = hours/24;
		int extraHours = hours%24;
		double expected = CAR_DAY_VALUE*days + CAR_HOUR_VALUE*extraHours;
		
		double actual = parkingTicket.calculateTotalToPay(hours);
		
		assertEquals(expected, actual,0.0);
	}
	
	@Test
	public void given40hourAndACar_whenCalculateTotalToPay_ThenReturnDouble() {
		parkingTicket = new CarParkingTicket();
		int hours = 40;
		int days = 2;
		double expected = CAR_DAY_VALUE*days;
		
		double actual = parkingTicket.calculateTotalToPay(hours);
		
		assertEquals(expected, actual,0.0);
	}
	
	@Test
	public void given6hourAndAMoto_whenCalculateTotalToPay_ThenReturnDouble() {
		parkingTicket = new  MotorcycleParkingTicket();
		int hours = 5;
		double expected = MOTORCYCLE_HOUR_VALUE * hours;
		
		double actual = parkingTicket.calculateTotalToPay(hours);
		
		assertEquals(expected, actual,0.0);
	}
	
	@Test
	public void given9hourAndAMoto_whenCalculateTotalToPay_ThenReturnDouble() {
		parkingTicket = new  MotorcycleParkingTicket();
		int hours = 9;
		double expected = MOTORCYCLE_DAY_VALUE;
		
		double actual = parkingTicket.calculateTotalToPay(hours);
		
		assertEquals(expected, actual,0.0);
	}
	
	@Test
	public void given15hourAndAMoto_whenCalculateTotalToPay_ThenReturnDouble() {
		parkingTicket = new  MotorcycleParkingTicket();
		int hours = 15;
		double expected = MOTORCYCLE_DAY_VALUE;
		
		double actual = parkingTicket.calculateTotalToPay(hours);
		
		assertEquals(expected, actual,0.0);
	}
	
	@Test
	public void given25hourAndAMoto_whenCalculateTotalToPay_ThenReturnDouble() {
		parkingTicket = new  MotorcycleParkingTicket();
		int hours = 25;
		int days = hours/24;
		int extraHours = hours%24;
		double expected = MOTORCYCLE_DAY_VALUE*days + MOTORCYCLE_HOUR_VALUE*extraHours;
		
		double actual = parkingTicket.calculateTotalToPay(hours);
		
		assertEquals(expected, actual,0.0);
	}
	
	@Test
	public void given40hourAndAMoto_whenCalculateTotalToPay_ThenReturnDouble() {
		parkingTicket = new  MotorcycleParkingTicket();
		int hours = 40;
		int days = 2;
		double expected = MOTORCYCLE_DAY_VALUE*days;
		
		double actual = parkingTicket.calculateTotalToPay(hours);
		
		assertEquals(expected, actual,0.0);
	}
}
