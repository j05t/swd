package main.java.service;

import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import main.java.data.Patient;

public class PatientService {
	private static JPAService service = JPAService.getInstance();

	public List<Patient> find(String firstName, String lastName) {
		Query query = service.getEntityManager().createQuery("Select p from Patient p WHERE p.first_name LIKE :firstName AND p.last_name LIKE :lastName");
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
	
	public List<Patient> findAll() {
		Query query = service.getEntityManager().createQuery("Select p from Patient p");

		try {
			return query.getResultList();
		} catch (NoResultException e) {
			System.out.println("no result");
			// not found
		} 

		return null;
	}

	public Patient findById(int patientId) {
		Query query = service.getEntityManager().createQuery("Select p from Patient p where p.id like :patientId");
		query.setParameter("patientId", patientId);

		try {
			return (Patient) query.getSingleResult();
		} catch (NoResultException e) {
			System.out.println("no result");
			// not found
		} 

		return null;
	}
}
