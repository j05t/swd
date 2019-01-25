package data;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * In this class, the attributes Id and Description are assigned to the
 * diagnosis.
 * 
 * @author Julia
 *
 */
@Entity
public class Diagnose implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String bezeichnung;

	/**
	 * This method returns the name of the diagnosis.
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
