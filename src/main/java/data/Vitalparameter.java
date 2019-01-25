package data;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "vitalparameter_id_seq", initialValue = 100, allocationSize = 10)
public class Vitalparameter implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vitalparameter_id_seq")
	private int id;

	private String blutdruckDiastolisch;

	/**
	 * This method returns the Id.
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * This method assigns an ID to the variable id(type:int).
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * This method returns the blood pressure diastolic.
	 * 
	 * @return
	 */
	public String getBlutdruckDiastolisch() {
		return blutdruckDiastolisch;
	}

	/**
	 * This method assigns an blood pressure diastolic to the variable
	 * BlutdruckDiastolisch(type:String).
	 * 
	 * @param blutdruckDiastolisch
	 */
	public void setBlutdruckDiastolisch(String blutdruckDiastolisch) {
		this.blutdruckDiastolisch = blutdruckDiastolisch;
	}

	/**
	 * This method returns the blood pressure systolic.
	 * 
	 * @return
	 */
	public String getBlutdruckSystolisch() {
		return blutdruckSystolisch;
	}

	/**
	 * This method assigns an blood pressure systolic to the variable
	 * BlutdruckSystolisch(type:String).
	 * 
	 * @param blutdruckSystolisch
	 */

	public void setBlutdruckSystolisch(String blutdruckSystolisch) {
		this.blutdruckSystolisch = blutdruckSystolisch;
	}

	/**
	 * This method returns the pulse.
	 * 
	 * @return
	 */
	public String getPuls() {
		return puls;
	}

	/**
	 * This method assigns an pulse to the variable puls(type:String).
	 * 
	 * @param puls
	 */
	public void setPuls(String puls) {
		this.puls = puls;
	}

	/**
	 * This method returns the temperature.
	 * 
	 * @return
	 */

	public String getTemperatur() {
		return temperatur;
	}

	/**
	 * This method assigns an temperature to the variable Temperatur(type:String).
	 * 
	 * @param temperatur
	 */
	public void setTemperatur(String temperatur) {
		this.temperatur = temperatur;
	}

	/**
	 * This method returns the maximum pain.
	 * 
	 * @return
	 */
	public String getMaximalSchmerz() {
		return maximalSchmerz;
	}

	/**
	 * This method assigns an maximum pain to the variable
	 * MaximalSchmerz(type:String).
	 * 
	 * @param maximalSchmerz
	 */
	public void setMaximalSchmerz(String maximalSchmerz) {
		this.maximalSchmerz = maximalSchmerz;
	}

	/**
	 * This method returns the pain under strain.
	 * 
	 * @return
	 */
	public String getBelastungsSchmerz() {
		return belastungsSchmerz;
	}

	/**
	 * This method assigns an pain under strain to the variable
	 * Belastungsschmerz(type:String).
	 * 
	 * @param belastungsSchmerz
	 */
	public void setBelastungsSchmerz(String belastungsSchmerz) {
		this.belastungsSchmerz = belastungsSchmerz;
	}

	/**
	 * This method returns the rest pain.
	 * 
	 * @return
	 */
	public String getRuheSchmerz() {
		return ruheSchmerz;
	}

	/**
	 * This method assigns an rest pain to the variable RuheSchmerz(type:String).
	 * 
	 * @param ruheSchmerz
	 */
	public void setRuheSchmerz(String ruheSchmerz) {
		this.ruheSchmerz = ruheSchmerz;
	}

	/**
	 * This method returns the diagnosis Date.
	 * 
	 * @return
	 */
	public LocalDate getDiagnosisDate() {
		return diagnoseDatum;
	}

	/**
	 * This method assigns a Date to the variable diagnoseDatum(type:String).
	 * 
	 * @param diagnosisDate
	 */
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

	/**
	 * This method returns a string representation of id,stress pain and heart rate.
	 */
	public String toString() {
		return String.format(" %s %s %s", id, belastungsSchmerz, puls);
	}
}
