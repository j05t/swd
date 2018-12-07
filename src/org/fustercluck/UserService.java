package org.fustercluck;

public class UserService {
	private static UserService instance;
	
	private UserService() {};
	
	public User find(String username, String password) {
		// TODO Auto-generated method stub
		return new User();
	}

	public static UserService getInstance() {
		// TODO Auto-generated method stub
		if (instance == null)
			instance = new UserService();
		
		return instance;
	}

}
