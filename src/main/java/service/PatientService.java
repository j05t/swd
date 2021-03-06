package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import data.Patient;
import data.Termin;
import data.Vitalparameter;
import data.Diagnose;


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
	
	public Set<Diagnose> findDiagnosisByIds(List<Integer> ids) {
		Query query = service.getEntityManager().createQuery("Select d from Diagnose d where d.id in (:selectedValues)");
		query.setParameter("selectedValues", ids);

		try {
			return new HashSet<Diagnose>(query.getResultList());
		} catch (NoResultException e) {
			System.out.println("no result");
			// not found
		}

		return null;
	}
	
	public List<Diagnose> findAllPossibleDiagnosis() {
		Query query = service.getEntityManager().createQuery("Select d from Diagnose d order by d.bezeichnung");

		try {
			return query.getResultList();
		} catch (NoResultException e) {
			System.out.println("no result");
			// not found
		}

		return null;
	}
}
