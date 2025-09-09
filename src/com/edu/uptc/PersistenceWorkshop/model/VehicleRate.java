package com.edu.uptc.PersistenceWorkshop.model;

public class VehicleRate {
	
	private String typeVehicle;
	
	private long price;
	

	public VehicleRate(String typeVehicle, long price) {
		this.typeVehicle = typeVehicle;
		this.price = price;
	}

	public String getTypeVehicle() {
		return typeVehicle;
	}

	public void setTypeVehicle(String typeVehicle) {
		this.typeVehicle = typeVehicle;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}
	
	
}
