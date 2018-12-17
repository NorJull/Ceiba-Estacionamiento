package com.vamosaprogramar.CeibaEstacionamiento.factory;

import java.util.List;

import com.vamosaprogramar.CeibaEstacionamiento.dto.ParkingTicketDTO;
import com.vamosaprogramar.CeibaEstacionamiento.entity.ParkingTicket;

public interface ParkingTicketFactory {

	public ParkingTicket createParkingTicket(ParkingTicketDTO parkingTicketDTO);
	
	public ParkingTicketDTO createParkingTicketDTO(ParkingTicket parkingTicket);

	List<ParkingTicketDTO> createAParkingTicketDAOList(List<ParkingTicket> parkingTickets);
}
