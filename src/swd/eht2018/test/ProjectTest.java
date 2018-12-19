package swd.eht2018.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import swd.eht2018.data.Address;
import swd.eht2018.data.Betreuer;
import swd.eht2018.data.Person;
import swd.eht2018.data.Termin;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProjectTest {

	private static EntityManagerFactory factory;
	private static EntityManager manager;
	private static EntityTransaction transaction;

	private static Person person;
	private static Person person2;
	private static Address address;
	private static Address address2;
	private static Betreuer betreuer;
	private static Termin termin;
	private static Termin termin2;

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
		person2 = new Person(2, "Frank", "Tuttle");
		address = new Address("Kasernstrasse 12", "Graz", "8010");
		address2 = new Address("Lendgasse 1", "Graz", "8020");
		betreuer = new Betreuer(1, "Marketing");
		termin = new Termin(1, "Termin 1");
		termin2 = new Termin(2, "Termin 2");

		person.setAddress(address);
		person2.setAddress(address2);
		person.setBetreuer(betreuer);
		person2.setBetreuer(betreuer);

		person.add(termin);
		person.add(termin2);

		person2.add(termin);
	}

	@Test
	public void create() {
		assertNotNull(person);
		assertNotNull(address);
		assertNotNull(person2);
		assertNotNull(address2);
		assertNotNull(betreuer);

		transaction.begin();
		manager.persist(betreuer);
		manager.persist(termin);
		manager.persist(termin2);

		manager.persist(person);
		manager.persist(person2);
		transaction.commit();

		Person p1 = manager.find(Person.class, person.getId());
		Person p2 = manager.find(Person.class, person2.getId());

		assertNotNull(p1);
		assertNotNull(p2);

		assertNotNull(p1.getAddress());
		assertNotNull(p2.getAddress());

		assertNotNull(p1.getBetreuer());
		assertNotNull(p2.getBetreuer());

		assertEquals("Marketing", p1.getBetreuer().getName());
		assertEquals("Marketing", p2.getBetreuer().getName());

		assertTrue(p1.getTermine().contains(termin));
		assertTrue(p1.getTermine().contains(termin2));
		assertTrue(p2.getTermine().contains(termin));
	}

	@Test
	public void remove() {
		Person p = manager.find(Person.class, person.getId());
		Person p2 = manager.find(Person.class, person2.getId());
		Betreuer d = manager.find(Betreuer.class, betreuer.getId());
		Termin pr = manager.find(Termin.class, termin.getId());
		Termin pr2 = manager.find(Termin.class, termin2.getId());

		transaction.begin();

		// remove person from database, cascades to address as well
		manager.remove(p);
		manager.remove(p2);

		// removal of betreuer is only possible when it is empty
		manager.remove(d);

		manager.remove(pr);
		manager.remove(pr2);

		transaction.commit();

		assertNull(manager.find(Person.class, person.getId()));
		assertNull(manager.find(Person.class, person2.getId()));

		assertNull(manager.find(Address.class, person.getId()));
		assertNull(manager.find(Address.class, person2.getId()));

		assertNull(manager.find(Betreuer.class, betreuer.getId()));

		assertNull(manager.find(Termin.class, termin.getId()));
		assertNull(manager.find(Termin.class, termin2.getId()));
	}

}
