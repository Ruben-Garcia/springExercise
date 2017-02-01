package com.udemy.model;

import javax.persistence.Column;

public class ContactModel {

	private int id;
	
	private String firstname;
	
	private String lastname;
	
	private String telephone;
	
	private String city;

	public ContactModel(String firstname, String lastname, String telephone, String city, int id) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.telephone = telephone;
		this.city = city;
		this.id = id;
	}

	public ContactModel() {
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", telephone=" + telephone
				+ ", city=" + city + "]";
	}
}
