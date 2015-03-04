package com.mavrick.fulfillment.order.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.mavrick.persistance.entities.MavrickEntity;

@Entity
@Table(name = "MVK_ORDER_SUBSTITUTION")

public class FulfillmentSubstitutionLine implements MavrickEntity {

	@Id
	@Column(name="ORDER_ID")
	
	private String orderId;
	
	@Column(name="PRODUCT_ID")
	private String productId;
	
	@Column(name="UPC_NUM")
	private String upcNum;
	
	@Column(name="PICK_QUANTITY")
	private int pickQuantity;
	
	@Column(name="UNIT_RETAIL_AMT")
	private Double unitRetailAmount;
	
	@Column(name="PICK_CREATE_DATE")
	private Date pickTimeStamp;
	
	@Column(name="USER_ID")
	private String userId;
	
	@Column(name="TOTE_NBR")
	private String toteNumber;
	
	@Column(name="PRODUCT_DESCRIPTITON")
	private String productDescription;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
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
