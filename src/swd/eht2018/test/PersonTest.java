package swd.eht2018.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

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
public class PersonTest {

	private static EntityManagerFactory factory;
	private static EntityManager manager;
	private static EntityTransaction transaction;

	private static Person person;
	private static Person person2;
	private static Address address;
	private static Address address2;
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
		person2 = new Person(2, "Frank", "Tuttle");
		address = new Address("Kasernstrasse 12", "Graz", "8010");
		address2 = new Address("Lendgasse 1", "Graz", "8020");
		betreuer = new Betreuer(1, "Marketing");

		person.setAddress(address);
		person2.setAddress(address2);
		person.setBetreuer(betreuer);
		person2.setBetreuer(betreuer);
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

		assertEquals(p1.getBetreuer().getName(), "Marketing");
		assertEquals(p2.getBetreuer().getName(), "Marketing");
	}

	@Test
	public void modify1() {

		Person p = manager.find(Person.class, person.getId());
		assertNotNull(p);
		assertEquals("John", p.getFirstName());

		transaction.begin();
		p.setFirstName("Frank");
		manager.merge(p);
		transaction.commit();

		p = manager.find(Person.class, person.getId());
		assertEquals("Frank", p.getFirstName());
	}

	@Test(expected = RollbackException.class)
	public void modify2() {
		person2.setAddress(address);

		// One to one relationship should prevent two persons having the same
		// address, expect RollbackException on commit
		transaction.begin();
		manager.persist(person2);
		transaction.commit();
	}

	@Test
	public void remove() {
		Person p = manager.find(Person.class, person.getId());
		Person p2 = manager.find(Person.class, person2.getId());
		Betreuer d = manager.find(Betreuer.class, betreuer.getId());

		transaction.begin();

		// remove person from database, cascades to address as well
		manager.remove(p);
		manager.remove(p2);

		// removal of department is only possible when it is empty
		manager.remove(d);

		transaction.commit();

		assertNull(manager.find(Person.class, person.getId()));
		assertNull(manager.find(Person.class, person2.getId()));

		assertNull(manager.find(Address.class, person.getId()));
		assertNull(manager.find(Address.class, person2.getId()));

		assertNull(manager.find(Betreuer.class, betreuer.getId()));
	}

}
