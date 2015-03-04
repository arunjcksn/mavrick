package com.mavrick.fulfillment.order.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.mavrick.persistance.entities.MavrickEntity;
@Entity
@Table(name = "MVK_PICK_ORDER_LINE")
public class FulfillmentPickOrderLine implements MavrickEntity{

	@Id
	@Column(name = "PRODUCT_ID")
	private String productId;
	
	@Column(name = "ORDER_ID")
	private String orderId;
	
	@Column(name = "PICK_DATE")
	private Date pickTimeStamp;
	
	@Column(name = "TOTE_NUMBER")
	private int toteNumber;
	
	@Column(name = "PICK_QUANTITY")
	private int pickQuantity;	
	
	
	@Column(name = "USER_ID")
	private String userId;	
	
	@Column(name = "UPC_CODE")
	private String upcCode;	
	
	
	@Column(name = "UNIT_RETAIL_AMOUNT")
	private Double unitRetailAmount;	
	
	@Column(name="OVERRIDE_IND")
	private boolean overrideInd;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Date getPickTimeStamp() {
		return pickTimeStamp;
	}

	public void setPickTimeStamp(Date date) {
		this.pickTimeStamp = date;
	}

	public int getToteNumber() {
		return toteNumber;
	}

	public void setToteNumber(int toteNumber) {
		this.toteNumber = toteNumber;
	}

	public int getPickQuantity() {
		return pickQuantity;
	}

	public void setPickQuantity(int qty) {
		this.pickQuantity = qty;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String string) {
		this.userId = string;
	}

	public String getUpcCode() {
		return upcCode;
	}

	public void setUpcCode(String string) {
		this.upcCode = string;
	}

	public Double getUnitRetailAmount() {
		return unitRetailAmount;
	}

	public void setUnitRetailAmount(Double unitRetailAmount) {
		this.unitRetailAmount = unitRetailAmount;
	}

	public boolean isOverrideInd() {
		return overrideInd;
	}

	public void setOverrideInd(boolean overrideInd) {
		this.overrideInd = overrideInd;
	}
	
	
}
