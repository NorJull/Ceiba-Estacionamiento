package com.vamosaprogramar.CeibaEstacionamiento.entity;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;


import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateTimeConverter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vamosaprogramar.CeibaEstacionamiento.exception.PlateStartsWithAException;

@Entity
@Table(name = "parking_ticket")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
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
	protected int vehicleCylinderCapacity;

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
	
	public boolean validateConcurrentVehicles(int numberConcurrentVehicles) {
		return true;
	}
	
	public void validateVehiclePlate() throws PlateStartsWithAException {
		if (vehiclePlate.startsWith("A")) {
			DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();

			if (!(dayOfWeek == DayOfWeek.SUNDAY || dayOfWeek == DayOfWeek.MONDAY))
				throw new PlateStartsWithAException();

		}
	}

	public double calculateTotalToPay(long hours) {
		
		return 0.0;
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

	@Override
	public String toString() {
		return "ParkingTicket [id=" + id + ", vehicleType=" + vehicleType + ", vehiclePlate=" + vehiclePlate
				+ ", vehicleCylinderCapacity=" + vehicleCylinderCapacity + ", startDate=" + startDate + ", finishDate="
				+ finishDate + ", total=" + total + ", status=" + status + "]";
	}

	
}
