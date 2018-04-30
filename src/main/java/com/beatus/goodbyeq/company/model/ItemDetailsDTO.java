package com.beatus.goodbyeq.company.model;

public class ItemDetailsDTO {
	private String itemId;
	private String itemName;
	private String itemDescription;
	private String hsnCode;
	private String brand;
	private String itemType;
	private String itemSubType;
	private Double quantityAvailable;
	private Double itemThresholdToNotify;
	private Double unitCostPrice;
	private Double unitSellingPrice;
	private Double unitDiscount;
	private Double unitDiscountType;
	private Double buyQuantity;
	private Double getQuantity;
	private Double unitProfit;
	private Double unitProfitPercentage;
	private Double gstTaxPercentage;
	private Double unitMRP;
	private String itemExpiryDate;
	private String inventoryDate;
	
	private String price;
	private String sgst;
	private String cgst;
	private String igst;
	private String itemQuantity;
	private String itemTaxAmount;
	private String itemTaxSgst;
	private String itemTaxCgst;
	private String itemTaxIgst;
	private String itemDiscount;
	private String productPrice;
	private String isReturned;
	private String itemStatus;
	
	public ItemDetailsDTO(String itemId, String itemName, String brand, Double unitMRP, Double unitDiscount,
			Double unitDiscountType, Double buyQuantity, Double getQuantity) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.brand = brand;
		this.unitMRP = unitMRP;
		this.unitDiscount = unitDiscount;
		this.unitDiscountType = unitDiscountType;
		this.buyQuantity = buyQuantity;
		this.getQuantity = getQuantity;
	}
	
	public ItemDetailsDTO(String itemId, String itemName, String hsnCode, String price, String sgst, String cgst,
			String igst, String itemQuantity, String itemTaxAmount, String itemDiscount) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.hsnCode = hsnCode;
		this.price = price;
		this.sgst = sgst;
		this.cgst = cgst;
		this.igst = igst;
		this.itemQuantity = itemQuantity;
		this.itemTaxAmount = itemTaxAmount;
		this.itemDiscount = itemDiscount;
	}
	
	public ItemDetailsDTO(String itemId, String itemName, String hsnCode, String itemQuantity, String itemTaxAmount,
			String itemTaxSgst, String itemTaxCgst, String itemTaxIgst, String itemDiscount, String productPrice,
			String isReturned) {
		this.itemId = itemId;
		this.itemName = itemName;
		this.hsnCode = hsnCode;
		this.itemQuantity = itemQuantity;
		this.itemTaxAmount = itemTaxAmount;
		this.itemTaxSgst = itemTaxSgst;
		this.itemTaxCgst = itemTaxCgst;
		this.itemTaxIgst = itemTaxIgst;
		this.itemDiscount = itemDiscount;
		this.productPrice = productPrice;
		this.isReturned = isReturned;
	}

	public ItemDetailsDTO(String itemId, String itemName, String itemDescription, String hsnCode, String brand,
			String itemType, String itemSubType, Double quantityAvailable, Double itemThresholdToNotify,
			Double unitCostPrice, Double unitSellingPrice, Double unitDiscount, Double unitDiscountType,
			Double buyQuantity, Double getQuantity, Double unitProfit, Double unitProfitPercentage,
			Double gstTaxPercentage, Double unitMRP, String itemExpiryDate, String inventoryDate) {
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemDescription = itemDescription;
		this.hsnCode = hsnCode;
		this.brand = brand;
		this.itemType = itemType;
		this.itemSubType = itemSubType;
		this.quantityAvailable = quantityAvailable;
		this.itemThresholdToNotify = itemThresholdToNotify;
		this.unitCostPrice = unitCostPrice;
		this.unitSellingPrice = unitSellingPrice;
		this.unitDiscount = unitDiscount;
		this.unitDiscountType = unitDiscountType;
		this.buyQuantity = buyQuantity;
		this.getQuantity = getQuantity;
		this.unitProfit = unitProfit;
		this.unitProfitPercentage = unitProfitPercentage;
		this.gstTaxPercentage = gstTaxPercentage;
		this.unitMRP = unitMRP;
		this.itemExpiryDate = itemExpiryDate;
		this.inventoryDate = inventoryDate;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public String getHsnCode() {
		return hsnCode;
	}

	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getItemSubType() {
		return itemSubType;
	}

	public void setItemSubType(String itemSubType) {
		this.itemSubType = itemSubType;
	}

	public Double getQuantityAvailable() {
		return quantityAvailable;
	}

	public void setQuantityAvailable(Double quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}

	public Double getItemThresholdToNotify() {
		return itemThresholdToNotify;
	}

	public void setItemThresholdToNotify(Double itemThresholdToNotify) {
		this.itemThresholdToNotify = itemThresholdToNotify;
	}

	public Double getUnitCostPrice() {
		return unitCostPrice;
	}

	public void setUnitCostPrice(Double unitCostPrice) {
		this.unitCostPrice = unitCostPrice;
	}

	public Double getUnitSellingPrice() {
		return unitSellingPrice;
	}

	public void setUnitSellingPrice(Double unitSellingPrice) {
		this.unitSellingPrice = unitSellingPrice;
	}

	public Double getUnitDiscount() {
		return unitDiscount;
	}

	public void setUnitDiscount(Double unitDiscount) {
		this.unitDiscount = unitDiscount;
	}

	public Double getUnitDiscountType() {
		return unitDiscountType;
	}

	public void setUnitDiscountType(Double unitDiscountType) {
		this.unitDiscountType = unitDiscountType;
	}

	public Double getBuyQuantity() {
		return buyQuantity;
	}

	public void setBuyQuantity(Double buyQuantity) {
		this.buyQuantity = buyQuantity;
	}

	public Double getGetQuantity() {
		return getQuantity;
	}

	public void setGetQuantity(Double getQuantity) {
		this.getQuantity = getQuantity;
	}

	public Double getUnitProfit() {
		return unitProfit;
	}

	public void setUnitProfit(Double unitProfit) {
		this.unitProfit = unitProfit;
	}

	public Double getUnitProfitPercentage() {
		return unitProfitPercentage;
	}

	public void setUnitProfitPercentage(Double unitProfitPercentage) {
		this.unitProfitPercentage = unitProfitPercentage;
	}

	public Double getGstTaxPercentage() {
		return gstTaxPercentage;
	}

	public void setGstTaxPercentage(Double gstTaxPercentage) {
		this.gstTaxPercentage = gstTaxPercentage;
	}

	public Double getUnitMRP() {
		return unitMRP;
	}

	public void setUnitMRP(Double unitMRP) {
		this.unitMRP = unitMRP;
	}

	public String getItemExpiryDate() {
		return itemExpiryDate;
	}

	public void setItemExpiryDate(String itemExpiryDate) {
		this.itemExpiryDate = itemExpiryDate;
	}

	public String getInventoryDate() {
		return inventoryDate;
	}

	public void setInventoryDate(String inventoryDate) {
		this.inventoryDate = inventoryDate;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getSgst() {
		return sgst;
	}

	public void setSgst(String sgst) {
		this.sgst = sgst;
	}

	public String getCgst() {
		return cgst;
	}

	public void setCgst(String cgst) {
		this.cgst = cgst;
	}

	public String getIgst() {
		return igst;
	}

	public void setIgst(String igst) {
		this.igst = igst;
	}

	public String getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(String itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public String getItemTaxAmount() {
		return itemTaxAmount;
	}

	public void setItemTaxAmount(String itemTaxAmount) {
		this.itemTaxAmount = itemTaxAmount;
	}

	public String getItemTaxSgst() {
		return itemTaxSgst;
	}

	public void setItemTaxSgst(String itemTaxSgst) {
		this.itemTaxSgst = itemTaxSgst;
	}

	public String getItemTaxCgst() {
		return itemTaxCgst;
	}

	public void setItemTaxCgst(String itemTaxCgst) {
		this.itemTaxCgst = itemTaxCgst;
	}

	public String getItemTaxIgst() {
		return itemTaxIgst;
	}

	public void setItemTaxIgst(String itemTaxIgst) {
		this.itemTaxIgst = itemTaxIgst;
	}

	public String getItemDiscount() {
		return itemDiscount;
	}

	public void setItemDiscount(String itemDiscount) {
		this.itemDiscount = itemDiscount;
	}

	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	public String getIsReturned() {
		return isReturned;
	}

	public void setIsReturned(String isReturned) {
		this.isReturned = isReturned;
	}

	public String getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}	
}