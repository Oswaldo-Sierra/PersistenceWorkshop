package com.edu.uptc.PersistenceWorkshop.model;

public class Vehicle {
	private String licensePlate;
	
	private String type;
	
	private String owner; //dueño del vehiculo
	
	private String model;
	
	private String color;
	
	private int pricePerHour;//no ingresado por usuario

	public Vehicle(String licensePlate, String type, String owner, String model, String color) {
		this.licensePlate = licensePlate;
		this.type = type;
		this.owner = owner;
		this.model = model;
		this.color = color;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public int getPricePerHour() {
		return pricePerHour;
	}

	public void setPricePerHour(int pricePerHour) {
		this.pricePerHour = pricePerHour;
	}
	
	
	
	

}
