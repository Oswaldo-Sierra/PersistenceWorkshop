package com.edu.uptc.PersistenceWorkshop.model;

public class VehicleRate {
	private String typeVehicle;
	
	private int price;
	

	public VehicleRate(String typeVehicle, int price) {
		this.typeVehicle = typeVehicle;
		this.price = price;
	}

	public String getTypeVehicle() {
		return typeVehicle;
	}

	public void setTypeVehicle(String typeVehicle) {
		this.typeVehicle = typeVehicle;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
}
