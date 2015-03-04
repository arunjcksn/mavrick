package com.mavrick.common.validators;

import java.util.ArrayList;
import java.util.List;

import com.mavrick.common.json.JSONResponse;
/**
 * This class encapsulate all the errors that needs to be sent as JSON response.
 * @author oracle
 *
 */
public class ValidationErrorDTO extends JSONResponse {
	private List<ErrorDTO> errors = new ArrayList<ErrorDTO>();

	public ValidationErrorDTO() {
		super("ERROR");
	}
	
	public ValidationErrorDTO(String entityType) {
		super("ERROR");
	}

	public void addFieldError(String path, String message) {
		ErrorDTO error = new ErrorDTO(path, message);
		errors.add(error);
	}

 

}
