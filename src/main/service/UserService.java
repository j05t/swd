package main.service;


import javax.persistence.NoResultException;
import javax.persistence.Query;

import main.java.data.User;

public class UserService {
	private static JPAService service = JPAService.getInstance();

	public User find(String username, String password) {
		Query query = service.getEntityManager().createQuery("Select u from User u WHERE u.userName LIKE :username");
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

}
