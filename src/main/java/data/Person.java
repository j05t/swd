package main.java.data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Person implements Serializable{

	private static final long serialVersionUID = 1L;

	protected Person() {
	}

	public Person(int id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Id
	private int id;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "AGE")
	private int age;

	@Column(name = "COMMENT")
	private String comment;

	@Column(name= "ADMISSION_DATE")
    private LocalDate admissionDate;

	
	@OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
	private Address address;

	@ManyToOne
	private Betreuer betreuer;

	@ManyToMany
	@JoinTable(joinColumns = @JoinColumn(name = "PERSON_ID"), inverseJoinColumns = @JoinColumn(name = "TERMIN_ID"))
	private List<Termin> termine = new ArrayList<Termin>();

	public List<Termin> getTermine() {
		return termine;
	}

	public void add(Termin termin) {
		termine.add(termin);
		termin.add(this);
	}

	public Betreuer getBetreuer() {
		return betreuer;
	}

	public void setBetreuer(Betreuer betreuer) {
		this.betreuer = betreuer;
		betreuer.addPerson(this);
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
		address.setPerson(this);
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getComment() {
		return comment != null ? comment: "";
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

}
