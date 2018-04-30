package com.beatus.goodbyeq.company.dac;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.beatus.goodbyeq.company.model.AddressDTO;
import com.beatus.goodbyeq.company.model.BillDTO;
import com.beatus.goodbyeq.company.model.CompanyDTO;
import com.beatus.goodbyeq.company.model.ItemDetailsDTO;
import com.beatus.goodbyeq.company.model.StoreDetailsDTO;
import com.beatus.goodbyeq.company.utils.CompanyConstants;

@Component("companyDBService")
public class CompanyDBService {
	
	private static final Logger logger = LoggerFactory.getLogger(CompanyDBService.class);
	@Value(value = "${db.dbname:localhost}")
	private String dbSchema;
	
	@Resource(name = "companyDBUtils")
	private CompanyDBUtils companyDBUtils;

	public ArrayList<StoreDetailsDTO> checkDBForRegisteredStores(String latitude, String longitude,
			String radius) throws ClassNotFoundException, SQLException {
		Connection dbConnection = null;
		Statement statement = null;
		StoreDetailsDTO storesListDTO = null;
		ArrayList<StoreDetailsDTO> storesList = new ArrayList<StoreDetailsDTO>();  
		dbConnection = companyDBUtils.getRemoteConnection();
		statement = dbConnection.createStatement();
		String fetchItemDetailsSQL = "SELECT * FROM(" +
			    "SELECT *,(((acos(sin(("+latitude+"*pi()/180)) * sin((GEO_LATITUDE*pi()/180))+cos(("+latitude+"*pi()/180)) * cos((GEO_LATITUDE*pi()/180)) * cos((("+longitude+" - GEO_LONGITUDE)*pi()/180))))*180/pi())*60*1.1515*1.609344) as distance "
			    		+ "FROM " + dbSchema + ".GBQ_STORE) t " +
				"WHERE distance <= " + radius;
		logger.info("Stores list query:- " + fetchItemDetailsSQL);
		ResultSet rs = statement.executeQuery(fetchItemDetailsSQL);
		while (rs.next()) {
			storesListDTO = new StoreDetailsDTO(rs.getString("GEO_LATITUDE"), rs.getString("GEO_LONGITUDE"),
					rs.getString("STORE_ID"), rs.getString("COMPANY_ID"), rs.getString("STORE_NAME"),
					new AddressDTO(rs.getString("ADDRESS"), rs.getString("CITY"), rs.getString("STATE"), rs.getString("ZIPCODE")));
			storesList.add(storesListDTO);
		}		
		return storesList;
	}
	
	public CompanyDTO fetchCompanyDetails(String companyId) throws ClassNotFoundException, SQLException {
		Connection dbConnection = null;
		Statement statement = null;
		CompanyDTO companyDetailsDTO = null;
		AddressDTO companyAddressDTO = null;
		dbConnection = companyDBUtils.getRemoteConnection();
		statement = dbConnection.createStatement();
 
		String fetchCompanyDetailsSQL = "SELECT * "
				+ "FROM "+dbSchema+".GBQ_COMPANY "
				+ "WHERE COMPANY_ID = '" + companyId + "'";
		logger.info(fetchCompanyDetailsSQL);
		ResultSet rs = statement.executeQuery(fetchCompanyDetailsSQL);
		while (rs.next()) {
			companyAddressDTO  = new AddressDTO(rs.getString("ADDRESS"), rs.getString("CITY"), rs.getString("STATE"), rs.getString("ZIPCODE"));
			
			companyDetailsDTO = new CompanyDTO(rs.getString("COMPANY_ID"), rs.getString("COMPANY_NAME"), rs.getString("EMAIL"), 
					rs.getString("PHONE"), companyAddressDTO);
		}
		return companyDetailsDTO;
	}
	
	public BillDTO fetchBillDetailsByBillId(String billId) throws ClassNotFoundException, SQLException {
		Connection dbConnection = null;
		Statement statement = null;
		BillDTO billDTO = null;
		List<ItemDetailsDTO> itemDetailsDTOList = new ArrayList<ItemDetailsDTO>();
		dbConnection = companyDBUtils.getRemoteConnection();
		statement = dbConnection.createStatement();
		
		itemDetailsDTOList = fetchItemsListForBillId(billId);
		if(itemDetailsDTOList != null) {
			logger.info(itemDetailsDTOList.size() + "number of Items found for Bill # " + billId);
		}
		String fetchCompanyDetailsSQL = "SELECT * "
				+ "FROM "+dbSchema+".GBQ_BILL "
				+ "WHERE BILL_ID = '" + billId + "'";
		logger.info(fetchCompanyDetailsSQL);
		ResultSet rs = statement.executeQuery(fetchCompanyDetailsSQL);
		while (rs.next()) {			
			billDTO = new BillDTO(rs.getString("BILL_ID"), rs.getString("USER_ID"), rs.getString("STORE_ID"), 
					rs.getString("COMPANY_ID"), rs.getString("ITEM_QTY"), rs.getString("TOTAL_QTY"),
					rs.getDouble("TOTAL_AMT"), rs.getDouble("TOTAL_TAX"), rs.getString("IS_RETURNED"),
					rs.getDouble("TOTAL_SGST"), rs.getDouble("TOTAL_CGST"), rs.getDouble("TOTAL_IGST"),
					rs.getString("RETURN_REASON"), rs.getString("BILL_CREATE_TIMESTAMP"),  
					rs.getString("BILL_UPDATE_TIMESTAMP"), CompanyConstants.BILL_FOUND, itemDetailsDTOList);
		}
		return billDTO;
	}
	
