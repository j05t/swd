package data;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="vitalparameter_id_seq", initialValue=100, allocationSize=10)
public class Vitalparameter implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="vitalparameter_id_seq")
	private int id;
	
	private String blutdruckDiastolisch;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBlutdruckDiastolisch() {
		return blutdruckDiastolisch;
	}

	public void setBlutdruckDiastolisch(String blutdruckDiastolisch) {
		this.blutdruckDiastolisch = blutdruckDiastolisch;
	}

	public String getBlutdruckSystolisch() {
		return blutdruckSystolisch;
	}

	public void setBlutdruckSystolisch(String blutdruckSystolisch) {
		this.blutdruckSystolisch = blutdruckSystolisch;
	}

	public String getPuls() {
		return puls;
	}

	public void setPuls(String puls) {
		this.puls = puls;
	}

	public String getTemperatur() {
		return temperatur;
	}

	public void setTemperatur(String temperatur) {
		this.temperatur = temperatur;
	}

	public String getMaximalSchmerz() {
		return maximalSchmerz;
	}

	public void setMaximalSchmerz(String maximalSchmerz) {
		this.maximalSchmerz = maximalSchmerz;
	}

	public String getBelastungsSchmerz() {
		return belastungsSchmerz;
	}

	public void setBelastungsSchmerz(String belastungsSchmerz) {
		this.belastungsSchmerz = belastungsSchmerz;
	}

	public String getRuheSchmerz() {
		return ruheSchmerz;
	}

	public void setRuheSchmerz(String ruheSchmerz) {
		this.ruheSchmerz = ruheSchmerz;
	}

	public LocalDate getDiagnosisDate() {
		return diagnoseDatum;
	}

	public void setDiagnosisDate(LocalDate diagnosisDate) {
		this.diagnoseDatum = diagnosisDate;
	}

	private String blutdruckSystolisch;
	
	private String puls;
	
	private String temperatur;
	
	private String maximalSchmerz;
	private String belastungsSchmerz;
	private String ruheSchmerz;

    private LocalDate diagnoseDatum;

    public String toString() {
    	return String.format(" %s %s %s" , id, belastungsSchmerz, puls);
    }
}
