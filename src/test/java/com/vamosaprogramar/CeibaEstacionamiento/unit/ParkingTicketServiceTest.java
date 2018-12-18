package com.vamosaprogramar.CeibaEstacionamiento.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.vamosaprogramar.CeibaEstacionamiento.dto.ParkingTicketDTO;
import com.vamosaprogramar.CeibaEstacionamiento.entity.ParkingTicket;
import com.vamosaprogramar.CeibaEstacionamiento.factory.ParkingTicketFactory;
import com.vamosaprogramar.CeibaEstacionamiento.repository.ParkingTicketRepository;
import com.vamosaprogramar.CeibaEstacionamiento.service.ParkingTicketService;
import com.vamosaprogramar.CeibaEstacionamiento.service.ParkingTicketServiceImpl;
import com.vamosaprogramar.CeibaEstacionamiento.utility.ParkingTickectUtility;

@RunWith(SpringRunner.class)
public class ParkingTicketServiceTest {

    @TestConfiguration
	static class ParkingTicketServiceTestContextConfiguration {
              
        @Bean
        public ParkingTicketService parkingTicketService() {
        	return new ParkingTicketServiceImpl();
        }
    }
    
    @Autowired
    private ParkingTicketService parkingTicketService;

    @MockBean
    private ParkingTicketRepository parkingTicketRepository;
    
    @MockBean
    private ParkingTicketFactory parkingTicketFactory;
    
    @MockBean
    private ParkingTickectUtility parkingTickectUtility;
    
    @Before
    public void setUp() {
    	
    	
    }
    
    @Test
    public void whenValidId_thenParkingTicketShouldBeFound() {
    	ParkingTicket parkingTicket = new ParkingTicket();
    	
    	ParkingTicketDTO parkingTicketDTO = new ParkingTicketDTO();
    	
    	Mockito.when(parkingTicketRepository.getParkingTicket(1)).thenReturn(parkingTicket);
    	Mockito.when(parkingTicketFactory.createParkingTicketDTO(parkingTicket)).thenReturn(parkingTicketDTO);
       	
    	ParkingTicketDTO actual = parkingTicketService.getParkingTicketDTO(1);
    	
    	assertEquals(actual, parkingTicketDTO);
       
     }
    
    @Test
    public void whenGetparkingTicketDTOs_thenparkingTicketDTOsShouldBeReturn() {
    	List<ParkingTicketDTO> parkingTicketDTOs = new ArrayList<>();
    	ParkingTicketDTO parkingTicketDTO = new ParkingTicketDTO();
    	
    	List<ParkingTicket> parkingTickets = new ArrayList<ParkingTicket>();
;
    	
    	Mockito.when(parkingTicketRepository.getParkingTickets()).thenReturn(parkingTickets);
    	Mockito.when(parkingTicketFactory.createAParkingTicketDAOList(parkingTickets)).thenReturn(parkingTicketDTOs);
    	
    	List<ParkingTicketDTO> actual = parkingTicketService.getparkingTicketDTOs();

    	assertEquals(parkingTicketDTOs, actual);
    	
    }
    
}
