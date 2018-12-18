package com.vamosaprogramar.CeibaEstacionamiento.unit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static com.vamosaprogramar.CeibaEstacionamiento.GeneralConstants.MOTORCYCLE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.AssertTrue;

import static com.vamosaprogramar.CeibaEstacionamiento.GeneralConstants.CAR;
import com.vamosaprogramar.CeibaEstacionamiento.dto.ParkingTicketDTO;
import com.vamosaprogramar.CeibaEstacionamiento.entity.CarParkingTicket;
import com.vamosaprogramar.CeibaEstacionamiento.entity.MotorcycleParkingTicket;
import com.vamosaprogramar.CeibaEstacionamiento.entity.ParkingTicket;
import com.vamosaprogramar.CeibaEstacionamiento.factory.ParkingTicketFactory;
import com.vamosaprogramar.CeibaEstacionamiento.factory.ParkingTicketFactoryImpl;

@RunWith(SpringRunner.class)
public class ParkingTicketFactoryTest {
	
	   @TestConfiguration
		static class ParkingTicketFactoryTestContextConfiguration {
	              
	        @Bean
	        public ParkingTicketFactory parkingTicketFactory() {
	        	return new ParkingTicketFactoryImpl();
	        }
	    }
	    
	    @Autowired
	    private ParkingTicketFactory parkingTicketFactory;
	    
	    @Test
	    public void whenDTOTypeCar_thenReturnAInstanceOfCarParkingTicket() {
	    	ParkingTicketDTO parkingTicketDTO = new ParkingTicketDTO();
	    	parkingTicketDTO.setVehicleType(CAR);
	    	
	    	ParkingTicket parkingTicket = parkingTicketFactory.createParkingTicket(parkingTicketDTO);
	    		    	
	    	assertTrue(parkingTicket instanceof CarParkingTicket);
	    }
	    
	    @Test
	    public void whenDTOTypeMotorcycle_thenReturnAInstanceOfCarParkingTicket() {
	    	ParkingTicketDTO parkingTicketDTO = new ParkingTicketDTO();
	    	parkingTicketDTO.setVehicleType(MOTORCYCLE);
	    	
	    	ParkingTicket parkingTicket = parkingTicketFactory.createParkingTicket(parkingTicketDTO);
	    		    	
	    	assertTrue(parkingTicket instanceof MotorcycleParkingTicket);
	    }
	    
	    @Test
	    public void whenParkingTicketTypeCar_thenReturnDTOTypeCar() {
	    	ParkingTicket parkingTicket = new CarParkingTicket();
	    	parkingTicket.setVehicleType(CAR);
	    	
	    	ParkingTicketDTO parkingTicketDTO = parkingTicketFactory.createParkingTicketDTO(parkingTicket);
	    	
	    	assertEquals(CAR, parkingTicketDTO.getVehicleType());		
	    }

	    @Test
	    public void whenListParkingTickets_thenReturnListParkingTicketDTOs() {
	    	List<ParkingTicket> parkingTickets = new ArrayList<>();
	    	ParkingTicket parkingTicket = new CarParkingTicket();
	    	parkingTickets.add(parkingTicket);
	    	
	    	List parkingTicketDTOs = parkingTicketFactory.createAParkingTicketDAOList(parkingTickets);
	    	
	    	assertTrue(parkingTicketDTOs.size() == 1);
	    }
}
