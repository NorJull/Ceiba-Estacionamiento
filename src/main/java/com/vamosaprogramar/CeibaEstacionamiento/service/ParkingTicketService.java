package com.vamosaprogramar.CeibaEstacionamiento.service;



import java.util.List;

import com.vamosaprogramar.CeibaEstacionamiento.dto.ParkingTicketDTO;
import com.vamosaprogramar.CeibaEstacionamiento.exception.EmptyPlateException;
import com.vamosaprogramar.CeibaEstacionamiento.exception.OverNumberVehiclesException;
import com.vamosaprogramar.CeibaEstacionamiento.exception.PlateStartsWithAException;

public interface ParkingTicketService {

	public ParkingTicketDTO getParkingTicketDTO(int id);

	public void toRegisterEntry(ParkingTicketDTO parkingTicketDTO) throws  PlateStartsWithAException, EmptyPlateException, OverNumberVehiclesException;

	public List<ParkingTicketDTO> getparkingTicketDTOs();


	public ParkingTicketDTO toCheckOut(int parkingTicketId);
}
