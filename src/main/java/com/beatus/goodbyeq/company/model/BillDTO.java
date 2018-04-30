package com.beatus.goodbyeq.company.model;

import java.util.ArrayList;
import java.util.List;

public class BillDTO {

	private String billId;
	private String userId;
	private String storeId;
	private String companyId;
	private String itemQuantity;
	private String totalQuantity;
	private Double totalAmount;
	private Double totalTax;
	private String isReturned;
	private Double totalSGST;
	private Double totalCGST;
	private Double totalIGST;
	private String returnReason;
	private String billCreatedOn;
	private String billUpdatedOn;
	private String billStatus;
	private List<ItemDetailsDTO> items = new ArrayList<ItemDetailsDTO>();
	
	public String getBillId() {
		return billId;
	}
	public void setBillId(String billId) {
		this.billId = billId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public String getItemQuantity() {
		return itemQuantity;
	}
	public void setItemQuantity(String itemQuantity) {
		this.itemQuantity = itemQuantity;
	}
	public String getTotalQuantity() {
		return totalQuantity;
	}
	public void setTotalQuantity(String totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Double getTotalTax() {
		return totalTax;
	}
	public void setTotalTax(Double totalTax) {
		this.totalTax = totalTax;
	}
	public String getIsReturned() {
		return isReturned;
	}
	public void setIsReturned(String isReturned) {
		this.isReturned = isReturned;
	}
	public Double getTotalSGST() {
		return totalSGST;
	}
	public void setTotalSGST(Double totalSGST) {
		this.totalSGST = totalSGST;
	}
	public Double getTotalCGST() {
		return totalCGST;
	}
	public void setTotalCGST(Double totalCGST) {
		this.totalCGST = totalCGST;
	}
	public Double getTotalIGST() {
		return totalIGST;
	}
	public void setTotalIGST(Double totalIGST) {
		this.totalIGST = totalIGST;
	}
	public String getReturnReason() {
		return returnReason;
	}
	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}
	public List<ItemDetailsDTO> getItems() {
		return items;
	}
	public void setItems(List<ItemDetailsDTO> items) {
		this.items = items;
	}
	
	public String getBillCreatedOn() {
		return billCreatedOn;
	}
	public void setBillCreatedOn(String billCreatedOn) {
		this.billCreatedOn = billCreatedOn;
	}
	public String getBillUpdatedOn() {
		return billUpdatedOn;
	}
	public void setBillUpdatedOn(String billUpdatedOn) {
		this.billUpdatedOn = billUpdatedOn;
	}
	public String getBillStatus() {
		return billStatus;
	}
	public void setBillStatus(String billStatus) {
		this.billStatus = billStatus;
	}
	
	public BillDTO(String billId, String userId, String storeId, String companyId, String itemQuantity,
			String totalQuantity, Double totalAmount, Double totalTax, String isReturned, Double totalSGST,
			Double totalCGST, Double totalIGST, String returnReason, String billCreatedOn, 
			String billUpdatedOn, String billStatus, List<ItemDetailsDTO> items) {
		super();
		this.billId = billId;
		this.userId = userId;
		this.storeId = storeId;
		this.companyId = companyId;
		this.itemQuantity = itemQuantity;
		this.totalQuantity = totalQuantity;
		this.totalAmount = totalAmount;
		this.totalTax = totalTax;
		this.isReturned = isReturned;
		this.totalSGST = totalSGST;
		this.totalCGST = totalCGST;
		this.totalIGST = totalIGST;
		this.returnReason = returnReason;
		this.billCreatedOn = billCreatedOn;
		this.billUpdatedOn = billUpdatedOn;
		this.billStatus = billStatus;
		this.items = items;
	}
	
	public BillDTO(String billId, String userId, String storeId, String companyId, String itemQuantity,
			String totalQuantity, Double totalAmount, Double totalTax, String isReturned, Double totalSGST,
			Double totalCGST, Double totalIGST, String returnReason, String billCreatedOn, 
			String billUpdatedOn) {
		this.billId = billId;
		this.userId = userId;
		this.storeId = storeId;
		this.companyId = companyId;
		this.itemQuantity = itemQuantity;
		this.totalQuantity = totalQuantity;
		this.totalAmount = totalAmount;
		this.totalTax = totalTax;
		this.isReturned = isReturned;
		this.totalSGST = totalSGST;
		this.totalCGST = totalCGST;
		this.totalIGST = totalIGST;
		this.returnReason = returnReason;
		this.billCreatedOn = billCreatedOn;
		this.billUpdatedOn = billUpdatedOn;
	}
	
	public BillDTO(String billStatus) {
		super();
		this.billStatus = billStatus;
	}
}