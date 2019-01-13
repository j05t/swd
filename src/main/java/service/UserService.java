package service;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import data.User;

public class UserService {
	private static JPAService service = JPAService.getInstance();
	private final String salt = "adfskngöaieo4gn34gq8"; 
	
	public User find(String username, String password) {
		Query query = service.getEntityManager().createQuery("Select u from User u WHERE u.userName LIKE :username AND u.password LIKE :password");
		query.setParameter("username", username);
		query.setParameter("password", CryptMD5.cryptWithMD5(salt + password));

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