package com.vamosaprogramar.CeibaEstacionamiento.service;

import static com.vamosaprogramar.CeibaEstacionamiento.GeneralConstants.TICKET_CHECKOUT_REGISTERED;
import static com.vamosaprogramar.CeibaEstacionamiento.GeneralConstants.TICKET_REGISTERED;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vamosaprogramar.CeibaEstacionamiento.dto.ParkingTicketDTO;
import com.vamosaprogramar.CeibaEstacionamiento.entity.ParkingTicket;
import com.vamosaprogramar.CeibaEstacionamiento.exception.EmptyPlateException;
import com.vamosaprogramar.CeibaEstacionamiento.exception.OverNumberVehiclesException;
import com.vamosaprogramar.CeibaEstacionamiento.exception.PlateStartsWithAException;
import com.vamosaprogramar.CeibaEstacionamiento.factory.ParkingTicketFactory;
import com.vamosaprogramar.CeibaEstacionamiento.repository.ParkingTicketRepository;
import com.vamosaprogramar.CeibaEstacionamiento.utility.ParkingTickectUtility;

@Service
public class ParkingTicketServiceImpl implements ParkingTicketService {

	@Autowired
	private ParkingTicketRepository parkingTicketRepository;
	@Autowired
	private ParkingTickectUtility parkingTickectUtility;

	@Autowired
	private ParkingTicketFactory parkingTicketFactory;

	@Override
	public ParkingTicketDTO getParkingTicketDTO(int id) {

		return parkingTicketFactory.createParkingTicketDTO(parkingTicketRepository.getParkingTicket(id));
	}

	@Override
	public void toRegisterEntry(ParkingTicketDTO parkingTicketDTO)
			throws PlateStartsWithAException, EmptyPlateException, OverNumberVehiclesException {

		ParkingTicket parkingTicket = parkingTicketFactory.createParkingTicket(parkingTicketDTO);

		// Valida el número de vehiculos concurrentes
		if (parkingTicket.validateConcurrentVehicles(
				parkingTicketRepository.countNumberConcurrentVehicles(parkingTicket.getVehicleType()))) {
			throw new OverNumberVehiclesException();
		}

		// Valida que la placa no venga vacía
		parkingTicket.getOptionalVehiclePlate().orElseThrow(EmptyPlateException::new);

		// Valida placas
		parkingTicket.validateVehiclePlate();

		// Fijar fecha de entrada, estado inicial y el total
		parkingTicket.setStartDate(LocalDateTime.now());
		parkingTicket.setStatus(TICKET_REGISTERED);
		parkingTicket.setTotal(0.0);

		parkingTicketRepository.addParkingTicket(parkingTicket);

	}

	@Override
	public List<ParkingTicketDTO> getparkingTicketDTOs() {

		return parkingTicketFactory.createAParkingTicketDAOList(parkingTicketRepository.getParkingTickets());
	}

	@Override
	public ParkingTicketDTO toCheckOut(int parkingTicketId) {

		ParkingTicket parkingTicket = parkingTicketRepository.getParkingTicket(parkingTicketId);

		LocalDateTime finishDate = LocalDateTime.now();

		long hours = parkingTickectUtility.hoursBetweenTwoDates(parkingTicket.getOptionalStartDate().get(), finishDate);

		double totalToPay = parkingTicket.calculateTotalToPay(hours);

		parkingTicket.setStatus(TICKET_CHECKOUT_REGISTERED);
		parkingTicket.setTotal(totalToPay);
		parkingTicket.setFinishDate(finishDate);

		parkingTicketRepository.toCheckOut(parkingTicket);

		return parkingTicketFactory.createParkingTicketDTO(parkingTicket);

	}

}
