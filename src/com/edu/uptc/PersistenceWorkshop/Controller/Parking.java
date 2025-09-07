package com.edu.uptc.PersistenceWorkshop.Controller;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.edu.uptc.PersistenceWorkshop.Enums.EParkingStatus;
import com.edu.uptc.PersistenceWorkshop.Enums.ETypeFiLe;
import com.edu.uptc.PersistenceWorkshop.Enums.ETypeVehicle;
import com.edu.uptc.PersistenceWorkshop.Model.*;
import com.edu.uptc.PersistenceWorkshop.Persistence.HandlingPersistence;

public class Parking {

	private HandlingPersistence handlingPersistence;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

	public Parking() {
		handlingPersistence = new HandlingPersistence();
		this.handlingPersistence.loadFileTotal();
	}

	/** Metodo encargado de añadir un usuario */
	public Boolean addUser(String nameU, String password) {
		User user = new User(nameU, password);
		if (this.handlingPersistence.addUser(user)) {
			this.handlingPersistence.dumpFile(ETypeFiLe.SER);
			return true;
		}
		return false;

	}

	/** Metodos encargados para el crudo del vehicle. */

	/** Metodo encargado de devolver los tipos de vehiculos que hay actualmente. */
	public String menuvehicle() {
		StringBuilder b = new StringBuilder();
		b.append("Seleccione el tipo de vehiculo:\n");
		int size = this.handlingPersistence.getListVehicleRate().size();
		for (int i = 0; i < size; i++) {
			b.append((i + 1)).append(". ").append(this.handlingPersistence.getListVehicleRate().get(i).getTypeVehicle())
					.append("\n");
		}
		return b.toString();
	}

	/** Metodo encargado de añadir el vehiculo. */
	public String addVehicle(String licensePlate, ETypeVehicle eTypeVehicle, String owner, String model, String color) {
		Vehicle vehicle = new Vehicle(licensePlate, eTypeVehicle, owner, model, color);
		vehicle.setPricePerHour(this.handlingPersistence.priceVehicle(eTypeVehicle));
		if (this.handlingPersistence.addVehicle(vehicle)) {
			this.handlingPersistence.dumpFile(ETypeFiLe.XML);
			return "Vehiculo añadido correctamente";
		} else {
			return "Se presento un problema al momento de añadir el vehiculo";
		}

	}

	/** Metodo que se encaga de eliminar el vehiculo. */
	public String delateVehicle(String licensePlate) {
		Vehicle v = this.handlingPersistence.findVehicleByLicensePlate(licensePlate);
		this.handlingPersistence.getListVehicle().remove(v);
		this.handlingPersistence.dumpFile(ETypeFiLe.XML);
		return "Vehículo eliminado correctamente.";

	}

	/** Metodo encargado de actualizar un vehicle. */
	public String udateVehicle(String licensePlate, String newOwner, String newColor) {
		Vehicle v = this.handlingPersistence.findVehicleByLicensePlate(licensePlate);

		if (v != null) {
			if (newOwner != null && newColor != null) {
				v.setColor(newColor);
				v.setOwner(newOwner);
				this.handlingPersistence.dumpFile(ETypeFiLe.XML);
				return "Datos actulizados del vehiculo con la palca: " + licensePlate;
			} else if (newOwner != null) {
				v.setOwner(newOwner);
				this.handlingPersistence.dumpFile(ETypeFiLe.XML);
				return "Actualizado el nuevo dueño del vehiculo con la placa " + licensePlate;
			} else if (newColor != null) {
				v.setColor(newColor);
				this.handlingPersistence.dumpFile(ETypeFiLe.XML);
				return "Actualizado el nuevo color del vehiculo con la placa " + licensePlate;
			} else {
				return "No se proporcionaron datos para actualizar el vehículo con placa " + licensePlate;
			}
		} else {
			return "Vehículo con placa " + licensePlate + " no encontrado.";
		}
	}

	/** Metodo encargado de mostrar vehiculos */
	public String viewVehicle() {
		StringBuilder vehiclesRegistered = new StringBuilder();

		vehiclesRegistered.append("| N° |").append("| Placa |").append("| Tipo |").append("| Dueño |")
				.append("| Modelo |").append("| Color |").append("| Precio por Hora|\n");
		for (int i = 0; i < this.handlingPersistence.getListVehicle().size(); i++) {
			Vehicle v = this.handlingPersistence.getListVehicle().get(i);
			vehiclesRegistered.append("| ").append(i + 1).append(" | ").append(v.getLicensePlate()).append(" | ")
					.append(v.geteTypeVehicle()).append(" | ").append(v.getOwner()).append(" | ").append(v.getModel())
					.append(" | ").append(v.getColor()).append(" | ").append(v.getPricePerHour()).append(" |\n");
		}

		return vehiclesRegistered.toString();

	}

	/** Metodos encargados de hacer el crud para el recordParking. */

	/** Metodo que se encarga de añadir un nuevo recordParking . */
	public String addRecordParking(String licensePlate, LocalDateTime entryTime) {
		if (this.handlingPersistence.getParkingCapacity() <= 0) {
			return "No hay cupos disponibles.";
		}

		RecordParking record = new RecordParking(licensePlate, entryTime);
		this.handlingPersistence.getListRecordParking().add(record);
		this.handlingPersistence.dumpFile(ETypeFiLe.JSON);
		return "Vehículo con placa " + licensePlate + " registrado. \nCupos disponibles: "
				+ this.handlingPersistence.getParkingCapacity();
	}

