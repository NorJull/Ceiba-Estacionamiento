package com.vamosaprogramar.CeibaEstacionamiento.service;



import java.util.List;

import com.vamosaprogramar.CeibaEstacionamiento.entity.ParkingTicket;
import com.vamosaprogramar.CeibaEstacionamiento.exception.EmptyPlateException;
import com.vamosaprogramar.CeibaEstacionamiento.exception.OverNumberCarException;
import com.vamosaprogramar.CeibaEstacionamiento.exception.OverNumberMotosException;
import com.vamosaprogramar.CeibaEstacionamiento.exception.OverNumberVehiclesException;
import com.vamosaprogramar.CeibaEstacionamiento.exception.PlateStartsWithAException;

public interface ParkingTicketService {

	public ParkingTicket getParkingTicket(int id);

	public void toRegisterEntry(ParkingTicket parkingTicket) throws  PlateStartsWithAException, EmptyPlateException, OverNumberVehiclesException;

	public List<ParkingTicket> getParkingTickets();


	public void toCheckOut(int parkingTicketId);
}
