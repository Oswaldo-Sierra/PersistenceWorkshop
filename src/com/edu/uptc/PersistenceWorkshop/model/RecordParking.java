package com.edu.uptc.PersistenceWorkshop.model;

import java.time.LocalDate;

public class RecordParking {
	private String licensePlate;
	
	private LocalDate entryTime;
	
	private LocalDate departureTime;
	
	private int total; // dato no ingresado por el usuario	
	
	public RecordParking(String licensePlate, LocalDate entryTime, LocalDate departureTime) {
		super();
		this.licensePlate = licensePlate;
		this.entryTime = entryTime;
		this.departureTime = departureTime;
	}
	

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public LocalDate getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(LocalDate entryTime) {
		this.entryTime = entryTime;
	}

	public LocalDate getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalDate departureTime) {
		this.departureTime = departureTime;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	
}
