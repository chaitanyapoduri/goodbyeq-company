package com.beatus.goodbyeq.company.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.beatus.goodbyeq.company.dac.CompanyDBService;
import com.beatus.goodbyeq.company.model.BillDTO;
import com.beatus.goodbyeq.company.model.CompanyDTO;
import com.beatus.goodbyeq.company.model.ItemDetailsDTO;
import com.beatus.goodbyeq.company.service.api.CompanyService;
import com.beatus.goodbyeq.company.utils.CompanyConstants;
import com.beatus.goodbyeq.company.utils.CompanyUtilities;
import com.beatus.goodbyeq.company.validation.CompanyValidator;
import com.beatus.goodbyeq.company.validation.exception.GoodByeQValidationException;

@Component("companyService")
public class CompanyServiceImpl implements CompanyService{
	
	@Resource(name = "companyValidator")
	private CompanyValidator companyValidator;
	
	@Resource(name = "companyDBService")
	private CompanyDBService companyDBService;
	
	
	private static final Logger logger = LoggerFactory.getLogger(CompanyServiceImpl.class);

	@Override
	public CompanyDTO fetchCompanyDetails(String companyId) throws GoodByeQValidationException, ClassNotFoundException, SQLException{
		CompanyDTO companyDTO = null;
		companyDTO = companyDBService.fetchCompanyDetails(companyId);
		if(companyDTO != null) {
			logger.info("Company Name:- " + companyDTO.getCompanyName());	
			companyDTO.setStatus(CompanyConstants.COMPANY_FOUND);
		}
		else {
			companyDTO = new CompanyDTO(CompanyConstants.COMPANY_NOT_FOUND);
		}
		return companyDTO;
	}
	
	@Override
	public BillDTO fetchBillDetailsByBillId(String billId) throws GoodByeQValidationException, ClassNotFoundException, SQLException{
		BillDTO billDTO = null;
		billDTO = companyDBService.fetchBillDetailsByBillId(billId);
		if(billDTO != null) {
			logger.info("Bill Id:- " + billDTO.getBillId());	
			billDTO.setBillStatus(CompanyConstants.BILL_FOUND);
		}
		else {
			billDTO = new BillDTO(CompanyConstants.BILL_NOT_FOUND);
		}
		return billDTO;
	}
	
	@Override
	public List<BillDTO> fetchBillDetailsByStoreId(String storeId) throws GoodByeQValidationException, ClassNotFoundException, SQLException{
		List<BillDTO> billDTOList = new ArrayList<BillDTO>();
		billDTOList = companyDBService.fetchBillDetailsByStoreId(storeId);
		if(billDTOList != null) {
			logger.info(billDTOList.size() + " Bills found!");	
		}
		return billDTOList;
	}
	
	@Override
	public String createItem(String scannedItemsStr) throws GoodByeQValidationException, 
	ClassNotFoundException, JSONException, IOException, SQLException {
		String responseMessage = null;
		ItemDetailsDTO itemDetailsDTO = null;
		
		String itemId = CompanyUtilities.readJSONObject(scannedItemsStr, "itemId");
		String itemName = CompanyUtilities.readJSONObject(scannedItemsStr, "itemName");
		String itemDescription = CompanyUtilities.readJSONObject(scannedItemsStr, "itemDescription");
		logger.info("itemId:- " + itemId + " itemName:- " + itemName);
		String hsnCode = CompanyUtilities.readJSONObject(scannedItemsStr, "hsnCode");
		String brand = CompanyUtilities.readJSONObject(scannedItemsStr, "brand");
		String itemType = CompanyUtilities.readJSONObject(scannedItemsStr, "itemType");
		String itemSubType = CompanyUtilities.readJSONObject(scannedItemsStr, "itemSubType");
		String quantityAvailable = CompanyUtilities.readJSONObject(scannedItemsStr, "quantityAvailable");
		String itemThresholdToNotify = CompanyUtilities.readJSONObject(scannedItemsStr, "itemThresholdToNotify");
		String unitCostPrice = CompanyUtilities.readJSONObject(scannedItemsStr, "unitCostPrice");	
		String unitSellingPrice = CompanyUtilities.readJSONObject(scannedItemsStr, "unitSellingPrice");
		String unitDiscount = CompanyUtilities.readJSONObject(scannedItemsStr, "unitDiscount");
		String unitDiscountType = CompanyUtilities.readJSONObject(scannedItemsStr, "unitDiscountType");
		String buyQuantity = CompanyUtilities.readJSONObject(scannedItemsStr, "buyQuantity");
		String getQuantity = CompanyUtilities.readJSONObject(scannedItemsStr, "getQuantity");
		String unitProfit = CompanyUtilities.readJSONObject(scannedItemsStr, "unitProfit");
		String unitProfitPercentage = CompanyUtilities.readJSONObject(scannedItemsStr, "unitProfitPercentage");
		String gstTaxPercentage = CompanyUtilities.readJSONObject(scannedItemsStr, "gstTaxPercentage");
		String unitMRP = CompanyUtilities.readJSONObject(scannedItemsStr, "unitMRP");
		String itemExpiryDate = CompanyUtilities.readJSONObject(scannedItemsStr, "itemExpiryDate");
		String inventoryDate = CompanyUtilities.readJSONObject(scannedItemsStr, "inventoryDate");
	
		itemDetailsDTO = new ItemDetailsDTO(itemId, itemName, itemDescription, hsnCode, brand, 
				itemType, itemSubType, Double.parseDouble(quantityAvailable), 
				Double.parseDouble(itemThresholdToNotify),
				Double.parseDouble(unitCostPrice), Double.parseDouble(unitSellingPrice),
				Double.parseDouble(unitDiscount), Double.parseDouble(unitDiscountType),
				Double.parseDouble(buyQuantity), Double.parseDouble(getQuantity),
				Double.parseDouble(unitProfit), Double.parseDouble(unitProfitPercentage),
				Double.parseDouble(gstTaxPercentage), Double.parseDouble(unitMRP),
				itemExpiryDate, inventoryDate); 
		responseMessage = companyDBService.createItem(itemDetailsDTO); 
		return responseMessage;
	}
	
