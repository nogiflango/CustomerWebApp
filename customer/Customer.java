package com.raj.customer;

public class Customer {
	public Customer() {
		
	}
	
	public Customer(String iD, String firstname, String lastname, String gender, String dob, String street, String city,
			String state, String zip) {
		super();
		ID = iD;
		this.firstname = firstname;
		this.lastname = lastname;
		this.gender = gender;
		this.dob = dob;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}
	private String ID;
    private String firstname;
    private String lastname;
    private String gender;
    private String dob;
    private String street;
    private String city;
    private String state;
    private String zip;
	
	
	public String getID() {
		return ID;
	}
	public void setID(String ID) {
		this.ID = ID;
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public void print() {
		System.out.println("ID = "+ID+" Firstname = "+firstname+" Lastname = "+lastname);
	}
	
	

}
