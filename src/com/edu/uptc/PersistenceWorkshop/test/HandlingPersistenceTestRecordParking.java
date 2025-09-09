package com.edu.uptc.PersistenceWorkshop.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.edu.uptc.PersistenceWorkshop.enums.ETypeFiLe;
import com.edu.uptc.PersistenceWorkshop.model.RecordParking;
import com.edu.uptc.PersistenceWorkshop.persistence.HandlingPersistence;

class HandlingPersistenceTestRecordParking {
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
		/** Simulacion de que la persona ingresa datos(En este caso el logeo). */
		RecordParking recordParking1 = new RecordParking("plb-123",
				"31/08/2025" , "31/08/2025");
		RecordParking recordParking2 = new RecordParking("plb-122",
				"31/08/2025" , "31/08/2025");
		RecordParking recordParking3 = new RecordParking("plb-123",
				"31/08/2025" , "31/08/2025");
		
		this.handlingPersistence.addRecordParking(recordParking1);
		this.handlingPersistence.addRecordParking(recordParking2);
		this.handlingPersistence.addRecordParking(recordParking3);

		this.handlingPersistence.dumpFile(ETypeFiLe.JSON);
	
	}

	public void scenerieTwo() {
		/** Simulacion de que la persona cierra la app */
		this.handlingPersistence.setListRecordParking(new ArrayList<>());

		assertTrue(this.handlingPersistence.getListRecordParking().isEmpty());

		this.handlingPersistence.loadFile(ETypeFiLe.JSON);
		assertEquals("plb-123",
				this.handlingPersistence.getListRecordParking().get(0).getLicensePlate());
	}

}