	public List<BillDTO> fetchBillDetailsByStoreId(String storeId) throws ClassNotFoundException, SQLException {
		Connection dbConnection = null;
		Statement statement = null;
		BillDTO billDTO = null;
		List<BillDTO> billDTOList = new ArrayList<BillDTO>();
		List<ItemDetailsDTO> itemDetailsDTOList = new ArrayList<ItemDetailsDTO>();
		dbConnection = companyDBUtils.getRemoteConnection();
		statement = dbConnection.createStatement();
		
		String fetchCompanyDetailsSQL = "SELECT * "
				+ "FROM "+dbSchema+".GBQ_BILL "
				+ "WHERE STORE_ID = '" + storeId + "'";
		logger.info("fetchBillDetailsByStoreId()::" + fetchCompanyDetailsSQL);
		ResultSet rs = statement.executeQuery(fetchCompanyDetailsSQL);
		while (rs.next()) {			
			billDTO = new BillDTO(rs.getString("BILL_ID"), rs.getString("USER_ID"), rs.getString("STORE_ID"), 
					rs.getString("COMPANY_ID"), rs.getString("ITEM_QTY"), rs.getString("TOTAL_QTY"),
					rs.getDouble("TOTAL_AMT"), rs.getDouble("TOTAL_TAX"), rs.getString("IS_RETURNED"),
					rs.getDouble("TOTAL_SGST"), rs.getDouble("TOTAL_CGST"), rs.getDouble("TOTAL_IGST"),
					rs.getString("RETURN_REASON"), rs.getString("BILL_CREATE_TIMESTAMP"),  
					rs.getString("BILL_UPDATE_TIMESTAMP"));
			itemDetailsDTOList = fetchItemsListForBillId(billDTO.getBillId());
			billDTO.setBillStatus(CompanyConstants.BILL_FOUND);
			billDTO.setItems(itemDetailsDTOList);
			billDTOList.add(billDTO);
		}
			
		return billDTOList;
	}

	private List<ItemDetailsDTO> fetchItemsListForBillId(String billId) throws ClassNotFoundException, SQLException {
		List<ItemDetailsDTO> itemDetailsDTOList = new ArrayList<ItemDetailsDTO>();
		Connection dbConnection = null;
		Statement statement = null;
		
		dbConnection = companyDBUtils.getRemoteConnection();
		statement = dbConnection.createStatement();
		
		String fetchItemsListSQL = "SELECT * "
				+ "FROM "+dbSchema+".GBQ_BILL_ITEM "
				+ "WHERE BILL_ID = '" + billId + "'";
		logger.info("fetchItemsListForBillId()" + fetchItemsListSQL);
		
		ResultSet rs = statement.executeQuery(fetchItemsListSQL);
		while (rs.next()) {
			itemDetailsDTOList.add(new ItemDetailsDTO(rs.getString("ITEM_ID"), rs.getString("ITEM_NAME"), rs.getString("HSN_CODE"), 
					rs.getString("ITEM_QUANTITY"), rs.getString("ITEM_TAX_AMOUNT"), 
					rs.getString("ITEM_TAX_SGST"), rs.getString("ITEM_TAX_CGST"), rs.getString("ITEM_TAX_IGST"), 					
					rs.getString("ITEM_DISCOUNT"), rs.getString("PRODUCT_PRICE"), 
					rs.getString("IS_RETURNED")));
		}
		return itemDetailsDTOList;
	}

