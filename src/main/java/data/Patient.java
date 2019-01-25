package data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

/**
 * Within this class, the attributes Id, First name, Last name, Age, Admission
 * date, Comment, Date of birth, Street, ZIP code are assigned to the patient.
 * and assigned the city.
 * 
 * @author Julia
 *
 */
@Entity
@SequenceGenerator(name = "patient_id_seq", initialValue = 100, allocationSize = 10)
public class Patient implements Serializable {

	private static final long serialVersionUID = 1L;

	public Patient() {
	}

	public Patient(int id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_id_seq")
	private int id;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "AGE")
	private int age;

	@Column(name = "COMMENT")
	private String comment;

	@Column(name = "ADMISSION_DATE")
	private LocalDate admissionDate;

	private LocalDate birthDate;

	private String ssn;

	private String street;
	private String city;
	private String zip;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Vitalparameter> vitalParameter;

	/**
	 * This method returns a list of vital signs.
	 * 
	 * @return
	 */

	public List<Vitalparameter> getVitalParameter() {
		return vitalParameter;
	}

	/**
	 * This method returns a string with the parameters Id,First name,Last
	 * name,Age,Recording date,Comment,Street,Postcode and City.
	 */
	public String toString() {
		return getId() + " " + getFirstName() + " " + getLastName() + " Age " + getAge() + " Admission date "
				+ getAdmissionDate() + " Comment " + getComment() + getStreet() + ", " + getZip() + " " + getCity();
	}

	@ManyToOne
	private Arzt arzt;

	@ManyToMany
	@JoinTable(joinColumns = @JoinColumn(name = "PATIENT_ID"), inverseJoinColumns = @JoinColumn(name = "TERMIN_ID"))
	private Set<Termin> termine = new HashSet<Termin>();

	/**
	 * This Method returns a list of appointments.
	 * 
	 * @return
	 */
	public Set<Termin> getAppointments() {
		return termine;
	}

	/**
	 * This method adds an appointment.
	 * 
	 * @param termin
	 */
	public void addAppointment(Termin termin) {
		termine.add(termin);
		termin.add(this);
	}

	/**
	 * This method returns the caregiver.
	 * 
	 * @return
	 */
	public Arzt getBetreuer() {
		return arzt;
	}

	/**
	 * With this method the varibale arzt(type:string) is assigned a caregiver. A
	 * patient is also assigned to the caregiver.
	 * 
	 * @param betreuer
	 */
	public void setArzt(Arzt betreuer) {
		this.arzt = betreuer;
		betreuer.addPatient(this);
	}

	/**
	 * This method returns the iD.
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * With this method, the variable id(type:int) is assigned an id.
	 * 
	 * @return
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * This method returns the first name.
	 * 
	 * @return
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * This method assigns a first name to the variable firstName(Type:String)..
	 * 
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * This method returns the last name.
	 * 
	 * @return
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * This method assigns a last name to the variable lastName(Type:Sting).
	 * 
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * This method returns the age.
	 * 
	 * @return
	 */
	public int getAge() {
		return age;
	}

	/**
	 * This method assigns an age to the age(type:int) variable.
	 * 
	 * @param age
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * This method returns the comment.
	 * 
	 * @return
	 */
	public String getComment() {
		return comment != null ? comment : "";
	}

	/**
	 * This method assigns a comment to the variable comment(Type:String).
	 * 
	 * @param comment
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	public LocalDate getAdmissionDate() {
		return admissionDate;
	}

	/**
	 * This method assigns an admission date to the variable
	 * AdmissionDate(Type:LocalDate).
	 * 
	 * @param admissionDate
	 */
	public void setAdmissionDate(LocalDate admissionDate) {
		this.admissionDate = admissionDate;
	}

	/**
	 * This method returns Ssn.
	 * 
	 * @return
	 */
	public String getSsn() {
		return ssn != null ? ssn : "";
	}

	/**
	 * With this method, the variable ssn(type:string) is assigned to SSN.
	 * 
	 * @param ssn
	 */
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	/**
	 * This method returns the date of birth.
	 * 
	 * @return
	 */
	public LocalDate getBirthDate() {
		return birthDate;
	}

	/**
	 * This method assigns a date of birth to the birthDate(Type:LocalDate)
	 * variable.
	 * 
	 * @param birthDate
	 */
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * This method returns the road.
	 * 
	 * @return
	 */
	public String getStreet() {
		return street != null ? street : "";
	}

	/**
	 * This method assigns a street to the variable street(type:string).
	 * 
	 * @param street
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * This method returns the city.
	 * 
	 * @return
	 */
	public String getCity() {
		return city != null ? city : "";
	}

	/**
	 * This method assigns a city to the variable city(Type:String).
	 * 
	 * @param city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * This method returns the Zip code.
	 * 
	 * @return
	 */
	public String getZip() {
		return zip != null ? zip : "";
	}

	/**
	 * This method assigns a Zip code to the variable zip(type:string).
	 * 
	 * @param zip
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * With this method the address is returned with the variables street, postal
	 * code and city.
	 * 
	 * @return Address
	 */
	public String getAddress() {
		return getStreet() + ", " + getZip() + " " + getCity();
	}

}
