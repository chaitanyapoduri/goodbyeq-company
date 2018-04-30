package com.beatus.goodbyeq.company.service.api;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.json.JSONException;

import com.beatus.goodbyeq.company.model.BillDTO;
import com.beatus.goodbyeq.company.model.CompanyDTO;
import com.beatus.goodbyeq.company.validation.exception.GoodByeQValidationException;


public interface CompanyService {
	
	public CompanyDTO fetchCompanyDetails(String companyId) throws GoodByeQValidationException, ClassNotFoundException, SQLException;
	public BillDTO fetchBillDetailsByBillId(String billId) throws GoodByeQValidationException, ClassNotFoundException, SQLException;
	public String createItem(String scannedItemsStr) throws GoodByeQValidationException, 
		ClassNotFoundException, JSONException, IOException, SQLException;
	public List<BillDTO> fetchBillDetailsByStoreId(String storeId) throws GoodByeQValidationException, ClassNotFoundException, SQLException;
	public String createItems(String scannedItemsStr) throws GoodByeQValidationException, 
		ClassNotFoundException, JSONException, IOException, SQLException;
	public String addCompanyDetails(String scannedItemsStr) throws GoodByeQValidationException, 
		ClassNotFoundException, JSONException, IOException, SQLException;
}
