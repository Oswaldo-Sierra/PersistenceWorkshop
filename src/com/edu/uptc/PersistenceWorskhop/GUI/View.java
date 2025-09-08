package com.edu.uptc.PersistenceWorskhop.GUI;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.edu.uptc.PersistenceWorkshop.Controller.Parking;
import com.edu.uptc.PersistenceWorkshop.Enums.EParkingStatus;
import com.edu.uptc.PersistenceWorkshop.Enums.ETypeVehicle;

public class View {
	private Scanner sc = new Scanner(System.in);
	private Parking parking = new Parking();
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

	public void startMenu() {
		String loggeo = """
				Bienvenido
				Ingrese la opccion deseada(Solo el numero)
				1.Iniciar sesion
				2.Registrar usuario
				3.Salir
				""";
		do {
			System.out.println(loggeo);
			String op1 = sc.nextLine();
			switch (op1) {
			case "1":
				System.out.println("Ingrese su nombre de usuario");
				String nameUserI = sc.nextLine();
				System.out.println("Ingrese su contraseña");
				String passwordI = sc.nextLine();
				if (this.parking.validateUser(nameUserI, passwordI)) {
					System.out.println("Usuario válido");
					crudVehicle();
				} else {
					System.out.println("Usuario no registrado o contraseña incorrecta");
				}

				break;
			case "2":
				System.out.println("Ingrese su nombre de usuario");
				String nameUserR = sc.nextLine();
				System.out.println("Ingrese su contraseña");
				String passwordR = sc.nextLine();
				if (this.parking.addUser(nameUserR, passwordR)) {
					System.out.println("Usuario registrado");
					crudVehicle();
				} else {
					System.out.println("Usuario ya registrado");
				}
				break;
			case "3":
				System.out.println("Saliendo...");
				return;
			default:
				System.out.println("Opcion invalida");
				break;
			}
		} while (true);

	}

	public void crudVehicle() {
		String menu1 = """
				\nBienvindo al menu de Vehiculos,Ingrese la opccion deseada(Solo el numero)
				1.Añadir nuevo Vehiculo
				2.Eliminar un Vehiculo
				3.Actualizar un Vehiculo
				4.Ver Vehiculos registrados
				5.Ingresar a la lista de parqueo
				6.Cerrar sesion
				""";
		do {
			System.out.println(menu1);
			String op2 = sc.nextLine();
			switch (op2) {
			case "1":
				addVehicle();
				break;
			case "2":
				deleteVehicle();
				break;
			case "3":
				updateVehicle();
				break;
			case "4":
				showVehicles();
				break;
			case "5":
				recordParkingV();
				break;
			case "6":
				System.out.println("Cerrando sesion...");
				return;
			default:
				System.out.println("Opcion invalida");
				break;
			}
		} while (true);
	}

	/** Metodo que añade vehiculos. */
	private void addVehicle() {
		System.out.println("\nIngrese la informacion solicitada, para añadir el vehiculo");
		System.out.print("Matricula/Placa del vehiculo: ");
		String plate = sc.nextLine();
		if (this.parking.validateVehicle(plate)) {
			System.out.println("El vehiculo ya se encuentra registrado");
		} else {
			ETypeVehicle type = null;
			do {
				System.out.print(this.parking.menuvehicle());
				String option = sc.nextLine();
				switch (option) {
				case "1":
					type = ETypeVehicle.Carro;
					break;
				case "2":
					type = ETypeVehicle.Motocicleta;
					break;
				case "3":
					type = ETypeVehicle.Bicicleta;
					break;
				default:
					System.out.println("Opcion invalida");
				}
			} while (type == null);

			System.out.print("Dueño/Propietario del Vehiculo: ");
			String owner = sc.nextLine();
			System.out.print("Modelo del Vehiculo: ");
			String model = sc.nextLine();
			System.out.print("Color del vehiculo: ");
			String color = sc.nextLine();
			System.out.println(this.parking.addVehicle(plate, type, owner, model, color));
		}

	}

	/** Metodo encargado de eliminar un vehiculo. */
	private void deleteVehicle() {

		System.out.println("Ingrese la informacion solicitada, para Eliminar el vehiculo");

		System.out.print("Matricula/Placa del vehiculo: ");
		String plate = sc.nextLine();
		if (this.parking.validateVehicle(plate)) {
			System.out.println(this.parking.delateVehicle(plate));
		} else {
			System.out.println("El vehiculo no se encuetra registrado");
		}
	}

