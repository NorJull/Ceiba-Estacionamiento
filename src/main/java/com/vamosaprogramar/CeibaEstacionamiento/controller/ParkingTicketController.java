package com.vamosaprogramar.CeibaEstacionamiento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vamosaprogramar.CeibaEstacionamiento.dto.ParkingTicketDTO;
import com.vamosaprogramar.CeibaEstacionamiento.exception.EmptyPlateException;
import com.vamosaprogramar.CeibaEstacionamiento.exception.OverNumberVehiclesException;
import com.vamosaprogramar.CeibaEstacionamiento.exception.PlateStartsWithAException;
import com.vamosaprogramar.CeibaEstacionamiento.service.ParkingTicketService;

@RestController
@RequestMapping("parkingtickets")
@CrossOrigin(origins = "http://localhost:4200")
public class ParkingTicketController {

	@Autowired
	private ParkingTicketService parkingTicketService;

	@GetMapping("{id}")
	public ResponseEntity<ParkingTicketDTO> getParkingTickect(@PathVariable int id) {

		ParkingTicketDTO parkingTicketDTO = parkingTicketService.getParkingTicketDTO(id);
		
		return new ResponseEntity<ParkingTicketDTO>(parkingTicketDTO, HttpStatus.OK);

	}

	@GetMapping
	public ResponseEntity<List<ParkingTicketDTO>> getParkingTickects() {

		List<ParkingTicketDTO> parkingTicketDTOs = parkingTicketService.getparkingTicketDTOs();

	
		return new ResponseEntity<List<ParkingTicketDTO>>(parkingTicketDTOs, HttpStatus.OK);

	}

	@PostMapping("/toRegisterEntry")
	public ResponseEntity<String> toRegisterEntry(@RequestBody ParkingTicketDTO parkingTicketDTO) {

		try {
			
			parkingTicketService.toRegisterEntry(parkingTicketDTO);
			
		} catch (OverNumberVehiclesException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (PlateStartsWithAException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (EmptyPlateException e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}catch(Exception e) {
			e.printStackTrace();
		}

		return ResponseEntity.status(HttpStatus.OK).body("The entry was registered");
	}

	@GetMapping("/toCheckOut/{id}")
	public ResponseEntity<ParkingTicketDTO> toCheckOut(@PathVariable int id) {

		ParkingTicketDTO  parkingTicketDTO = null;
		
		try {
			parkingTicketDTO = parkingTicketService.toCheckOut(id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(parkingTicketDTO);
	}

}
