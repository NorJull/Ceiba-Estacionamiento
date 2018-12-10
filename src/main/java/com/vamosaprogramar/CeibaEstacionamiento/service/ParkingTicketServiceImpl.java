package com.vamosaprogramar.CeibaEstacionamiento.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vamosaprogramar.CeibaEstacionamiento.GeneralConstants;
import com.vamosaprogramar.CeibaEstacionamiento.entity.ParkingTicket;
import com.vamosaprogramar.CeibaEstacionamiento.exception.EmptyPlateException;
import com.vamosaprogramar.CeibaEstacionamiento.exception.OverNumberCarException;
import com.vamosaprogramar.CeibaEstacionamiento.exception.OverNumberMotosException;
import com.vamosaprogramar.CeibaEstacionamiento.exception.OverNumberVehiclesException;
import com.vamosaprogramar.CeibaEstacionamiento.exception.PlateStartsWithAException;
import com.vamosaprogramar.CeibaEstacionamiento.repository.ParkingTicketRepository;
import com.vamosaprogramar.CeibaEstacionamiento.utility.ParkingTickectUtility;

@Service
public class ParkingTicketServiceImpl implements ParkingTicketService{

	@Autowired
	private ParkingTicketRepository parkingTicketRepository;
	@Autowired
	private ParkingTickectUtility parkingTickectUtility;
	
	@Override
	public ParkingTicket getParkingTicket(int id) {
		
		return parkingTicketRepository.getParkingTicket(id) ;
	}

	@Override
	public void toRegisterEntry(ParkingTicket parkingTicket) throws OverNumberMotosException, OverNumberCarException, PlateStartsWithAException, EmptyPlateException, OverNumberVehiclesException {

		//Valida el número de motos concurrentes
		parkingTicket.getOptionalVehicleType()
		.filter(x -> parkingTickectUtility.validateNumberOfConcurrentVehicles(x, parkingTicketRepository.countNumberConcurrentVehicles(x)))
		.orElseThrow(OverNumberVehiclesException::new);
	
		
		//Valida que la placa no venga vacía
		parkingTicket.getOptionalVehiclePlate()
		.orElseThrow(EmptyPlateException::new);
		
		//Valida placas que inician con 'A'
		parkingTicket.getOptionalVehiclePlate()
		.filter(x -> parkingTickectUtility.validatePlateStartWithA(x))
		.orElseThrow(PlateStartsWithAException::new);
		
		//Fijar fecha de entrada y estado inicial
		parkingTicket.setStartDate(LocalDateTime.now());
		parkingTicket.setStatus(GeneralConstants.TICKET_REGISTERED);
		
		parkingTicketRepository.addParkingTicket(parkingTicket);
		
	}

	@Override
	public List<ParkingTicket> getParkingTickets() {
		
		return parkingTicketRepository.getParkingTickets();
	}

	@Override
	public void toCheckOut(ParkingTicket parkingTicket) {
		// TODO Auto-generated method stub
		
	}

}
