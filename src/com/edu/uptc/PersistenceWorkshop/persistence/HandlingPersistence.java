package com.edu.uptc.PersistenceWorkshop.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.edu.uptc.PersistenceWorkshop.Interface.IActionsFile;
import com.edu.uptc.PersistenceWorkshop.constants.CommonConstants;
import com.edu.uptc.PersistenceWorkshop.enums.ETypeFiLe;
import com.edu.uptc.PersistenceWorkshop.enums.ETypeVehicle;
import com.edu.uptc.PersistenceWorkshop.model.*;

public class HandlingPersistence extends FilePlain implements IActionsFile {
	private List<VehicleRate> listVehicleRate;
	private List<User> listUser;
	private List<Vehicle> listVehicle;
	private List<RecordParking> listRecordParking;

	public HandlingPersistence() {
		this.listRecordParking = new ArrayList<>();
		this.listUser = new ArrayList<>();
		this.listVehicle = new ArrayList<>();
		this.listVehicleRate = new ArrayList<>();
	}

	/** Metodo para añadir un nuevo usuario. */
	public Boolean addUser(User user) {
		if (Objects.isNull(this.findUsers(user.getUserName(), user.getPassword()))) {
			this.listUser.add(user);
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	/** Metodo que se encarga de verficar que no existan dos usuarios iguales. */
	public User findUsers(String userName, String password) {
		for (User user : this.listUser) {
			if (user.getUserName().contentEquals(userName) && user.getPassword().contentEquals(password)) {
				return user;
			}
		}
		return null;
	}

	/** Metodo que añade un carro por placa y se encarga de que sea unico */
	public Boolean addVehicle(Vehicle vehicle) {
		if (Objects.isNull(this.findVehicleByLicensePlate(vehicle.getLicensePlate()))) {
			this.listVehicle.add(vehicle);
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	/** Metodo que se encarga de verficar que no existan dos vehiculos iguales. */
	public Vehicle findVehicleByLicensePlate(String licensePlate) {
		for (Vehicle vehicle : this.listVehicle) {
			if (vehicle.getLicensePlate().contentEquals(licensePlate)) {
				return vehicle;
			}
		}
		return null;
	}

	/**
	 * Metodo que se encarga de añadir un nuevo registro de parqueo y que sea unico.
	 */
	public Boolean addRecordParking(RecordParking recordParking) {
		if (Objects.isNull(this.findRecordParkingByLicensePlatebyEntreTime(recordParking.getLicensePlate(),
				recordParking.getEntryTime()))) {
			this.listRecordParking.add(recordParking);
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	/** Metodo que se encarga de verficar que no existan dos vehiculos iguales. */
	public RecordParking findRecordParkingByLicensePlatebyEntreTime(String licensePlate, String entryTime) {
		for (RecordParking recordParking : this.listRecordParking) {
			if (recordParking.getLicensePlate().contentEquals(licensePlate)
					&& recordParking.getEntryTime().contentEquals(entryTime)) {
				return recordParking;
			}
		}
		return null;
	}

	/** Metodo que se encarda de volcar la informacion. */
	@Override
	public void dumpFile(ETypeFiLe eTypeFiLe) {
		if (ETypeFiLe.CSV.equals(eTypeFiLe)) {
			String nameFileCSV = config.getVehicleRateCSV();
			this.dumpFilePlainVehicleRate(nameFileCSV);
		}
		if (ETypeFiLe.SER.equals(eTypeFiLe)) {
			dumpFileSerializateUser();
		}
		if (ETypeFiLe.XML.equals(eTypeFiLe)) {
			dumpFileXMLVehicle();
		}
		if (ETypeFiLe.JSON.equals(eTypeFiLe)) {
			dumpFileJSONRecordParking();
		}
	}

	/** Metodo para cargar la informacion. */
	@Override
	public void loadFile(ETypeFiLe eTypeFiLe) {
		if (ETypeFiLe.CSV.equals(eTypeFiLe)) {
			String nameFileCSV = config.getVehicleRateCSV();
			this.loadFilePlainVehicleRate(nameFileCSV);
		}
		if (ETypeFiLe.SER.equals(eTypeFiLe)) {
			loadFileSerializateUser();
		}
		if (ETypeFiLe.XML.equals(eTypeFiLe)) {
			loadFileXMLVehicle();
		}
		if (ETypeFiLe.JSON.equals(eTypeFiLe)) {
			loadFileJSORecordParking();
		}

	}

	public void loadFileTotal() {
		loadFile(ETypeFiLe.CSV);

		if (config.getRecordParkingJSON() != null) {
			File jsonFile = new File(config.getPathFile().concat(config.getRecordParkingJSON()));
			if (jsonFile.exists()) {
				loadFile(ETypeFiLe.JSON);
			}
		}

		if (config.getUseSER() != null) {
			File serFile = new File(config.getPathFile().concat(config.getUseSER()));
			if (serFile.exists()) {
				loadFile(ETypeFiLe.SER);
			}
		}

		if (config.getVehicleXML() != null) {
			File xmlFile = new File(config.getPathFile().concat(config.getVehicleXML()));
			if (xmlFile.exists()) {
				loadFile(ETypeFiLe.XML);
			}
		}
	}

	/** Metodo que guarda la informacion de un usuario en tipo ser */
	private void dumpFileSerializateUser() {
		try (FileOutputStream fileOut = new FileOutputStream(this.config.getPathFile().concat(config.getUseSER()));
				ObjectOutput out = new ObjectOutputStream(fileOut)) {
			out.writeObject(this.listUser);
		} catch (IOException i) {
			i.printStackTrace();
		}

	}

	/**
	 * Metodo que carga la informacion en tipo ser Observacion: La clase o el objeto
	 * tiene que implementar la libreria Serializable.
	 */
	@SuppressWarnings("unchecked")
	private void loadFileSerializateUser() {
		try (FileInputStream fileIn = new FileInputStream(this.config.getPathFile().concat(this.config.getUseSER()));
				ObjectInputStream in = new ObjectInputStream(fileIn)) {
			this.listUser = (List<User>) in.readObject();
		} catch (IOException i) {
			i.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	/** Metodo encargado de volcar la informacion vehiclerate en tipo csv */
	private void dumpFilePlainVehicleRate(String nameFileCSV) {
		StringBuilder rutaArchivo = new StringBuilder();
		rutaArchivo.append(config.getPathFile());
		rutaArchivo.append(nameFileCSV);
		List<String> records = new ArrayList<>();

		for (VehicleRate vehicleRate : this.listVehicleRate) {
			StringBuilder contentBeer = new StringBuilder();
			contentBeer.append(vehicleRate.getTypeVehicle()).append(CommonConstants.SEMICOLON);
			contentBeer.append(vehicleRate.getPrice()).append(CommonConstants.SEMICOLON);
			records.add(contentBeer.toString());
		}
		this.writer(rutaArchivo.toString(), records);

	}

	/** Metodo encargado de cargar la informacion vehiclerate en tipo csv */
	private void loadFilePlainVehicleRate(String nameFileCSV) {
		List<String> contentInLine = this.reader(config.getPathFile().concat(nameFileCSV));
		contentInLine.forEach(row -> {
			StringTokenizer tokens = new StringTokenizer(row, CommonConstants.SEMICOLON);
			while (tokens.hasMoreElements()) {
				String typeVehicle = tokens.nextToken();
				long price = Long.parseLong(tokens.nextToken().replace(",", "."));
				this.listVehicleRate.add(new VehicleRate(typeVehicle, price));
			}
		});

	}

	private void dumpFileXMLVehicle() {
		String rutaArchivo = config.getPathFile().concat(config.getVehicleXML());

		List<String> records = new ArrayList<String>();
		records.add("<XML version= \"1.0\" encoding =\"UTF-8\"> \n");// se inicia la etiqueta del inicio de xml
		for (Vehicle vehicle : this.listVehicle) {
			records.add("<Vehicle>");
			records.add("\t<LicensePlate>" + vehicle.getLicensePlate() + "</LicensePlate>");
			records.add("\t<Type>" + vehicle.geteTypeVehicle() + "</Type>");
			records.add("\t<Owner>" + vehicle.getOwner() + "</Owner>");
			records.add("\t<Model>" + vehicle.getModel() + "</Model>");
			records.add("\t<Color>" + vehicle.getColor() + "</Color>");
			records.add("\t<PricePerHour>" + vehicle.getPricePerHour() + "</PricePerHour> \n");
			records.add("</Vehicle>");
		}
		records.add("</XML>");
		this.writer(rutaArchivo, records);

	}

	private void loadFileXMLVehicle() {
		try {
			File file = new File(config.getPathFile().concat(config.getVehicleXML()));
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(file);
			NodeList list = document.getElementsByTagName(CommonConstants.Name_Tag_Vehicle);
			for (int i = 0; i < list.getLength(); i++) {
				String licensePlate = document.getElementsByTagName("LicensePlate").item(i).getTextContent();
				String type = document.getElementsByTagName("Type").item(i).getTextContent();
				String owner = document.getElementsByTagName("Owner").item(i).getTextContent();
				String Model = document.getElementsByTagName("Model").item(i).getTextContent();
				String Color = document.getElementsByTagName("Color").item(i).getTextContent();
				String PricePerHour = document.getElementsByTagName("PricePerHour").item(i).getTextContent();

				Vehicle vehicle = new Vehicle(licensePlate, ETypeVehicle.valueOf(type), owner, Model, Color);
				vehicle.setPricePerHour(Integer.parseInt(PricePerHour));
				this.listVehicle.add(vehicle);
			}

		} catch (Exception e) {
			System.out.println("No se puedo leer el XML");
		}

	}

	private void dumpFileJSONRecordParking() {
		String rutaArchivo = config.getPathFile().concat(config.getRecordParkingJSON());
		StringBuilder json = null;
		List<String> content = new ArrayList<String>();
		content.add(CommonConstants.OPENING_BRACKET);
		int cont = 0;
		int total = listRecordParking.size();

		for (RecordParking r : this.listRecordParking) {
			json = new StringBuilder();
			json.append("{");
			json.append(" \"LicensePlate\":\"").append(escape(r.getLicensePlate())).append("\",");
			json.append(" \"EntryTime\":\"").append(escape(String.valueOf(r.getEntryTime()))).append("\",");
			json.append(" \"DepartureTime\":\"").append(escape(String.valueOf(r.getDepartureTime()))).append("\",");
			json.append(" \"Total\":").append(r.getTotal());
			json.append("}");

			cont++;
			if (cont < total) {
				json.append(",");
			}
			content.add(json.toString());
		}

		content.add(CommonConstants.CLOSING_BRACKET);
		this.writer(rutaArchivo, content);

	}

	private String escape(String value) {
		if (value == null)
			return "";
		return value.replace("\\", "\\\\").replace("\"", "\\\"");
	}

	private void loadFileJSORecordParking() {
		List<String> contentInLine = this.reader(config.getPathFile().concat(config.getRecordParkingJSON())).stream()
				.filter(line -> !line.equals("[") && !line.equals("]") && !line.equals(CommonConstants.BREAK_LINE)
						&& !line.trim().isEmpty() && !line.trim().isBlank())
				.collect(Collectors.toList());

		for (String line : contentInLine) {
			line = line.replace("{", "").replace("},", "").replace("}", "");
			StringTokenizer tokens = new StringTokenizer(line, ",");

			while (tokens.hasMoreElements()) {
				String LicensePlate = this.escapeValue(tokens.nextToken().split(":", 2)[1]);
				String EntryTimeand = this.escapeValue(tokens.nextToken().split(":", 2)[1]);
				String DepartureTimeype = this.escapeValue(tokens.nextToken().split(":", 2)[1]);
				long total = Long.parseLong(this.escapeValue(tokens.nextToken().split(":", 2)[1]));

				RecordParking record = new RecordParking(LicensePlate, EntryTimeand, DepartureTimeype);
				record.setTotal(total);
				this.listRecordParking.add(record);

			}
		}

	}

	private String escapeValue(String value) {
		return value.replace("\"", "");
	}

	/** Metodo encargado de devolver la capacidad actual del parqueadero. */
	public int getParkingCapacity() {
		return config.getParkingCapacity() - getUsedSpots();
	}

	/**
	 * Metodo que se llama para calcualar cuantos vehiculos hay dentro del
	 * parqueadero actualmente
	 */
	public int getUsedSpots() {
		int count = 0;
		for (RecordParking record : this.listRecordParking) {
			if (record.getDepartureTime() == null) {
				count++;
			}
		}
		return count;
	}

	public long priceVehicle(ETypeVehicle eTypeVehicle) {
		for (VehicleRate vR : this.listVehicleRate) {
			if (eTypeVehicle.equals(ETypeVehicle.valueOf(vR.getTypeVehicle()))) {
				return vR.getPrice();
			}

		}
		return 0;
	}

	/** Getters y Setters de las distintias listas */

	public List<RecordParking> getListRecordParking() {
		return listRecordParking;
	}

	public void setListRecordParking(List<RecordParking> listRecordParking) {
		this.listRecordParking = listRecordParking;
	}

	public List<User> getListUser() {
		return listUser;
	}

	public void setListUser(List<User> listUser) {
		this.listUser = listUser;
	}

	public List<Vehicle> getListVehicle() {
		return listVehicle;
	}

	public void setListVehicle(List<Vehicle> listVehicle) {
		this.listVehicle = listVehicle;
	}

	public List<VehicleRate> getListVehicleRate() {
		return listVehicleRate;
	}

	public void setListVehicleRate(List<VehicleRate> listVehicleRate) {
		this.listVehicleRate = listVehicleRate;
	}

}
