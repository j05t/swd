package main.java.data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Termin implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private int id;
	private String name;

	@ManyToMany(mappedBy = "termine",  fetch=FetchType.EAGER)
	private List<Patient> persons = new ArrayList<Patient>();

	private List<Medikament> medikation;
	
	public List<Medikament> getMedikation() {
		return medikation;
	}

	public void addMedikation(Medikament medikament) {
		this.medikation.add(medikament);
	}
	

	
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private List<Diagnose> diagnosen;
	
	
	protected Termin() {
	};
	
	@Column
    private LocalDate datum;
	@Column
    private LocalTime zeit;
    
    
	public LocalDate getDate() {
		return datum;
	}

	public void setDate(LocalDate date) {
		this.datum = date;
	}

	public LocalTime getTime() {
		return zeit;
	}

	public void setTime(LocalTime time) {
		this.zeit = time;
	}

	public Termin(int id, String name) {
		setId(id);
		setName(name);
	}

	public void add(Patient person) {
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

	public List<Patient> getPersons() {
		return persons;
	}

	public void setPersons(List<Patient> persons) {
		this.persons = persons;
	}

	public String getMedikationAsString() {
		StringBuilder sb = new StringBuilder();
		
		for (Medikament m : getMedikation()) {
			sb.append(m.getBezeichnung()).append("<br>");
		}
		return sb.toString();
	}

	public List<Diagnose> getDiagnosen() {
		return diagnosen;
	}

	public void addDiagnose(Diagnose diagnose) {
		this.diagnosen.add(diagnose);
	}

	public String getDiagnosenAsString() {
		StringBuilder sb = new StringBuilder();
		
		for (Diagnose d : getDiagnosen()) {
			sb.append(d.getBezeichnung()).append("<br>");
		}
		return sb.toString();
	}

}
