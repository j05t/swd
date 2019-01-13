package service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JPAService {
	private static EntityManagerFactory factory;
	private static EntityManager manager;
	private static EntityTransaction transaction;
	
	private static JPAService instance;
	
	private JPAService() {
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
	}

	public static EntityManager getEntityManager() {
		return manager;
	};
	
	public static EntityTransaction getEntityTransaction() {
		return transaction;
	};
	
	
	public static JPAService getInstance() {
		if(instance ==  null) 
			instance = new JPAService();
		
		return instance;
	}
}
