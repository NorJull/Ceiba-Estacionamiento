package com.vamosaprogramar.CeibaEstacionamiento.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.AssertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;





import static com.vamosaprogramar.CeibaEstacionamiento.GeneralConstants.MAX_NUMBER_CONCURRENT_MOTO;
import static com.vamosaprogramar.CeibaEstacionamiento.GeneralConstants.MOTORCYCLE;
import static com.vamosaprogramar.CeibaEstacionamiento.GeneralConstants.MAX_NUMBER_CONCURRENT_CAR;
import static com.vamosaprogramar.CeibaEstacionamiento.GeneralConstants.CAR;
import com.vamosaprogramar.CeibaEstacionamiento.dto.ParkingTicketDTO;
import com.vamosaprogramar.CeibaEstacionamiento.entity.CarParkingTicket;
import com.vamosaprogramar.CeibaEstacionamiento.entity.MotorcycleParkingTicket;
import com.vamosaprogramar.CeibaEstacionamiento.entity.ParkingTicket;
import com.vamosaprogramar.CeibaEstacionamiento.exception.EmptyPlateException;
import com.vamosaprogramar.CeibaEstacionamiento.exception.OverNumberVehiclesException;
import com.vamosaprogramar.CeibaEstacionamiento.exception.PlateStartsWithAException;
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

    	
    	Mockito.when(parkingTicketRepository.getParkingTickets()).thenReturn(parkingTickets);
    	Mockito.when(parkingTicketFactory.createAParkingTicketDAOList(parkingTickets)).thenReturn(parkingTicketDTOs);
    	
    	List<ParkingTicketDTO> actual = parkingTicketService.getparkingTicketDTOs();

    	assertEquals(parkingTicketDTOs, actual);
    	
    }
    @Test(expected = OverNumberVehiclesException.class)
    public void whenNumberOfCarsIsOverAllowed_thenThrowException() throws PlateStartsWithAException, EmptyPlateException, OverNumberVehiclesException {
    	ParkingTicketDTO parkingTicketDTO = new ParkingTicketDTO();
    	ParkingTicket parkingTicket = new CarParkingTicket();
    	parkingTicket.setVehicleType(CAR);
    	
    	Mockito.when(parkingTicketFactory.createParkingTicket(parkingTicketDTO)).thenReturn(parkingTicket);
    	Mockito.when(parkingTicketRepository.countNumberConcurrentVehicles(CAR)).thenReturn(MAX_NUMBER_CONCURRENT_CAR + 1);
    	
    	parkingTicketService.toRegisterEntry(parkingTicketDTO);
    }
    
    @Test(expected = OverNumberVehiclesException.class)
    public void whenNumberOfMotorcyclesIsOverAllowed_thenThrowException() throws PlateStartsWithAException, EmptyPlateException, OverNumberVehiclesException {
    	ParkingTicketDTO parkingTicketDTO = new ParkingTicketDTO();
    	ParkingTicket parkingTicket = new MotorcycleParkingTicket();
    	parkingTicket.setVehicleType(MOTORCYCLE);
    	
    	Mockito.when(parkingTicketFactory.createParkingTicket(parkingTicketDTO)).thenReturn(parkingTicket);
    	Mockito.when(parkingTicketRepository.countNumberConcurrentVehicles(MOTORCYCLE)).thenReturn(MAX_NUMBER_CONCURRENT_MOTO + 1);
    	
    	parkingTicketService.toRegisterEntry(parkingTicketDTO);
    }
    
    @Test(expected = EmptyPlateException.class)
    public void whenPlateIsEmpty_thenThrowException() throws PlateStartsWithAException, EmptyPlateException, OverNumberVehiclesException {
    	ParkingTicketDTO parkingTicketDTO = new ParkingTicketDTO();
    	ParkingTicket parkingTicket = new MotorcycleParkingTicket();
    	parkingTicket.setVehicleType(MOTORCYCLE);
    	
    	Mockito.when(parkingTicketFactory.createParkingTicket(parkingTicketDTO)).thenReturn(parkingTicket);
    	Mockito.when(parkingTicketRepository.countNumberConcurrentVehicles(MOTORCYCLE)).thenReturn(MAX_NUMBER_CONCURRENT_MOTO - 1);
    	
    	parkingTicketService.toRegisterEntry(parkingTicketDTO);
    }
    
    @Test
    public void whenToCheckOut_thenReturnParkingTicketDTO() {
    	ParkingTicket parkingTicket = new ParkingTicket();
    	LocalDateTime startDate = LocalDateTime.of(2018, 12, 20, 12, 0);
    	parkingTicket.setStartDate(startDate);
    	
    	LocalDateTime finishDate = startDate.plusHours(10);
    	
    	parkingTicketService.setLocalDateTime(finishDate);
    	ParkingTicketDTO parkingTicketDTO = new ParkingTicketDTO();
    	
    	Mockito.when(parkingTicketRepository.getParkingTicket(1)).thenReturn(parkingTicket);
    	Mockito.doNothing().when(parkingTicketRepository).toCheckOut(parkingTicket);
    	Mockito.when(parkingTicketFactory.createParkingTicketDTO(parkingTicket)).thenReturn(parkingTicketDTO);
    
    	ParkingTicketDTO p = parkingTicketService.toCheckOut(1);
    
    	assertTrue(p instanceof ParkingTicketDTO);
    }
}
