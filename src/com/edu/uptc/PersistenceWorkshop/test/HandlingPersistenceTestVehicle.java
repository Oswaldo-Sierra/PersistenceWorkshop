package com.edu.uptc.PersistenceWorkshop.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.edu.uptc.PersistenceWorkshop.enums.ETypeFiLe;
import com.edu.uptc.PersistenceWorkshop.model.Vehicle;
import com.edu.uptc.PersistenceWorkshop.persistence.HandlingPersistence;

class HandlingPersistenceTestVehicle {

	private HandlingPersistence handlingPersistence = new HandlingPersistence();

	@Test
	void test() {
		scenerieOne();
	}

	@Test
	void test2() {
		scenerieTwo();
	}

	public void scenerieOne() {
		/** Simulacion de que la persona ingresa datos(En este caso el logeo). 
		Vehicle vehicle1 = new Vehicle("plb-123", "Carro", "Juan", "2019", "blanco");
		Vehicle vehicle2 = new Vehicle("jew-10", "Carro", "Juan", "2019", "blanco");
		Vehicle vehicle3 = new Vehicle("koa-090", "Moto", "Juan", "2019", "Negro");
		Vehicle vehicle4 = new Vehicle("koa-090", "Carro", "Juan", "2019", "blanco");
		
		this.handlingPersistence.addVehicle(vehicle1);
		this.handlingPersistence.addVehicle(vehicle2);
		this.handlingPersistence.addVehicle(vehicle3);
		this.handlingPersistence.addVehicle(vehicle4);

		this.handlingPersistence.dumpFile(ETypeFiLe.XML);
		*/

	}

	public void scenerieTwo() {
		/** Simulacion de que la persona cierra la app */
		this.handlingPersistence.setListVehicleRate(new ArrayList<>());

		assertTrue(this.handlingPersistence.getListVehicle().isEmpty());

		this.handlingPersistence.loadFile(ETypeFiLe.XML);
		assertEquals("Carro", this.handlingPersistence.getListVehicle().get(0).geteTypeVehicle());
	}

}