	/**
	 * Metodo que se encarga de actualizar algunos datos de un vehiculo en
	 * especifico.
	 */
	private void updateVehicle() {
		String updateV = """
				Ingrese que desea actualizar del vehiculo
				1.Dueño/Propietario
				2.Color
				3.Amabas
				""";

		System.out.println("Ingrese la informacion solicitada, para actualizar el vehiculo");

		System.out.print("Matricula/Placa del vehiculo: ");
		String plate = sc.nextLine();
		// Aquí deberías verificar que el vehículo exista antes de actualizarlo
		if (this.parking.validateVehicle(plate)) {
			System.out.println(updateV);
			String option = sc.nextLine();
			switch (option) {
			case "1":
				System.out.print("Ingrese el nuevo Dueño/Propietario del Vehiculo: ");
				String newOwner = sc.nextLine();
				System.out.println(this.parking.udateVehicle(plate, newOwner, null));
				break;
			case "2":
				System.out.print("Ingrese el nuevo Color del vehiculo: ");
				String newColor = sc.nextLine();
				System.out.println(this.parking.udateVehicle(plate, null, newColor));
				break;
			case "3":
				System.out.print("Ingrese el nuevo Dueño/Propietario del Vehiculo: ");
				String newOwner2 = sc.nextLine();
				System.out.print("Ingrese el nuevo Color del vehiculo: ");
				String newColor2 = sc.nextLine();
				System.out.println(this.parking.udateVehicle(plate, newOwner2, newColor2));
				break;
			default:
				System.out.println("Opcion invalida");
				break;
			}
		} else {

			System.out.println("El vehiculo no se encuetra registrado");

		}

	}

	/** Metodo que Muestra los vehiculos registrados. */
	private void showVehicles() {
		if (this.parking.numVehicles() != 0) {
			System.out.println("Los vehiculos registrados actualmente son:");
			System.out.println(this.parking.viewVehicle());

		}else {
			System.out.println("No hay vehiculos registrados");
		}
	}

	/** Metodo de crud para el recordParking. */
	private void recordParkingV() {
		String menu2 = """
				\nBienvenido al registro de parqueo,Ingrese la opccion deseada(Solo el numero)
				1.Añadir un nuevo registro de parqueo
				2.Eliminar un registro de parqueo
				3.Actualizar un registro de parqueo
				4.Ver los registros de parqueo actuales
				5.Ver la tasa de vehiculos actuales(Precio segun el vehiculo)
				6.Volver al menu anterior
				""";
		do {
			System.out.println(menu2);
			String op3 = sc.nextLine();
			switch (op3) {
			case "1":
				addRecordP();
				break;
			case "2":
				deleteRecordP();
				break;
			case "3":
				updateRecordP();
				break;
			case "4":
				viewRecordP();
				break;
			case "5":
				showVehicleRate();
				break;
			case "6":
				System.out.println("Volviendo al menu anterior...");
				return;
			default:
				System.out.println("Opcion invalida");
				break;
			}
		} while (true);

	}

	

	private void addRecordP() {

		System.out.println("Ingrese la información solicitada, para añadir un registro de parqueo");

		System.out.print("Matrícula/Placa del vehículo: ");
		String plate = sc.nextLine();

		LocalDateTime entryTime = null;

		while (entryTime == null) {
			try {
				System.out.print("Fecha y hora de entrada (dd/MM/yyyy HH:mm): ");
				String input = sc.nextLine();
				LocalDateTime tempEntry = LocalDateTime.parse(input, formatter);

				if (tempEntry.isBefore(LocalDateTime.now())) {
					System.out.println("La fecha de entrada no puede estar en el pasado.");
				} else {
					entryTime = tempEntry;
				}
			} catch (Exception e) {
				System.out.println("Formato inválido. Ejemplo: 06/09/2025 08:30");
			}
		}

		EParkingStatus status = this.parking.validateRecordParkingByEntryTime(plate, entryTime.format(formatter));
		if (status == EParkingStatus.INSIDE) {
			System.out.println("El vehículo ya está dentro del parqueadero.");
		} else if (status == EParkingStatus.ALREADY_EXISTS) {
			System.out.println("Ya existe un registro en esa fecha/hora, pero el vehículo ya salió.");
		} else {
			if (this.parking.validateVehicle(plate)) {
				String result = this.parking.addRecordParking(plate, entryTime);
				System.out.println(result);
			} else {
				System.out.println("Debe registrar el vehículo primero antes de parquearlo.");
			}
		}
	}

