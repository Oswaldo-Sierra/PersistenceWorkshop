package com.edu.uptc.PersistenceWorkshop.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.edu.uptc.PersistenceWorkshop.enums.ETypeFiLe;
import com.edu.uptc.PersistenceWorkshop.model.VehicleRate;
import com.edu.uptc.PersistenceWorkshop.persistence.HandlingPersistence;

class HandlingPersistenceTestVehicleRate {
	private HandlingPersistence handlingPersistence = new HandlingPersistence();
	
	/*@Test
	void test() {
		scenerieOne();
	}*/
	
	@Test
	void test2() {
		scenerieTwo();
	}
	
	public void scenerieOne() {
		/** Simulacion de que la persona ingresa datos(En este caso el logeo).*/
		VehicleRate vR1 = new VehicleRate("Carro", 2500);
		VehicleRate vR2 = new VehicleRate("Moto", 2000);
		VehicleRate vR3 = new VehicleRate("Cicla", 1000);
		
		List<VehicleRate> vR = new ArrayList<>();
		vR.add(vR1);
		vR.add(vR2);
		vR.add(vR3);
		
		this.handlingPersistence.setListVehicleRate(vR);
		
		this.handlingPersistence.dumpFile(ETypeFiLe.CSV);

	}
	
	public void scenerieTwo() {
		/** Simulacion de que la persona cierra la app*/
		this.handlingPersistence.setListVehicleRate(new ArrayList<>());
		
		//assertTrue(this.handlingPersistence.getListVehicleRate().isEmpty());
		
		this.handlingPersistence.loadFile(ETypeFiLe.CSV);
		assertEquals("Carro", this.handlingPersistence.getListVehicleRate().get(0).getTypeVehicle());
	}
	

}
