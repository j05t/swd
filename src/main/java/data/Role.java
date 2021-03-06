package data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * The roles are assigned within this class.
 * 
 * @author Julia
 *
 */
@Entity
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name = "NAME")
	private String name;

	public String toString() {
		return this.name;
	}

	/**
	 * Returns the name of the role.
	 */
	public String getName() {
		return name;
	}
}
