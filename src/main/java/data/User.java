package data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * User authentication is performed within this class.
 * 
 * @author Julia
 *
 */
@Entity
@Table(name = "USERS")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column
	private String userName;

	@Column
	private String password;

	@ManyToOne(fetch = FetchType.EAGER)
	private Role role;

	public boolean isAdmin() {
		if (role.getName().equals("Admin"))
			return true;

		return false;
	}

	/**
	 * This method determines if user has administrator or user role
	 * 
	 * @return
	 */
	public String getRole() {
		if (isAdmin())
			return "admin";

		return "user";
	}

	/**
	 * This method returns a string representation of the user.
	 */
	@Override
	public String toString() {
		return "User: " + userName + " Password: ***** isAdmin: " + isAdmin();
	}

}
