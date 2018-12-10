package com.vamosaprogramar.CeibaEstacionamiento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vamosaprogramar.CeibaEstacionamiento.entity.ParkingTicket;
import com.vamosaprogramar.CeibaEstacionamiento.service.ParkingTicketService;

@RestController
@RequestMapping("parkingtickets")
public class ParkingTicketController {

	@Autowired
	private ParkingTicketService parkingTicketService;
	
	@GetMapping("{id}")
	public ResponseEntity<ParkingTicket> getParkingTickect(@PathVariable int id){
		
		ParkingTicket parkingTicket = parkingTicketService.getParkingTicket(id);
		
		return new ResponseEntity<ParkingTicket>(parkingTicket, HttpStatus.OK);
	
	}
	
	@GetMapping
	public ResponseEntity<List<ParkingTicket>> getParkingTickects(){
		
		List<ParkingTicket> parkingTickets = parkingTicketService.getParkingTickets();
		
		return new ResponseEntity<List<ParkingTicket>>(parkingTickets, HttpStatus.OK);
	
	}
	
	
	@PostMapping("/toRegisterEntry")
	public ResponseEntity<String> toRegisterEntry(@RequestBody ParkingTicket parkingTicket){
		try {
			parkingTicketService.toRegisterEntry(parkingTicket);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		 return ResponseEntity.status(HttpStatus.OK)
			        .body("The entry was registered");
	} 
	
	@PostMapping("/toCheckOut")
	public ResponseEntity<String> toCheckOut(@RequestBody ParkingTicket parkingTicket){
		
		try {
			parkingTicketService.toCheckOut(parkingTicket);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		 return ResponseEntity.status(HttpStatus.OK)
			        .body("Checkout was done");
	}
	
	
}
