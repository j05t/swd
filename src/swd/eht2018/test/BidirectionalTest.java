package swd.eht2018.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import swd.eht2018.data.Address;
import swd.eht2018.data.Betreuer;
import swd.eht2018.data.Person;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BidirectionalTest {

	private static EntityManagerFactory factory;
	private static EntityManager manager;
	private static EntityTransaction transaction;

	private static Person person;
	private static Address address;
	private static Betreuer betreuer;

	@BeforeClass
	public static void init() {
		factory = Persistence.createEntityManagerFactory("swd");
		manager = factory.createEntityManager();
		transaction = manager.getTransaction();
	}

	@AfterClass
	public static void destroy() {
		manager.close();
		factory.close();
	}

	@Before
	public void setup() {
		person = new Person(1, "John", "Doe");
		address = new Address("Kasernstrasse 12", "Graz", "8010");
		betreuer = new Betreuer(1, "Marketing");

		person.setAddress(address);
		person.setBetreuer(betreuer);

		transaction.begin();
		manager.persist(betreuer);
		manager.persist(person);
		transaction.commit();
	}

	@After
	public void tearDown() {
		Person p = manager.find(Person.class, person.getId());
		Betreuer d = manager.find(Betreuer.class, betreuer.getId());

		transaction.begin();
		manager.remove(p);
		manager.remove(d);
		transaction.commit();
	}

	@Test
	public void testBidirectionals() {
		assertEquals(address, person.getAddress());
		assertEquals(person, address.getPerson());

		assertEquals(betreuer, person.getBetreuer());
		assertTrue(betreuer.getPersons().contains(person));
	}

	@Test
	public void testPersistedBidirectionals() {
		Person p = manager.find(Person.class, person.getId());
		Betreuer d = manager.find(Betreuer.class, betreuer.getId());
		Address a = p.getAddress();

		assertEquals(p, a.getPerson());
		assertEquals(d, p.getBetreuer());
		assertTrue(d.getPersons().contains(p));
	}
}
