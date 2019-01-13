package data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class User implements Serializable {

	@Id
	private int id;

	@Column
	private String userName;

	@Column
	private String password;

	@Column
	private int role;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public boolean isAdmin() {
		if (role == 1)
			return true;
		
		return false;
	}
	
	public String getRole() {
		if (isAdmin())
			return "admin";
		
		return "user";
	}

	@Override
	public String toString() {
		return "User: " + userName + " Password: ***** isAdmin: " + isAdmin();
	}

}
