package com.mavrick.common.exceptions;


import java.util.List;

public class MavrickException extends Exception {

	String errorCode;

	String errorMessage;

	List<String> errors;

	public MavrickException(String eC, String eM) {
		this.errorCode = eC;
		this.errorMessage = eM;
	}

	public MavrickException(String eC, String eM, List<String> errors) {
		this.errorCode = eC;
		this.errorMessage = eM;
		this.errors = errors;
	}

	public MavrickException(String eC, List<String> errors) {
		this.errorCode = eC;
		this.errors = errors;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