	private void deleteRecordP() {
		System.out.println("Ingrese la información solicitada, para Eliminar un registro de parqueo");

		System.out.print("Matrícula/Placa del vehículo: ");
		String plate = sc.nextLine();

		LocalDateTime entryTime = null;

		while (entryTime == null) {
			try {
				System.out.print("Fecha y hora de entrada (dd/MM/yyyy HH:mm): ");
				String input = sc.nextLine();
				LocalDateTime tempEntry = LocalDateTime.parse(input, formatter);

				if (tempEntry.isBefore(LocalDateTime.now())) {
					System.out.println("La fecha de entrada no puede estar en el pasado.");
				} else {
					entryTime = tempEntry;
				}
			} catch (Exception e) {
				System.out.println("Formato inválido. Ejemplo: 06/09/2025 08:30");
			}
		}

		EParkingStatus status = this.parking.validateRecordParkingByEntryTime(plate, entryTime.format(formatter));
		if (status == EParkingStatus.NOT_FOUND) {
			System.out.println("No existe un registro con esa placa y fecha de entrada.");
		} else {
			String result = this.parking.delateRecordParking(plate, entryTime.format(formatter));
			System.out.println(result);
		}
	}

	private void updateRecordP() {
		System.out.print("Matrícula/Placa del vehículo: ");
		String plate = sc.nextLine();

		LocalDateTime entryTime = null;

		// Pedir y validar la fecha de entrada
		while (entryTime == null) {
			try {
				System.out.print("Fecha y hora de entrada (dd/MM/yyyy HH:mm): ");
				String input = sc.nextLine();
				entryTime = LocalDateTime.parse(input, formatter);
			} catch (Exception e) {
				System.out.println("Formato inválido. Ejemplo correcto: 06/09/2025 08:30");
			}
		}
		EParkingStatus status = this.parking.validateRecordParkingByEntryTime(plate, entryTime.format(formatter));
		// Verifica primero si la placa ya está dentro del parqueadero
		if (status == EParkingStatus.INSIDE) {

			LocalDateTime departureTime = null;

			while (departureTime == null) {
				try {
					System.out.print("Fecha y hora de salida (dd/MM/yyyy HH:mm): ");
					String input2 = sc.nextLine();

					departureTime = LocalDateTime.parse(input2, formatter);

					String result = this.parking.updateRecordParking(plate, entryTime, departureTime);
					System.out.println(result);

				} catch (IllegalArgumentException ex) {
					System.out.println("Error " + ex.getMessage());
					departureTime = null;
				} catch (Exception e) {
					System.out.println("Formato inválido. Ejemplo correcto: 06/09/2025 10:30");
				}
			}

		} else if (status == EParkingStatus.ALREADY_EXISTS) {
			System.out.println("El vehículo ya tiene registrada una salida para esa entrada.");
		} else {
			System.out.println("El vehículo ya no está dentro del parqueadero.");
		}
	}

	private void viewRecordP() {
		String reportsInfo = """
				Seleccione una opción de vista para ver,Ingrese la opccion deseada(Solo el numero)
				1. Los vehículos registrados en un día específico
				2. Total de dinero recaudado en un día específico
				3. Volver al menu anterior
				""";

		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		do {
			System.out.println(reportsInfo);
			String option2 = sc.nextLine();

			switch (option2) {
			case "1": {
				LocalDate date1 = null;
				while (date1 == null) {
					try {
						System.out.print("Ingrese la fecha (dd/MM/yyyy): ");
						String input = sc.nextLine();
						date1 = LocalDate.parse(input, formatter2);
					} catch (Exception e) {
						System.out.println("Formato inválido. Ejemplo correcto: 06/09/2025");
					}
				}
				System.out.println(this.parking.vehicleRecordByDate(date1));
				break;
			}
			case "2": {
				LocalDate date2 = null;
				while (date2 == null) {
					try {
						System.out.print("Ingrese la fecha (dd/MM/yyyy): ");
						String input = sc.nextLine();
						date2 = LocalDate.parse(input, formatter2);
					} catch (Exception e) {
						System.out.println("Formato inválido. Ejemplo correcto: 06/09/2025");
					}
				}
				System.out.println(this.parking.moneyCollectedDay(date2));
				break;
			}
			case "3":
				System.out.println("Volviendo al menú anterior...");
				return;
			default:
				System.out.println("Opción inválida");
				break;
			}
		} while (true);
	}
	
	private void showVehicleRate() {
		System.out.println("El precio por tipo de vehiculo es:");
		System.out.println(this.parking.showVehicleR());	
	}

}
