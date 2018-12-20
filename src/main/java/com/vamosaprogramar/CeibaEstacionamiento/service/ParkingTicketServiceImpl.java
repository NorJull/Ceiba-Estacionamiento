package com.vamosaprogramar.CeibaEstacionamiento.service;

import static com.vamosaprogramar.CeibaEstacionamiento.GeneralConstants.TICKET_CHECKOUT_REGISTERED;
import static com.vamosaprogramar.CeibaEstacionamiento.GeneralConstants.TICKET_REGISTERED;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vamosaprogramar.CeibaEstacionamiento.GeneralConstants;
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

	private LocalDateTime localDateTime;
	
	public ParkingTicketServiceImpl() {
		localDateTime = LocalDateTime.now();
	}

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
		parkingTicket.setStartDate(localDateTime.now());
		parkingTicket.setStatus(TICKET_REGISTERED);
		parkingTicket.setTotal(0.0);

		parkingTicketRepository.addParkingTicket(parkingTicket);

	}



	@Override
	public ParkingTicketDTO toCheckOut(int parkingTicketId) {

		ParkingTicket parkingTicket = parkingTicketRepository.getParkingTicket(parkingTicketId);

		LocalDateTime finishDate = localDateTime.now();

		long hours = parkingTickectUtility.hoursBetweenTwoDates(parkingTicket.getOptionalStartDate().get(), finishDate);

		double totalToPay = parkingTicket.calculateTotalToPay(hours);

		parkingTicket.setStatus(TICKET_CHECKOUT_REGISTERED);
		parkingTicket.setTotal(totalToPay);
		parkingTicket.setFinishDate(finishDate);

		parkingTicketRepository.toCheckOut(parkingTicket);

		return parkingTicketFactory.createParkingTicketDTO(parkingTicket);

	}
	@Override
	public List<ParkingTicketDTO> getparkingTicketDTOs() {

		return parkingTicketFactory.createAParkingTicketDAOList(parkingTicketRepository.getParkingTickets());
	}
	
	@Override
	public List<ParkingTicketDTO> getCurrentParkingTicketDTOs() {
		// TODO Auto-generated method stub
		return parkingTicketFactory.createAParkingTicketDAOList(parkingTicketRepository.getParkingTicketsByStatus(GeneralConstants.TICKET_REGISTERED));
	}

	@Override
	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}
	

}
