package com.mavrick.fulfillment.order.vo;

import java.util.Date;

import com.mavrick.common.vo.MaverickVO;

public class FulfillmentSubstitutionLineVO implements MaverickVO {
	

	private String orderId;
	
	
	private String productId;
	
	
	private String upcNum;
	
	
	private int pickQuantity;
	
	
	private Double unitRetailAmount;
	
	
	private Date pickTimeStamp;
	
	
	private String userId;
	
	
	private String toteNumber;
	
	
	private String productDescription;


	public String getOrderId() {
		return orderId;
	}


	public void setOrderId(String string) {
		this.orderId = string;
	}


	public String getProductId() {
		return productId;
	}


	public void setProductId(String string) {
		this.productId = string;
	}


	public String getUpcNum() {
		return upcNum;
	}


	public void setUpcNum(String upcNum) {
		this.upcNum = upcNum;
	}


	public int getPickQuantity() {
		return pickQuantity;
	}


	public void setPickQuantity(int pickQuantity) {
		this.pickQuantity = pickQuantity;
	}


	public Double getUnitRetailAmount() {
		return unitRetailAmount;
	}


	public void setUnitRetailAmount(Double unitRetailAmount) {
		this.unitRetailAmount = unitRetailAmount;
	}


	public Date getPickTimeStamp() {
		return pickTimeStamp;
	}


	public void setPickTimeStamp(Date pickTimeStamp) {
		this.pickTimeStamp = pickTimeStamp;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getToteNumber() {
		return toteNumber;
	}


	public void setToteNumber(String toteNumber) {
		this.toteNumber = toteNumber;
	}


	public String getProductDescription() {
		return productDescription;
	}


	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

}
