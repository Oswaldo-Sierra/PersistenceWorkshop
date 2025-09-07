package com.edu.uptc.PersistenceWorkshop.Config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class Config {
	private static Config config;
	
	private String pathFile;
	
	private String vehicleRateCSV;
	
	private String userSER;
	
	private String vehicleXML;
	
	private String recordParkingJSON;
	
	private String parkingCapacity;
	
	private Properties properties;
	
	private Config() {
		this.properties = new Properties();
		try (FileInputStream file = new FileInputStream("resources/config/appconfig.properties")){
			properties.load(file);
			this.pathFile = properties.getProperty("app.config.path.file");
			this.vehicleRateCSV = properties.getProperty("app.config.path.file.vehiculeRate.csv");
			this.userSER = properties.getProperty("app.config.path.file.user.ser");
			this.vehicleXML = properties.getProperty("app.config.path.file.vehicle.xml");
			this.recordParkingJSON = properties.getProperty("app.config.path.file.recordParking.json");
			this.parkingCapacity = properties.getProperty("app.config.path.file.parkingCapacity");
			
		} catch (IOException e) {
			System.out.println("Error al cargar el archivo properties de configuración: " + e.getMessage());
		}
		
	}
	
	public static Config getInstance() {
		if(Objects.isNull(config)) {
			config = new Config();
		}
		return config;
	}
	
	

	public int getParkingCapacity() {
		return Integer.parseInt(parkingCapacity);
	}

	public void setParkingCapacity(String parkingCapacity) {
		this.parkingCapacity = parkingCapacity;
	}

	public String getPathFile() {
		return pathFile;
	}

	public void setPathFile(String pathFile) {
		this.pathFile = pathFile;
	}

	public String getVehicleRateCSV() {
		return vehicleRateCSV;
	}

	public void setVehicleRateCSV(String vehicleRateCSV) {
		this.vehicleRateCSV = vehicleRateCSV;
	}

	public String getUseSER() {
		return userSER;
	}

	public void setUseSER(String useSER) {
		this.userSER = useSER;
	}

	public String getVehicleXML() {
		return vehicleXML;
	}

	public void setVehicleXML(String vehicleXML) {
		this.vehicleXML = vehicleXML;
	}

	public String getRecordParkingJSON() {
		return recordParkingJSON;
	}

	public void setRecordParkingJSON(String recordParkingJSON) {
		this.recordParkingJSON = recordParkingJSON;
	}

	

}
