package swd.eht2018.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Termin implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private int id;
	private String name;

	@ManyToMany(mappedBy = "termine")
	private List<Person> persons = new ArrayList<Person>();

	protected Termin() {
	};

	public Termin(int id, String name) {
		setId(id);
		setName(name);
	}

	public void add(Person person) {
		persons.add(person);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

}
