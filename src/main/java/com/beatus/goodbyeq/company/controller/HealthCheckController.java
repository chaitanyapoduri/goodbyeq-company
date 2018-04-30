package com.beatus.goodbyeq.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.beatus.goodbyeq.company.utils.CompanyConstants;

@Controller
public class HealthCheckController {
	@RequestMapping(value= "/healthcheck", method = RequestMethod.GET)
    public String getHealthCheckStatus(){
		return CompanyConstants.SUCCESS;
		
	}

}
