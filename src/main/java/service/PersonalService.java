package main.java.service;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import main.java.data.Betreuer;

public class PersonalService {
	private static JPAService service = JPAService.getInstance();

	public List<Betreuer> find(String name) {
		Query query = service.getEntityManager().createQuery("Select p from Betreuer p WHERE p.name LIKE :name");
		query.setParameter("name", name);

		try {
			return query.getResultList();
		} catch (NoResultException e) {
			System.out.println("no result");
			// not found
		} 

		return null;
	}
	
	public List<Betreuer> findAll() {
		Query query = service.getEntityManager().createQuery("Select p from Betreuer p");

		try {
			return query.getResultList();
		} catch (NoResultException e) {
			System.out.println("no result");
			// not found
		} 

		return null;
	}

	public Betreuer findById(int betreuerId) {
		Query query = service.getEntityManager().createQuery("Select p from Betreuer p where p.id like :betreuerId");
		query.setParameter("betreuerId", betreuerId);

		try {
			return (Betreuer) query.getSingleResult();
		} catch (NoResultException e) {
			System.out.println("no result");
			// not found
		} 

		return null;
	}

}