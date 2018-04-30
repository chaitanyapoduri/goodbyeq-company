package com.beatus.goodbyeq.company.model;

public class CompanyDTO {
	
	private String companyID;
	private String companyName;
	private String email;
	private String phoneNumber;
	private AddressDTO addressDTO; 
	private String status;
	
	public String getCompanyID() {
		return companyID;
	}
	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public AddressDTO getAddressDTO() {
		return addressDTO;
	}
	public void setAddressDTO(AddressDTO addressDTO) {
		this.addressDTO = addressDTO;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public CompanyDTO(String companyID, String companyName, String email, String phoneNumber, AddressDTO addressDTO) {
		super();
		this.companyID = companyID;
		this.companyName = companyName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.addressDTO = addressDTO;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public CompanyDTO(String status) {
		super();
		this.status = status;
	}
}