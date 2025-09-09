package com.edu.uptc.PersistenceWorkshop.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.edu.uptc.PersistenceWorkshop.enums.ETypeFiLe;
import com.edu.uptc.PersistenceWorkshop.model.User;
import com.edu.uptc.PersistenceWorkshop.persistence.HandlingPersistence;

class HandlingPersistenceTestUser {
	private HandlingPersistence hPTU = new HandlingPersistence();


	@Test
	void test() {
		scenerieOne();
	}
	
	@Test
	void test2() {
		scenerieTwo();
	}

	public void scenerieOne() {
		/** Simulacion de que la persona ingresa datos(En este caso el logeo).*/
		User user1 = new User("Pepito", "hola123@");
		User user2 = new User("Pepito", "hola122@");
		User user3 = new User("juan", "jajaja980");
		
		this.hPTU.addUser(user1);
		this.hPTU.addUser(user2);
		this.hPTU.addUser(user3);
		
		this.hPTU.dumpFile(ETypeFiLe.SER);

	}
	
	public void scenerieTwo() {
		/** Simulacion de que la persona cierra la app*/
		this.hPTU.setListUser(new ArrayList<>());
		
		assertTrue(this.hPTU.getListUser().isEmpty());
		
		this.hPTU.loadFile(ETypeFiLe.SER);
		assertEquals("Pepito", this.hPTU.getListUser().get(0).getUserName());
	}

}
