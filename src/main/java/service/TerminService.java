package main.java.service;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import main.java.data.Termin;

public class TerminService {
	private static JPAService service = JPAService.getInstance();

	public List<Termin> findAll() {
		Query query = service.getEntityManager().createQuery("Select t from Termin t order by t.datum desc");

		try {
			return query.getResultList();
		} catch (NoResultException e) {
			System.out.println("no result");
		} 

		return null;
	}

}
