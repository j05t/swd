package main.java.data;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;


@Entity
public class Address implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Address() {
	}

	public Address(String street, String city, String zip) {
		this.street = street;
		this.city = city;
		this.zip = zip;
	}

	@Id
	private int personId;

	private String street;
	private String city;
	private String zip;

	@MapsId
	@OneToOne
	private Person person;

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
	
	public String toString() {
		return getStreet() + ", " + getZip() + " " + getCity();
	}
}
