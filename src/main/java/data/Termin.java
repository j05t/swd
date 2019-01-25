package data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * Within this class, an appointment is assigned to the diagnosis and
 * medication.
 * 
 * @author Julia
 *
 */

@Entity
@SequenceGenerator(name = "termin_id_seq", initialValue = 100, allocationSize = 10)
public class Termin implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "termin_id_seq")
	private int id;

	private String name;

	@ManyToMany(mappedBy = "termine", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Patient> patienten = new ArrayList<Patient>();

	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(targetEntity = Medikament.class, cascade = CascadeType.ALL)
	private Set<Medikament> medikation;

	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(targetEntity = Diagnose.class, cascade = CascadeType.ALL)
	private Set<Diagnose> diagnosen;

	/**
	 * This method returns the medication via a set (The Elements are Unique).
	 * 
	 * @return
	 */
	public Set<Medikament> getMedikation() {
		return medikation;
	}

	/**
	 * This method adds a medication to the Set<Medication>.
	 * 
	 * @param medikament
	 */
	public void addMedikation(Medikament medikament) {
		this.medikation.add(medikament);
	}

	public Termin() {
	};

	@Column
	private LocalDate datum;

	@Column
	private LocalTime zeit;

	/**
	 * This method returns the LocalDate.
	 * 
	 * @return
	 */
	public LocalDate getDate() {
		return datum;
	}

	/**
	 * This method assigns a LocalDate to the variable LocalDate(type:localDate).
	 * 
	 * @param date
	 */
	public void setDate(LocalDate date) {
		this.datum = date;
	}

	/**
	 * This method returns the LocalTime.
	 * 
	 * @return
	 */
	public LocalTime getTime() {
		return zeit;
	}

	/**
	 * This method assigns a LocalTime to the variable LocalTime(type:localTime).
	 * 
	 * @param time
	 */
	public void setTime(LocalTime time) {
		this.zeit = time;
	}

	/**
	 * This method specifies the appointment consisting of ID and name.
	 * 
	 * @param id
	 * @param name
	 */
	public Termin(int id, String name) {
		setId(id);
		setName(name);
	}

	/**
	 * With this method a patient is added.
	 * 
	 * @param person
	 */
	public void add(Patient person) {
		patienten.add(person);
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

	/**
	 * This method returns the name.
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

	/**
	 * This method returns a list of patients.
	 * 
	 * @return
	 */
	public List<Patient> getPatienten() {
		return patienten;
	}

	/**
	 * This method adds a patient.
	 * 
	 * @param patient
	 */
	public void addPatient(Patient patient) {
		this.patienten.add(patient);
	}

	public String getMedikationAsString() {
		StringBuilder sb = new StringBuilder();

		if (getMedikation() != null)
			for (Medikament m : getMedikation()) {
				sb.append(m.getBezeichnung()).append("<br/>");
			}

		return sb.toString();
	}

	/**
	 * This method returns the diagnoses via a set (The Elements are Unique).
	 * 
	 * @return
	 */
	public Set<Diagnose> getDiagnosen() {
		return diagnosen;
	}

	/**
	 * This method adds a diagnosis.
	 * 
	 * @param diagnose
	 */
	public void addDiagnose(Diagnose diagnose) {
		this.getDiagnosen().add(diagnose);
	}

	public String getDiagnosenAsHtmlString() {
		StringBuilder sb = new StringBuilder();

		if (getDiagnosen() != null) {
			sb.append("<ul>");

			for (Diagnose d : getDiagnosen()) {
				sb.append("<li>").append(d.getBezeichnung()).append("</li>");
			}
			sb.append("</ul>");
		}

		return sb.toString();
	}

	public void setDiagnosen(Set<Diagnose> diagnosen) {
		this.diagnosen = diagnosen;
	}

}
