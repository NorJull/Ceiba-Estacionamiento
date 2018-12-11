package com.vamosaprogramar.CeibaEstacionamiento.entity;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateTimeConverter;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "parking_ticket")
public class ParkingTicket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "vehicle_type")
	private String vehicleType;

	@Column(name = "vehicle_plate")
	private String vehiclePlate;

	@Column(name = "vehicle_cylinder_capacity")
	private int vehicleCylinderCapacity;

	@Column(name = "start_date")
	@Convert(converter = LocalDateTimeConverter.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDateTime startDate;

	@Column(name = "finish_date")
	@Convert(converter = LocalDateTimeConverter.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDateTime finishDate;

	@Column(name = "total")
	private Double total;

	@Column(name = "status")
	private String status;
	
	public ParkingTicket() {

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

	public double getVehicleCylinderCapacity() {
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

	@Override
	public String toString() {
		return "ParkingTicket [id=" + id + ", vehicleType=" + vehicleType + ", vehiclePlate=" + vehiclePlate
				+ ", vehicleCylinderCapacity=" + vehicleCylinderCapacity + ", startDate=" + startDate + ", finishDate="
				+ finishDate + ", total=" + total + ", status=" + status + "]";
	}

	
}
