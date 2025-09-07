package com.edu.uptc.PersistenceWorkshop.Model;

import com.edu.uptc.PersistenceWorkshop.Enums.ETypeVehicle;

public class Vehicle {
	private String licensePlate;

	private ETypeVehicle eTypeVehicle;

	private String owner; // dueño del vehiculo

	private String model;

	private String color;

	private long pricePerHour;// no ingresado por usuario

	public Vehicle(String licensePlate, ETypeVehicle eTypeVehicle, String owner, String model, String color) {
		this.licensePlate = licensePlate;
		this.eTypeVehicle = eTypeVehicle;
		this.owner = owner;
		this.model = model;
		this.color = color;
	}

	public ETypeVehicle geteTypeVehicle() {
		return eTypeVehicle;
	}

	public void seteTypeVehicle(ETypeVehicle eTypeVehicle) {
		this.eTypeVehicle = eTypeVehicle;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public long getPricePerHour() {
		return pricePerHour;
	}

	public void setPricePerHour(long pricePerHour) {
		this.pricePerHour = pricePerHour;
	}

}
