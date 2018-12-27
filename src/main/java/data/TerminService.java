package main.java.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class TerminService {
	private static EntityManagerFactory factory;
	private static EntityManager manager;
	private static EntityTransaction transaction;

	private static TerminService instance;

	private TerminService() {
		
		Properties props = new Properties();
		try {
			props.load(new FileInputStream("persistence.properties"));
			factory = Persistence.createEntityManagerFactory("appName", props);
		} catch (IOException e) {
			System.out.println("Persistence properties not found, loading default.");
			factory = Persistence.createEntityManagerFactory("swd");
		}
		
		manager = factory.createEntityManager();
		transaction = manager.getTransaction();
	};

	public List<Termin> findAll() {
		Query query = manager.createQuery("Select t from Termin t");

		try {
			return query.getResultList();
		} catch (NoResultException e) {
			System.out.println("no result");
			// not found
		} 

		return null;
	}

	public static TerminService getInstance() {
		// TODO Auto-generated method stub
		if (instance == null)
			instance = new TerminService();

		return instance;
	}

}
