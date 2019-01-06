package main.java.service;

import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import main.java.data.Patient;
import main.java.data.Termin;
import main.java.data.Vitalparameter;

public class PatientService {
	private static JPAService service = JPAService.getInstance();

	public List<Patient> find(String firstName, String lastName) {
		Query query = service.getEntityManager().createQuery(
				"Select p from Patient p WHERE p.first_name LIKE :firstName AND p.last_name LIKE :lastName");
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
		Query query = service.getEntityManager().createQuery("Select p from Patient p order by p.lastName");

		try {
			return query.getResultList();
		} catch (NoResultException e) {
			System.out.println("no result");
			// not found
		}

		return null;
	}

	public Patient findById(int patientId) {
		Query query = service.getEntityManager().createQuery("Select p from Patient p where p.id = :patientId");
		query.setParameter("patientId", patientId);

		try {
			return (Patient) query.getSingleResult();
		} catch (NoResultException e) {
			System.out.println("no result");
			// not found
		}

		return null;
	}

	public Vitalparameter findVitalparameterById(int vid) {
		Query query = service.getEntityManager().createQuery("Select p from Vitalparameter p where p.id = :vid");
		query.setParameter("vid", vid);

		try {
			return (Vitalparameter) query.getSingleResult();
		} catch (NoResultException e) {
			System.out.println("no result");
			// not found
		}

		return null;
	}

	public Termin findTerminById(int tid) {
		Query query = service.getEntityManager().createQuery("Select t from Termin t where t.id = :tid");
		query.setParameter("tid", tid);

		try {
			return (Termin) query.getSingleResult();
		} catch (NoResultException e) {
			System.out.println("no result");
			// not found
		}

		return null;
	}
}
