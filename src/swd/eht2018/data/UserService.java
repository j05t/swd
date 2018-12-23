package swd.eht2018.data;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class UserService {
	private static EntityManagerFactory factory;
	private static EntityManager manager;
	private static EntityTransaction transaction;

	private static UserService instance;

	private UserService() {
		factory = Persistence.createEntityManagerFactory("swd");
		manager = factory.createEntityManager();
		transaction = manager.getTransaction();
	};

	public User find(String username, String password) {
		Query query = manager.createQuery("Select u from User u WHERE u.userName LIKE :username");
		query.setParameter("username", username);
		
		try {
			User u = (User) query.getSingleResult();
			return u;
		} catch (NoResultException e) {
			System.out.println("no result");
			// not found
		} 

		return null;
	}

	public static UserService getInstance() {
		// TODO Auto-generated method stub
		if (instance == null)
			instance = new UserService();

		return instance;
	}

}
