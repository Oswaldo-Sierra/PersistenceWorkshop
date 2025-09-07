package com.edu.uptc.PersistenceWorkshop.Model;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class RecordParking {
	private String licensePlate;

	private LocalDateTime entryTime;

	private LocalDateTime departureTime;

	private long total; // dato no ingresado por el usuario

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

	public RecordParking(String licensePlate, String entryTime, String departureTime) {
		this.licensePlate = licensePlate;
		this.entryTime = LocalDateTime.parse(entryTime, formatter);
		this.departureTime = (departureTime != null && !departureTime.isEmpty()  && !"null".equalsIgnoreCase(departureTime))
				? LocalDateTime.parse(departureTime, formatter)
				: null;

	}

	public RecordParking(String licensePlate, LocalDateTime entryTime) {
		this.licensePlate = licensePlate;
		this.entryTime = entryTime;
		this.departureTime = null;
	}
	
	

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getEntryTime() { 
		return entryTime.format(formatter);
	}

	public void setEntryTime(LocalDateTime entryTime) {
		this.entryTime = entryTime;
	}

	public String getDepartureTime() {
		if(!Objects.isNull(departureTime)) {
			return departureTime.format(formatter);
		}
		return null;
	}

	public void setDepartureTime(LocalDateTime departureTime) {
		 if (departureTime == null) {
		        throw new IllegalArgumentException("La fecha de salida no puede ser nula.");
		    }
		    if (!departureTime.isAfter(entryTime)) {
		        throw new IllegalArgumentException("La fecha de salida debe ser posterior a la de entrada.");
		    }
	    
	    this.departureTime = departureTime;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public DateTimeFormatter getFormatter() {
		return formatter;
	}

	public void setFormatter(DateTimeFormatter formatter) {
		this.formatter = formatter;
	}
	
	

}
