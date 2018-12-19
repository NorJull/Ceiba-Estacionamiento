package com.vamosaprogramar.CeibaEstacionamiento.unit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;
import java.util.Arrays;
import java.util.List;


import static org.hamcrest.Matchers.containsString;
import com.vamosaprogramar.CeibaEstacionamiento.controller.ParkingTicketController;
import com.vamosaprogramar.CeibaEstacionamiento.dto.ParkingTicketDTO;
import com.vamosaprogramar.CeibaEstacionamiento.service.ParkingTicketService;

@RunWith(SpringRunner.class)
@WebMvcTest(ParkingTicketController.class)
public class ParkingTicketControllerTest {
	
	@Autowired
    private MockMvc mvc;
	
	@MockBean
    private ParkingTicketService parkingTicketService;

	@Test
	public void givenId_when_GetParkingTicket_thenReturnJsonWithParkingTicket() throws Exception {
		
		ParkingTicketDTO parkingTicketDTO = new ParkingTicketDTO();
		parkingTicketDTO.setVehiclePlate("ABC123");
		
		Mockito.when(parkingTicketService.getParkingTicketDTO(1)).thenReturn(parkingTicketDTO);
		
		mvc.perform(get("/parkingtickets/{id}",1)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().string(containsString(parkingTicketDTO.getVehiclePlate())));
	}
	
	@Test
	public void whenGetParkingTickets_thenReturnJsonWithParkingTikectDTOs() throws Exception {
		
		ParkingTicketDTO parkingTicketDTO = new ParkingTicketDTO();
		parkingTicketDTO.setVehiclePlate("ABC123");
		
		List<ParkingTicketDTO> parkingTicketDTOs = Arrays.asList(parkingTicketDTO);
		
		Mockito.when(parkingTicketService.getparkingTicketDTOs()).thenReturn(parkingTicketDTOs);
		
		mvc.perform(get("/parkingtickets")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(1)))
		.andExpect(jsonPath("$[0].vehiclePlate", is(parkingTicketDTO.getVehiclePlate())));
		
		
	}
	
	@Test
	public void whenGetCurrentParkingTickets_thenReturnJsonWithParkingTikectDTOs() throws Exception {
		
		ParkingTicketDTO parkingTicketDTO = new ParkingTicketDTO();
		parkingTicketDTO.setVehiclePlate("ABC123");
		
		List<ParkingTicketDTO> parkingTicketDTOs = Arrays.asList(parkingTicketDTO);
		
		Mockito.when(parkingTicketService.getCurrentParkingTicketDTOs()).thenReturn(parkingTicketDTOs);
		
		mvc.perform(get("/parkingtickets/currents")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(1)))
		.andExpect(jsonPath("$[0].vehiclePlate", is(parkingTicketDTO.getVehiclePlate())));
		
		
	}
	
	@Test
	public void whenToRegisterEntry_thenReturnSuccessMessage() throws Exception {
		ParkingTicketDTO parkingTicketDTO = new ParkingTicketDTO();
		parkingTicketDTO.setVehiclePlate("ABC123");
		
		Mockito.doNothing().when(parkingTicketService).toRegisterEntry(parkingTicketDTO);
		
		mvc.perform(post("/parkingtickets/toRegisterEntry")
				.contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(parkingTicketDTO)))
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("The entry was registered")));
	}
	
	@Test
	public void whenToCheckOut_thenReturnSuccessMessage() throws Exception {
		ParkingTicketDTO parkingTicketDTO = new ParkingTicketDTO();
		parkingTicketDTO.setVehiclePlate("ABC123");
		
		Mockito.when(parkingTicketService.toCheckOut(1)).thenReturn(parkingTicketDTO);
		
		mvc.perform(get("/parkingtickets/toCheckOut/{id}",1)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().string(containsString(parkingTicketDTO.getVehiclePlate())));
	}
	
}
