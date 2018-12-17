package com.vamosaprogramar.CeibaEstacionamiento.dto;

import java.time.LocalDateTime;
import java.util.Optional;



public class ParkingTicketDTO {

	private Integer id;

	private String vehicleType;

	private String vehiclePlate;

	private int vehicleCylinderCapacity;

	private LocalDateTime startDate;

	private LocalDateTime finishDate;

	private Double total;

	private String status;

	public ParkingTicketDTO() {
		
	}
	public Optional<Integer> getOptionalId() {
		return Optional.ofNullable(id);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Optional<String> getOptionalVehicleType() {
		return Optional.ofNullable(vehicleType);
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public Optional<String> getOptionalVehiclePlate() {
		return Optional.ofNullable(vehiclePlate);
	}

	public String getVehiclePlate() {
		return vehiclePlate;
	}

	public void setVehiclePlate(String vehiclePlate) {
		this.vehiclePlate = vehiclePlate;
	}

	public Optional<Integer> getOptionalVehicleCylinderCapacity() {
		return Optional.ofNullable(vehicleCylinderCapacity);
	}

	public int getVehicleCylinderCapacity() {
		return vehicleCylinderCapacity;
	}

	public void setVehicleCylinderCapacity(int vehicleCylinderCapacity) {
		this.vehicleCylinderCapacity = vehicleCylinderCapacity;
	}

	public Optional<LocalDateTime> getOptionalStartDate() {
		return Optional.ofNullable(startDate);
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public Optional<LocalDateTime> getOptinalFinishDate() {
		return Optional.ofNullable(finishDate);
	}

	public LocalDateTime getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(LocalDateTime finishDate) {
		this.finishDate = finishDate;
	}

	public Optional<Double> getOptionalTotal() {
		return Optional.ofNullable(total);
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
	public Optional<String> getOptionalStatus(){
		return Optional.ofNullable(status);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
