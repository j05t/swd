package main.java.service;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import main.java.data.Arzt;


public class PersonalService {
	private static JPAService service = JPAService.getInstance();

	public List<Arzt> find(String name) {
		Query query = service.getEntityManager().createQuery("Select p from Arzt p WHERE p.name LIKE :name");
		query.setParameter("name", name);

		try {
			return query.getResultList();
		} catch (NoResultException e) {
			System.out.println("no result");
			// not found
		} 

		return null;
	}
	
	public List<Arzt> findAll() {
		Query query = service.getEntityManager().createQuery("Select p from Arzt p");

		try {
			return query.getResultList();
		} catch (NoResultException e) {
			System.out.println("no result");
			// not found
		} 

		return null;
	}

	public Arzt findById(int arztId) {
		Query query = service.getEntityManager().createQuery("Select p from Arzt p where p.id = :arztId");
		query.setParameter("arztId", arztId);

		try {
			return (Arzt) query.getSingleResult();
		} catch (NoResultException e) {
			System.out.println("no result");
			// not found
		} 

		return null;
	}

}