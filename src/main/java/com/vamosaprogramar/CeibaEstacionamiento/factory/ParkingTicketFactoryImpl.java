package com.vamosaprogramar.CeibaEstacionamiento.factory;

import org.springframework.stereotype.Component;

import static com.vamosaprogramar.CeibaEstacionamiento.GeneralConstants.CAR;
import static com.vamosaprogramar.CeibaEstacionamiento.GeneralConstants.MOTORCYCLE;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.vamosaprogramar.CeibaEstacionamiento.dto.ParkingTicketDTO;
import com.vamosaprogramar.CeibaEstacionamiento.entity.CarParkingTicket;
import com.vamosaprogramar.CeibaEstacionamiento.entity.MotorcycleParkingTicket;
import com.vamosaprogramar.CeibaEstacionamiento.entity.ParkingTicket;

@Component
public class ParkingTicketFactoryImpl implements ParkingTicketFactory {

	@Override
	public ParkingTicket createParkingTicket(ParkingTicketDTO parkingTicketDTO) {
		
		ParkingTicket parkingTicket = null;
		
		if (parkingTicketDTO.getOptionalVehicleType().isPresent()) {
			
			if(parkingTicketDTO.getVehicleType().equals(CAR)) {
				
				parkingTicket = new CarParkingTicket();
				parkingTicket.setVehiclePlate(parkingTicketDTO.getVehiclePlate());
				parkingTicket.setVehicleType(CAR);
				
				return parkingTicket;
			}
			
			if(parkingTicketDTO.getVehicleType().equals(MOTORCYCLE)) {

				parkingTicket = new MotorcycleParkingTicket();
				parkingTicket.setVehiclePlate(parkingTicketDTO.getVehiclePlate());
				parkingTicket.setVehicleType(MOTORCYCLE);
				
				parkingTicket.setVehicleCylinderCapacity(parkingTicketDTO.getVehicleCylinderCapacity());
				
				return parkingTicket;
			}
		}
		
		return null;
	}

	@Override
	public ParkingTicketDTO createParkingTicketDTO(ParkingTicket parkingTicket) {
		ParkingTicketDTO parkingTicketDTO = new ParkingTicketDTO();
		
		parkingTicketDTO.setId(parkingTicket.getId());
		parkingTicketDTO.setStartDate(parkingTicket.getStartDate());
		parkingTicketDTO.setFinishDate(parkingTicket.getFinishDate());
		parkingTicketDTO.setStatus(parkingTicket.getStatus());
		parkingTicketDTO.setTotal(parkingTicket.getTotal());
		parkingTicketDTO.setVehicleCylinderCapacity(parkingTicket.getVehicleCylinderCapacity());
		parkingTicketDTO.setVehiclePlate(parkingTicket.getVehiclePlate());
		parkingTicketDTO.setVehicleType(parkingTicket.getVehicleType());
		
		return parkingTicketDTO;
	}
	
	@Override
	public List<ParkingTicketDTO> createAParkingTicketDAOList(List<ParkingTicket> parkingTickets){
		
		List<ParkingTicketDTO> parkingTicketDTOs = 
				parkingTickets.stream().map(x -> createParkingTicketDTO(x)).collect(Collectors.toList());
		
		return parkingTicketDTOs;
	}

}
