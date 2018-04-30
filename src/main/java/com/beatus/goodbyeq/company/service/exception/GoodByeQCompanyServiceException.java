package com.beatus.goodbyeq.company.service.exception;

import com.beatus.goodbyeq.company.exception.ResponseEntityException;

public class GoodByeQCompanyServiceException extends ResponseEntityException {
	
	/**
	 * @author vakey15
	 * This is a exception that is thrown when there is a bad data in the request parameters
	 */
	private static final long serialVersionUID = 1L;

	public GoodByeQCompanyServiceException(String message) {
        super(message);
    }

    public GoodByeQCompanyServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}