	/** Metodo que se encarga de eliminar un recordParking. */
	public String delateRecordParking(String licensePlate, String entryTime) {
		RecordParking record = this.handlingPersistence.findRecordParkingByLicensePlatebyEntreTime(licensePlate,
				entryTime);

		if (record == null) {
			return "No se encontró un registro con esa placa y fecha de entrada.";
		}

		if (record.getDepartureTime() == null) {
			this.handlingPersistence.getListRecordParking().remove(record);
			this.handlingPersistence.dumpFile(ETypeFiLe.JSON);
			return "Vehículo eliminado correctamente." + "\nCupos disponibles: "
					+ this.handlingPersistence.getParkingCapacity();
		} else {
			return "EL Vehiculo no se puede eliminar\nYa que este cuanta con fecha de salida registrada.";
		}
	}

	/** Metodo encargado de actualizar un recordParking. */
	public String updateRecordParking(String licensePlate, LocalDateTime entryTime, LocalDateTime departureTime) {

		RecordParking r = this.handlingPersistence.findRecordParkingByLicensePlatebyEntreTime(licensePlate,
				entryTime.format(formatter));
		Vehicle v = this.handlingPersistence.findVehicleByLicensePlate(licensePlate);

		if (r != null) {
			if (departureTime != null) {
				r.setDepartureTime(departureTime);

				long hours = durationCalculation(entryTime, departureTime);

				r.setTotal((v.getPricePerHour() * hours));
				this.handlingPersistence.dumpFile(ETypeFiLe.JSON);
				return "Salida registrada para la placa " + licensePlate + " Precio total a pagar: " + r.getTotal()
						+ "\nCupos disponibles: " + this.handlingPersistence.getParkingCapacity();
			} else {
				return "Error: la fecha de salida no puede ser nula o vacía.";
			}
		} else {
			return "El registro de parqueo con la placa: " + licensePlate + " y fecha de entrada " + entryTime
					+ " no encontrado.";
		}
	}

	/** Calculo de precio por hora */
	private long durationCalculation(LocalDateTime entryTime, LocalDateTime departureTime) {
		if (entryTime != null && departureTime != null) {
			Duration duration = Duration.between(entryTime, departureTime);
			return duration.toHours();
		}
		return 0;
	}

	/** Metodos que muestran lo registros de informacion. */

	public String vehicleRecordByDate(LocalDate date) {
		StringBuilder vDate = new StringBuilder();

		int count = 1;
		for (RecordParking record : this.handlingPersistence.getListRecordParking()) {
			LocalDateTime entryDateTime = LocalDateTime.parse(record.getEntryTime(), formatter);

			if (entryDateTime.toLocalDate().equals(date)) {
				if (count == 1) {
					vDate.append("Los reportes de parqueo para la fecha: ").append(date).append(" son:\n");
					vDate.append("| N° | Placa | Hora de entrada | Hora de salida |\n");
				}

				String departureHour = "----";
				if (record.getDepartureTime() != null && !record.getDepartureTime().isEmpty()) {
					LocalDateTime departureDateTime = LocalDateTime.parse(record.getDepartureTime(), formatter);
					departureHour = departureDateTime.toLocalTime().toString();
				}

				vDate.append("| ").append(count++).append(" | ").append(record.getLicensePlate()).append(" | ")
						.append(entryDateTime.toLocalTime()).append(" | ").append(departureHour).append(" |\n");
			}
		}

		if (count == 1) {
			return "No se encontraron reportes para la fecha: " + date + "\n";
		}

		return vDate.toString();
	}

	public String moneyCollectedDay(LocalDate date) {
		long moneyTotal = 0;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

		for (RecordParking record : this.handlingPersistence.getListRecordParking()) {
			LocalDateTime entryDateTime = LocalDateTime.parse(record.getEntryTime(), formatter);

			if (entryDateTime.toLocalDate().equals(date)) {
				moneyTotal += record.getTotal();
			}
		}

		if (moneyTotal == 0) {
			return "No se registraron ingresos en la fecha: " + date + "\n";
		}

		return "El dinero que se recaudó el día " + date + " fue: " + moneyTotal + "\n";
	}

	/** Validaciones necesarias. */

	/** Metodo encargado de validar la existencia de un usuario */
	public Boolean validateUser(String nameU, String password) {
		return this.handlingPersistence.findUsers(nameU, password) != null;
	}

	/** Metodo encargado de validar si el vehiculo existe y este es unico */
	public Boolean validateVehicle(String licensePlate) {
		return this.handlingPersistence.findVehicleByLicensePlate(licensePlate) != null;
	}

	/** Metodo encargado de validar si el recordParking existe y este es unico */
	public EParkingStatus validateRecordParkingByEntryTime(String licensePlate, String entryTime) {
		RecordParking r = this.handlingPersistence.findRecordParkingByLicensePlatebyEntreTime(licensePlate, entryTime);

		if (r != null) {
			if (r.getDepartureTime() == null) {
				return EParkingStatus.INSIDE; // sigue dentro
			} else {
				return EParkingStatus.ALREADY_EXISTS; // ya salió
			}
		}
		return EParkingStatus.NOT_FOUND;
	}

}
