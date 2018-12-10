package com.vamosaprogramar.CeibaEstacionamiento.repository;

import java.util.List;

import com.vamosaprogramar.CeibaEstacionamiento.entity.ParkingTicket;

public interface ParkingTicketRepository {

	public Integer countNumberConcurrentVehicles(String vehicleType);

	public void addParkingTicket(ParkingTicket parkingTicket);

	public List<ParkingTicket> getParkingTickets();

	public ParkingTicket getParkingTicket(int id);

}
