package com.vamosaprogramar.CeibaEstacionamiento.unit;

import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.vamosaprogramar.CeibaEstacionamiento.GeneralConstants;
import com.vamosaprogramar.CeibaEstacionamiento.entity.ParkingTicket;
import com.vamosaprogramar.CeibaEstacionamiento.repository.ParkingTicketRepository;
import com.vamosaprogramar.CeibaEstacionamiento.repository.ParkingTicketRepositoryImpl;

@RunWith(SpringRunner.class)
public class ParkingTicketRepositoryTest {

	@TestConfiguration
	static class ParkingTicketRepositoryTestContextConfiguration{
		
		@Bean
		public ParkingTicketRepository parkingTicketRepository() {
			return new ParkingTicketRepositoryImpl();
		}
		
	}
	
	@Autowired
	private ParkingTicketRepository parkingTicketRepository;
	
	@MockBean
	private SessionFactory sessionFactory;
	
	
	@Test
	public void whenAddParkingTicket_thenSaveTheTickect() {
		ParkingTicket parkingTicket = new ParkingTicket();
		parkingTicket.setVehiclePlate("ABC123");
		parkingTicket.setVehicleType(GeneralConstants.CAR);
		
		parkingTicketRepository.addParkingTicket(parkingTicket);
		
	}
}
