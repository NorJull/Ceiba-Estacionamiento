package com.vamosaprogramar.CeibaEstacionamiento.service;

import static com.vamosaprogramar.CeibaEstacionamiento.GeneralConstants.CAR;
import static com.vamosaprogramar.CeibaEstacionamiento.GeneralConstants.CAR_DAY_VALUE;
import static com.vamosaprogramar.CeibaEstacionamiento.GeneralConstants.CAR_HOUR_VALUE;
import static com.vamosaprogramar.CeibaEstacionamiento.GeneralConstants.HOURS_TO_BE_DAY;
import static com.vamosaprogramar.CeibaEstacionamiento.GeneralConstants.MOTORCYCLE_DAY_VALUE;
import static com.vamosaprogramar.CeibaEstacionamiento.GeneralConstants.MOTORCYCLE_HOUR_VALUE;
import static com.vamosaprogramar.CeibaEstacionamiento.GeneralConstants.TICKET_CHECKOUT_REGISTERED;
import static com.vamosaprogramar.CeibaEstacionamiento.GeneralConstants.TICKET_REGISTERED;
import static com.vamosaprogramar.CeibaEstacionamiento.GeneralConstants.VALUE_TO_PAY_EXCESSED_BY_CYLINDER;

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
public class ParkingTicketServiceImpl implements ParkingTicketService {

	@Autowired
	private ParkingTicketRepository parkingTicketRepository;
	@Autowired
	private ParkingTickectUtility parkingTickectUtility;

	@Override
	public ParkingTicket getParkingTicket(int id) {

		return parkingTicketRepository.getParkingTicket(id);
	}

	@Override
	public void toRegisterEntry(ParkingTicket parkingTicket)
			throws PlateStartsWithAException, EmptyPlateException, OverNumberVehiclesException {

		// Valida el número de vehiculos concurrentes
		parkingTicket.getOptionalVehicleType()
				.filter(x -> parkingTickectUtility.validateNumberOfConcurrentVehicles(x,
						parkingTicketRepository.countNumberConcurrentVehicles(x)))
				.orElseThrow(OverNumberVehiclesException::new);

		// Valida que la placa no venga vacía
		parkingTicket.getOptionalVehiclePlate().orElseThrow(EmptyPlateException::new);

		// Valida placas que inician con 'A'
		parkingTicket.getOptionalVehiclePlate().filter(x -> parkingTickectUtility.validatePlateStartWithA(x))
				.orElseThrow(PlateStartsWithAException::new);

		// Fijar fecha de entrada, estado inicial y el total
		parkingTicket.setStartDate(LocalDateTime.now());
		parkingTicket.setStatus(TICKET_REGISTERED);
		parkingTicket.setTotal(0.0);

		parkingTicketRepository.addParkingTicket(parkingTicket);

	}

	@Override
	public List<ParkingTicket> getParkingTickets() {

		return parkingTicketRepository.getParkingTickets();
	}

	@Override
	public void toCheckOut(int parkingTicketId) {

		ParkingTicket parkingTicket = parkingTicketRepository.getParkingTicket(parkingTicketId);

		LocalDateTime finishDate = LocalDateTime.now();
		
		long hours = parkingTickectUtility.hoursBetweenTwoDates(parkingTicket.getOptionalStartDate().get(), finishDate);

		double totalToPay = parkingTickectUtility.calculateTotalToPay(
				parkingTicket.getOptionalVehicleType().orElse(CAR), hours,
				parkingTicket.getOptionalVehicleCylinderCapacity().orElse(0));

		parkingTicket.setStatus(TICKET_CHECKOUT_REGISTERED);
		parkingTicket.setTotal(totalToPay);
		parkingTicket.setFinishDate(finishDate);

		parkingTicketRepository.toCheckOut(parkingTicket);

	}

}
