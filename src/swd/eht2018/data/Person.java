package swd.eht2018.data;

import java.io.Serializable;
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

	public void add(Termin project) {
		termine.add(project);
		project.add(this);
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

}
