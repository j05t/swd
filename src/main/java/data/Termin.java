package main.java.data;

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


@Entity
@SequenceGenerator(name="termin_id_seq", initialValue=100, allocationSize=10)
public class Termin implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="termin_id_seq")
	private int id;
	
	private String name;

	@ManyToMany(mappedBy = "termine", cascade=CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Patient> patienten = new ArrayList<Patient>();
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(targetEntity=Medikament.class, cascade=CascadeType.ALL)
	private Set<Medikament> medikation;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(targetEntity=Diagnose.class, cascade=CascadeType.ALL)
	private Set<Diagnose> diagnosen;
	
	public Set<Medikament> getMedikation() {
		return medikation;
	}

	public void addMedikation(Medikament medikament) {
		this.medikation.add(medikament);
	}
	
	public Termin() {};
	
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
		patienten.add(person);
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

	public List<Patient> getPatienten() {
		return patienten;
	}

	public void addPatient(Patient patient) {
		this.patienten.add(patient);
	}

	public String getMedikationAsString() {
		StringBuilder sb = new StringBuilder();
		
		if(getMedikation() != null)
			for (Medikament m : getMedikation()) {
				sb.append(m.getBezeichnung()).append("<br/>");
			}
		
		return sb.toString();
	}

	public Set<Diagnose> getDiagnosen() {
		return diagnosen;
	}

	public void addDiagnose(Diagnose diagnose) {
		this.diagnosen.add(diagnose);
	}

	public String getDiagnosenAsString() {
		StringBuilder sb = new StringBuilder();
		
		if(getDiagnosen() != null)
			for (Diagnose d : getDiagnosen()) {
				sb.append(d.getBezeichnung()).append("<br/>");
			}
		
		return sb.toString();
	}

}
