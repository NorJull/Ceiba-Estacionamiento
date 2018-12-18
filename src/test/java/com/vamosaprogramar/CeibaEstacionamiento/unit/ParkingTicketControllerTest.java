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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


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
}
