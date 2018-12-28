package main.java.service;

import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import main.java.data.Person;

public class PatientService {
	private static JPAService service = JPAService.getInstance();

	public List<Person> find(String firstName, String lastName) {
		Query query = service.getEntityManager().createQuery("Select p from Person p WHERE p.first_name LIKE :firstName AND p.last_name LIKE :lastName");
		query.setParameter("firstName", firstName);
		query.setParameter("lastName", lastName);

		try {
			return query.getResultList();
		} catch (NoResultException e) {
			System.out.println("no result");
			// not found
		} 

		return null;
	}
	
	public List<Person> findAll() {
		Query query = service.getEntityManager().createQuery("Select p from Person p");

		try {
			return query.getResultList();
		} catch (NoResultException e) {
			System.out.println("no result");
			// not found
		} 

		return null;
	}

	public Person findById(int personId) {
		Query query = service.getEntityManager().createQuery("Select p from Person p where p.id like :personId");
		query.setParameter("personId", personId);

		try {
			return (Person) query.getSingleResult();
		} catch (NoResultException e) {
			System.out.println("no result");
			// not found
		} 

		return null;
	}

}