	public String createItem(ItemDetailsDTO itemDetailsDTO) throws ClassNotFoundException, SQLException {
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;
		dbConnection = companyDBUtils.getRemoteConnection();
		int statusCode = 0;
		
		preparedStatement = dbConnection.prepareStatement("INSERT INTO "+dbSchema+".GBQ_ITEM "
				+ "(ITEM_ID, ITEM_NAME, ITEM_DESCRIPTION, HSN_CODE, BRAND, ITEM_TYPE, ITEM_SUB_TYPE, "
				+ "QUANTITY_AVAILABLE, ITEM_THRESHOLD_TO_NOTIFY, UNIT_COST_PRICE, UNIT_SELLING_PRICE,"
				+ "UNIT_DISCOUNT, UNIT_DISCOUNT_TYPE, BUY_QUANTITY, GET_QUANTITY, UNIT_PROFIT, UNIT_PROFIT_PERCENTAGE,"
				+ "GST_TAX_PERCENTAGE, UNIT_MRP) " + 
				"VALUES" + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		preparedStatement.setString(1, itemDetailsDTO.getItemId());
		preparedStatement.setString(2, itemDetailsDTO.getItemName());
		preparedStatement.setString(3, itemDetailsDTO.getItemDescription());
		preparedStatement.setString(4, itemDetailsDTO.getHsnCode());
		preparedStatement.setString(5, itemDetailsDTO.getBrand());
		preparedStatement.setString(6, itemDetailsDTO.getItemType());
		preparedStatement.setString(7, itemDetailsDTO.getItemSubType());
		preparedStatement.setDouble(8, itemDetailsDTO.getQuantityAvailable());
		preparedStatement.setDouble(9, itemDetailsDTO.getItemThresholdToNotify());
		preparedStatement.setDouble(10, itemDetailsDTO.getUnitCostPrice());
		preparedStatement.setDouble(11, itemDetailsDTO.getUnitSellingPrice());
		preparedStatement.setDouble(12, itemDetailsDTO.getUnitDiscount());
		preparedStatement.setDouble(13, itemDetailsDTO.getUnitDiscountType());
		preparedStatement.setDouble(14, itemDetailsDTO.getBuyQuantity());
		preparedStatement.setDouble(15, itemDetailsDTO.getGetQuantity());
		preparedStatement.setDouble(16, itemDetailsDTO.getUnitProfit());
		preparedStatement.setDouble(17, itemDetailsDTO.getUnitProfitPercentage());
		preparedStatement.setDouble(18, itemDetailsDTO.getGstTaxPercentage());
		preparedStatement.setDouble(19, itemDetailsDTO.getUnitMRP());
		preparedStatement.setString(20, itemDetailsDTO.getItemExpiryDate());
		preparedStatement.setString(21, itemDetailsDTO.getInventoryDate());
		
		// execute insert SQL statement
		statusCode = preparedStatement.executeUpdate();
		logger.info("Record is inserted into GBQ_ITEM table with status " + statusCode);
		return String.valueOf(statusCode);
	}
	
	public String createItems(List<ItemDetailsDTO> itemDetailsDTOList) throws ClassNotFoundException, SQLException {
		Connection dbConnection = null;
		dbConnection = companyDBUtils.getRemoteConnection();
		Statement statement = dbConnection.createStatement();
		int statusCode[] = null;
		String query = null;
		
		for (ItemDetailsDTO itemDetailsDTO: itemDetailsDTOList) {
			query = "INSERT INTO "+dbSchema+".GBQ_ITEM "
					+ "(ITEM_ID, ITEM_NAME, ITEM_DESCRIPTION, HSN_CODE, BRAND, ITEM_TYPE, ITEM_SUB_TYPE, "
					+ "QUANTITY_AVAILABLE, ITEM_THRESHOLD_TO_NOTIFY, UNIT_COST_PRICE, UNIT_SELLING_PRICE,"
					+ "UNIT_DISCOUNT, UNIT_DISCOUNT_TYPE, BUY_QUANTITY, GET_QUANTITY, UNIT_PROFIT, UNIT_PROFIT_PERCENTAGE,"
					+ "GST_TAX_PERCENTAGE, UNIT_MRP) "
					+ "values('"
					+ itemDetailsDTO.getItemId() + "','" + itemDetailsDTO.getItemName() + "','"  
					+ itemDetailsDTO.getItemDescription() + "','" + itemDetailsDTO.getHsnCode() + "','" 
					+ itemDetailsDTO.getBrand() + "','" + itemDetailsDTO.getItemType() + "','" 
					+ itemDetailsDTO.getItemSubType() + "'," + itemDetailsDTO.getQuantityAvailable() + "," 
					+ itemDetailsDTO.getItemThresholdToNotify() + "," + itemDetailsDTO.getUnitCostPrice() + "," 
					+ itemDetailsDTO.getUnitSellingPrice() + "," + itemDetailsDTO.getUnitDiscount() + ","
					+ itemDetailsDTO.getUnitDiscountType() + "," + itemDetailsDTO.getBuyQuantity() + ","
					+ itemDetailsDTO.getGetQuantity() + "," + itemDetailsDTO.getUnitProfit() + ","
					+ itemDetailsDTO.getUnitProfitPercentage() + "," + itemDetailsDTO.getGstTaxPercentage() + ","
					+ itemDetailsDTO.getUnitMRP() + ",'" + itemDetailsDTO.getItemExpiryDate() + "','"
					+ itemDetailsDTO.getInventoryDate() + "')";
			statement.addBatch(query);
		}
		
		// execute insert SQL statement
		statusCode = statement.executeBatch();
		logger.info("Record is inserted into GBQ_ITEM table with status " + statusCode);
		statement.close();
		dbConnection.close();		
		return statusCode.toString();
	}
}