package data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Within this class, the attributes Id,Name,Role,Specialty and Extension are
 * assigned to the physician.
 * 
 * @author Julia
 *
 */
@Entity
public class Arzt implements Serializable {

	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "arzt")
	private Collection<Patient> patienten = new ArrayList<Patient>();

	/**
	 * This constructor represents a list of patients.
	 * 
	 * @return
	 */
	public Collection<Patient> getPatients() {
		return patienten;
	}

	/**
	 * This constructor adds a patient to the collection.
	 * 
	 * @param patient
	 */
	public void addPatient(Patient patient) {
		patienten.add(patient);
	}

	@Id
	private int id;

	private String name;

	@ManyToOne(fetch = FetchType.EAGER)
	private Role role;

	/**
	 * This method returns the value of the roles.
	 * 
	 * @return
	 */
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * This method returns the value of the subject.
	 * 
	 * @return
	 */
	public String getFachgebiet() {
		return fachgebiet;
	}

	/**
	 * With this method, the variable Fachgebiet (type:string) is assigned to a
	 * subject area.
	 * 
	 * @param fachgebiet
	 */
	public void setFachgebiet(String fachgebiet) {
		this.fachgebiet = fachgebiet;
	}

	public String getDurchwahl() {
		return durchwahl;
	}

	/**
	 * This method returns the value of the extension.
	 * 
	 * @return
	 */

	public void setDurchwahl(String durchwahl) {
		this.durchwahl = durchwahl;
	}

	private String fachgebiet;

	private String durchwahl;

	/**
	 * This method returns the value of the names.
	 * 
	 * @return
	 */

	public String getName() {
		return name;
	}

	/**
	 * This method assigns a name to the variable name(Type:String).
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	protected Arzt() {
	};

	/**
	 * With this method, a doctor is assigned the attributes name(type:string) and
	 * id(type:int).
	 * 
	 * @param id
	 * @param name
	 */
	public Arzt(int id, String name) {
		setId(id);
		setName(name);
	}

	/**
	 * This method returns the id.
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * With this method, the variable id(type:int) is assigned an id.
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
}
