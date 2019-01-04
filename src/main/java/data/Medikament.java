package main.java.data;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Medikament implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String bezeichnung;


	public String getBezeichnung() {
		return bezeichnung;
	}


	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	
}