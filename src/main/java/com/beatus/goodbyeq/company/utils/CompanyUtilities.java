package com.beatus.goodbyeq.company.utils;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.beatus.goodbyeq.company.model.ItemsDTO;
import com.beatus.goodbyeq.company.model.SearchResultsDTO;
import com.beatus.goodbyeq.company.service.exception.GoodByeQCompanyServiceException;

public class CompanyUtilities {

	public static String createStoresJSONObject(ArrayList<SearchResultsDTO> searchResultsByCoordinates) throws GoodByeQCompanyServiceException, JSONException {
		String jsonResponse  = "";
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject item = new JSONObject();
		
		for(SearchResultsDTO searchResultsDTO : searchResultsByCoordinates) {

			item.put("name", searchResultsDTO.getName());
			item.put("address", searchResultsDTO.getVicinity());
			item.put("rating", searchResultsDTO.getRating());
			array.put(item);
			item = new JSONObject();
			
			json.put("results", array);
		}
		
		jsonResponse = json.toString();
		
		return jsonResponse;
	}
	
	public static String createItemsJSONObject(ItemsDTO itemsDTO) throws GoodByeQCompanyServiceException, JSONException {
		String jsonResponse  = "";
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject item = new JSONObject();
		
		item.put("itemName", itemsDTO.getName());
		item.put("discount", itemsDTO.getDiscount());
		item.put("price", itemsDTO.getPrice());
		item.put("sgst", itemsDTO.getSgst());
		item.put("cgst", itemsDTO.getCgst());
		array.put(item);
		item = new JSONObject();
		
		json.put("results", array);
		jsonResponse = json.toString();
		
		return jsonResponse;
	}
	
	public static String readJSONObject(String inputJSONStr, String key) throws GoodByeQCompanyServiceException, JSONException {
		String jsonObjectValue = "";
		
		// Create a JSON object hierarchy from the results
        JSONObject jsonObj = new JSONObject(inputJSONStr);
        jsonObjectValue = jsonObj.getString(key);
        return jsonObjectValue;
	}
}