	@Override
	public String createItems(String scannedItemsStr) throws GoodByeQValidationException, 
	ClassNotFoundException, JSONException, IOException, SQLException {
		String responseMessage = null;
		List<ItemDetailsDTO> itemDetailsList = new ArrayList<ItemDetailsDTO>();
        JSONObject itemDetailsJSONObj = new JSONObject(scannedItemsStr);
        JSONArray itemDetailsJSONArray = itemDetailsJSONObj.getJSONArray("itemsList");
        int itemsCount = itemDetailsJSONArray.length();
		for (int i = 0; i < itemsCount; i++) {
			String itemId  = itemDetailsJSONArray.getJSONObject(i).getString("itemId");
			String itemName  = itemDetailsJSONArray.getJSONObject(i).getString("itemName");
			logger.info("Item#" + (i+1) + "::Item Id:- " + itemId + " Item Name:- " + itemName);
			String itemDescription  = itemDetailsJSONArray.getJSONObject(i).getString("itemDescription");
			String hsnCode  = itemDetailsJSONArray.getJSONObject(i).getString("hsnCode");
			String brand  = itemDetailsJSONArray.getJSONObject(i).getString("brand");
			String itemType  = itemDetailsJSONArray.getJSONObject(i).getString("itemType");
			String itemSubType  = itemDetailsJSONArray.getJSONObject(i).getString("itemSubType");
			String quantityAvailable  = itemDetailsJSONArray.getJSONObject(i).getString("quantityAvailable");
			String itemThresholdToNotify  = itemDetailsJSONArray.getJSONObject(i).getString("itemThresholdToNotify");
			String unitCostPrice  = itemDetailsJSONArray.getJSONObject(i).getString("unitCostPrice");
			String unitSellingPrice  = itemDetailsJSONArray.getJSONObject(i).getString("unitSellingPrice");
			String unitDiscount  = itemDetailsJSONArray.getJSONObject(i).getString("unitDiscount");
			 
			String unitDiscountType = itemDetailsJSONArray.getJSONObject(i).getString("unitDiscountType");
			String buyQuantity = itemDetailsJSONArray.getJSONObject(i).getString("buyQuantity");
			String getQuantity = itemDetailsJSONArray.getJSONObject(i).getString("getQuantity");
			String unitProfit = itemDetailsJSONArray.getJSONObject(i).getString("unitProfit");
			String unitProfitPercentage = itemDetailsJSONArray.getJSONObject(i).getString("unitProfitPercentage");
			String gstTaxPercentage = itemDetailsJSONArray.getJSONObject(i).getString("gstTaxPercentage");
			String unitMRP = itemDetailsJSONArray.getJSONObject(i).getString("unitMRP");
			String itemExpiryDate = itemDetailsJSONArray.getJSONObject(i).getString("itemExpiryDate");
			String inventoryDate = itemDetailsJSONArray.getJSONObject(i).getString("inventoryDate");
				
			itemDetailsList.add(new ItemDetailsDTO(itemId, itemName, itemDescription, hsnCode, brand, 
				itemType, itemSubType, Double.parseDouble(quantityAvailable), 
				Double.parseDouble(itemThresholdToNotify),
				Double.parseDouble(unitCostPrice), Double.parseDouble(unitSellingPrice),
				Double.parseDouble(unitDiscount), Double.parseDouble(unitDiscountType),
				Double.parseDouble(buyQuantity), Double.parseDouble(getQuantity),
				Double.parseDouble(unitProfit), Double.parseDouble(unitProfitPercentage),
				Double.parseDouble(gstTaxPercentage), Double.parseDouble(unitMRP),
				itemExpiryDate, inventoryDate));
		}

		responseMessage = companyDBService.createItems(itemDetailsList); 
		return responseMessage;
	}

	@Override
	public String addCompanyDetails(String scannedItemsStr) throws GoodByeQValidationException, 
		ClassNotFoundException, JSONException, IOException, SQLException{
		String responseMessage = null;
		
		return responseMessage;
	}
}