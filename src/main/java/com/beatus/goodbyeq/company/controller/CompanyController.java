package com.beatus.goodbyeq.company.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beatus.goodbyeq.company.model.CompanyDTO;
import com.beatus.goodbyeq.company.model.JSendResponse;
import com.beatus.goodbyeq.company.service.api.CompanyService;
import com.beatus.goodbyeq.company.utils.CompanyConstants;
import com.beatus.goodbyeq.company.utils.GoodByeQMediaType;
import com.beatus.goodbyeq.company.validation.exception.GoodByeQClientValidationException;

@Controller
public class CompanyController extends BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);	
	
	@Resource(name = "companyService")
	private CompanyService companyService;

	private JSendResponse<CompanyDTO> jsend(CompanyDTO companyData) {
		if (companyData == null) {
			return new JSendResponse<CompanyDTO>(CompanyConstants.FAILURE, companyData);
		} else {
			return new JSendResponse<CompanyDTO>(CompanyConstants.SUCCESS, companyData);
		}
	}

	@RequestMapping(value = "/getCompanyDetailsById", method = RequestMethod.GET, 
			produces = { GoodByeQMediaType.APPLICATION_JSON })
	public @ResponseBody JSendResponse<CompanyDTO> getCompanyDetailsById(
			@RequestParam(CompanyConstants.COMPANY_ID) String companyId,
			HttpServletRequest request, HttpServletResponse response) 
					throws GoodByeQClientValidationException {
		String METHOD_NAME = "getCompanyDetailsById()";
		long startTime = System.nanoTime();
		CompanyDTO companyDTO = null;
		logger.debug(METHOD_NAME + "::companyId loaded:- " + companyId);
		
		try {		
			companyDTO = companyService.fetchCompanyDetails(companyId);
		}catch(Exception exception) {
			logger.error(METHOD_NAME + "::Exception has occurred due to " + exception.getMessage()); 
		}
		long endTime = System.nanoTime();
		logger.debug(METHOD_NAME + "::completed in " +(endTime - startTime)/1000000 + " ms");
		return jsend(companyDTO);
	}
	
	/**
	 * Search the scanned product details which includes discount, price, tax from the database.
	 * @param searchItemString - Scanned item bar code	
	 */
	@RequestMapping(value = "/saveCompanyDetails", method = RequestMethod.POST, 
			consumes = {GoodByeQMediaType.APPLICATION_JSON}, produces = {GoodByeQMediaType.APPLICATION_JSON})
	public @ResponseBody JSendResponse<String> createItems(@RequestBody String companyDetailsString, 
			HttpServletRequest request,
			HttpServletResponse response) throws GoodByeQClientValidationException {
		String METHOD_NAME = "createBills()";
		long startTime = System.nanoTime();
		String responseMessage = null;
		logger.info(METHOD_NAME + "::companyDetailsString loaded:- " + companyDetailsString);
		try {		
			responseMessage = companyService.addCompanyDetails(companyDetailsString);
		}catch(Exception exception) {
			logger.error(METHOD_NAME + "::Exception has occurred due to " + exception.getMessage()); 
		}
		long endTime = System.nanoTime();
		logger.info(METHOD_NAME + "::completed in " +(endTime - startTime)/1000000 + " ms");
		return jsend(responseMessage);
	}	
}