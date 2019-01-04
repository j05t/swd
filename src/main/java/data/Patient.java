package main.java.data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

@Entity
@SequenceGenerator(name="patient_id_seq", initialValue=100, allocationSize=10)
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
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="patient_id_seq")
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
	
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private List<Vitalparameter> vitalParameter;

	public List<Vitalparameter> getVitalParameter() {
		return vitalParameter;
	}

	public String toString() {
		return getId() + " " + getFirstName() + " " + getLastName() + " Age " + getAge() + " Admission date "
				+ getAdmissionDate() + " Comment " + getComment() + getStreet() + ", " + getZip() + " " + getCity();
	}

	@ManyToOne
	private Arzt arzt;

	@ManyToMany
	@JoinTable(joinColumns = @JoinColumn(name = "PATIENT_ID"), inverseJoinColumns = @JoinColumn(name = "TERMIN_ID"))
	private List<Termin> termine = new ArrayList<Termin>();

	public List<Termin> getTermine() {
		return termine;
	}

	public void addTermin(Termin termin) {
		termine.add(termin);
		termin.add(this);
	}	

	public Arzt getBetreuer() {
		return arzt;
	}

	public void setArzt(Arzt betreuer) {
		this.arzt = betreuer;
		betreuer.addPatient(this);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getComment() {
		return comment != null ? comment : "";
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public LocalDate getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(LocalDate admissionDate) {
		this.admissionDate = admissionDate;
	}

	public String getSsn() {
		return ssn != null ? ssn : "";
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	

	public String getStreet() {
		return street != null ? street : "";
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city != null ? city : "";
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip != null ? zip : "";
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getAddress() {
		return getStreet() + ", " + getZip() + " " + getCity();
	}



}
