package main.java.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Betreuer implements Serializable{

	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "betreuer")
	private Collection<Person> persons = new ArrayList<Person>();

	public Collection<Person> getPersons() {
		return persons;
	}

	public void addPerson(Person person) {
		persons.add(person);
	}

	@Id
	private int id;

	private String name;
	
	@ManyToOne(fetch =FetchType.EAGER)
	private Role role;
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getFachgebiet() {
		return fachgebiet;
	}

	public void setFachgebiet(String fachgebiet) {
		this.fachgebiet = fachgebiet;
	}

	public String getDurchwahl() {
		return durchwahl;
	}

	public void setDurchwahl(String durchwahl) {
		this.durchwahl = durchwahl;
	}

	private String fachgebiet;
	
	private String durchwahl;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	protected Betreuer() {
	};

	public Betreuer(int id, String name) {
		setId(id);
		setName(name);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
