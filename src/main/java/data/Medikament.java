package data;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Within this method, the medication are returned with their name and id.
 * 
 * @author Julia
 *
 */
@Entity
public class Medikament implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String bezeichnung;

	/**
	 * This method returns the name
	 * 
	 * @return
	 */
	public String getBezeichnung() {
		return bezeichnung;
	}

	/**
	 * With this method, the name is assigned to the variable name (Type:String).
	 * 
	 * @param bezeichnung
	 */
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

}
