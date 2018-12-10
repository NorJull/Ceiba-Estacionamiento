package com.vamosaprogramar.CeibaEstacionamiento.repository;

import java.util.List;

import com.vamosaprogramar.CeibaEstacionamiento.entity.ParkingTicket;

public interface ParkingTicketRepository {

	public int countNumberConcurrentVehicles(String vehicleType);

	public void addParkingTicket(ParkingTicket parkingTicket);

	public List<ParkingTicket> getParkingTickets();

}
