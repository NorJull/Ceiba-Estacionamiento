package com.vamosaprogramar.CeibaEstacionamiento.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vamosaprogramar.CeibaEstacionamiento.GeneralConstants;
import com.vamosaprogramar.CeibaEstacionamiento.entity.ParkingTicket;
import com.vamosaprogramar.CeibaEstacionamiento.exception.EmptyPlateException;
import com.vamosaprogramar.CeibaEstacionamiento.exception.OverNumberCarException;
import com.vamosaprogramar.CeibaEstacionamiento.exception.OverNumberMotosException;
import com.vamosaprogramar.CeibaEstacionamiento.exception.PlateStartsWithAException;
import com.vamosaprogramar.CeibaEstacionamiento.repository.ParkingTicketRepository;

@Service
public class ParkingTicketServiceImpl implements ParkingTicketService{

	@Autowired
	private ParkingTicketRepository parkingTicketRepository;

	@Override
	public ParkingTicket getParkingTicket(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void toRegisterEntry(ParkingTicket parkingTicket) throws OverNumberMotosException, OverNumberCarException, PlateStartsWithAException, EmptyPlateException {
		
		//Valida el número de motos concurrentes
		parkingTicket.getOptionalVehicleType()
		.filter(x -> x.equals(GeneralConstants.MOTORCYCLE))
		.filter(x -> {return parkingTicketRepository.countNumberConcurrentVehicles(x) < GeneralConstants.MAX_NUMBER_CONCURRENT_MOTO;})
		.orElseThrow(OverNumberMotosException::new);
		
		//Valida el número de carros concurrentes
		parkingTicket.getOptionalVehicleType()
		.filter(x -> x.equals(GeneralConstants.CAR))
		.filter(x -> {return parkingTicketRepository.countNumberConcurrentVehicles(x) < GeneralConstants.MAX_NUMBER_CONCURRENT_CAR;})
		.orElseThrow(OverNumberCarException::new);
		
		//Valida que la placa no venga vacía
		parkingTicket.getOptionalVehiclePlate()
		.orElseThrow(EmptyPlateException::new);
		
		//Valida placas que inician con 'A'
		parkingTicket.getOptionalVehiclePlate()
		.filter(x -> x.startsWith("A"))
		.filter(x -> {
			DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
			return (dayOfWeek != DayOfWeek.SUNDAY) && (dayOfWeek != DayOfWeek.MONDAY); 
			}
				)
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
