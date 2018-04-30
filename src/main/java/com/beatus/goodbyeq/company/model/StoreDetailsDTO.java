package com.beatus.goodbyeq.company.model;

public class StoreDetailsDTO {

	private String latitude;
	private String longitude;
	private String storeId;
	private String companyId;
	private String storeName;
	private AddressDTO addressDTO;
	
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public AddressDTO getAddressDTO() {
		return addressDTO;
	}
	public void setAddressDTO(AddressDTO addressDTO) {
		this.addressDTO = addressDTO;
	}
	public StoreDetailsDTO(String latitude, String longitude, String storeId, String companyId, String storeName,
			AddressDTO addressDTO) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.storeId = storeId;
		this.companyId = companyId;
		this.storeName = storeName;
		this.addressDTO = addressDTO;
	}
}