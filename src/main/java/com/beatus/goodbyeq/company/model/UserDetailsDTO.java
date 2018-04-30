package com.beatus.goodbyeq.company.model;

public class UserDetailsDTO {

	private String emailID;
	private String phoneNumber;
	private String firstName;
	private String lastName;
	private String userID;
	private String password;
	private String socialIdentifier;
	private CoordinatesDTO userCoordinates;
	
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public CoordinatesDTO getUserCoordinates() {
		return userCoordinates;
	}
	public void setUserCoordinates(CoordinatesDTO userCoordinates) {
		this.userCoordinates = userCoordinates;
	}
	public String getSocialIdentifier() {
		return socialIdentifier;
	}
	public void setSocialIdentifier(String socialIdentifier) {
		this.socialIdentifier = socialIdentifier;
	}	
}
