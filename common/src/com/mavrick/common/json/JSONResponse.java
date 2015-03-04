package com.mavrick.common.json;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mavrick.common.vo.MaverickVO;
import com.mavrick.persistance.entities.MavrickEntity;

public class JSONResponse {

	

	String payLoadName;

	List<MaverickVO> responsePayload = new ArrayList<MaverickVO>();

	List<String> errors = new ArrayList<String>();

	String statusCode;

	String statusMessage;

	@Deprecated
	String entityName;

	@Deprecated
	List<MaverickVO> responseVOs;
	@Deprecated
	private MavrickEntity entity;

	@Deprecated
	List<Map<String, Object>> additionalObjects;

	public JSONResponse() {

	}

	public JSONResponse(String name) {
		this.entityName = name;
		this.payLoadName = name;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	@Deprecated
	public String getEntityName() {
		return entityName;
	}

	@Deprecated
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	@Deprecated
	public List<MaverickVO> getResponseVOs() {
		return responseVOs;
	}

	public void setResponseVOs(List<MaverickVO> responseVOs) {
		this.responseVOs = responseVOs;
	}

	@Deprecated
	public MavrickEntity getEntity() {
		return entity;
	}

	@Deprecated
	public void setEntity(MavrickEntity entity) {
		this.entity = entity;
	}

	@Deprecated
	public List<Map<String, Object>> getAdditionalObjects() {
		return additionalObjects;
	}

	@Deprecated
	public void setAdditionalObjects(List<Map<String, Object>> additionalObjects) {
		this.additionalObjects = additionalObjects;
	}
	public String getPayLoadName() {
		return payLoadName;
	}

	public void setPayLoadName(String payLoadName) {
		this.payLoadName = payLoadName;
	}

	public List<MaverickVO> getResponsePayload() {
		return responsePayload;
	}

	public void setResponsePayload(List<MaverickVO> responsePayload) {
		this.responsePayload = responsePayload;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
}
