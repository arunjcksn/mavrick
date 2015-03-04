package com.mavrick.fulfillment.order.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.mavrick.persistance.entities.MavrickEntity;

@Entity
@Table(name = "MVK_ORDER_ADJUSTMENT")
public class FulfillmentOrderAdjustment implements MavrickEntity {

	
	@Id
	@Column(name = "ORDER_ID")
	private String orderId;
	
	@Column(name = "COST_ADJ_REASON_CODE")
	private int costAdjustmentReasonCode;
	
	@Column(name = "SEQUENCE_NUM")
	private String sequenceNum;
	
	@Column(name = "PRODUCT_ID")
	private String productId;
	
	@Column(name = "AMOUNT")
	private Double amount;	
	
	
	@Column(name = "CHANGE_USER_ID")
	private Double changeUserId;	
	
	@Column(name = "CHANGE_TS")
	private Double changeTs;	
	
	
	@Column(name = "UPC_CODE")
	private Double upcCode;


	public String getOrderId() {
		return orderId;
	}


	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


	public int getCostAdjustmentReasonCode() {
		return costAdjustmentReasonCode;
	}


	public void setCostAdjustmentReasonCode(int costAdjustmentReasonCode) {
		this.costAdjustmentReasonCode = costAdjustmentReasonCode;
	}


	public String getSequenceNum() {
		return sequenceNum;
	}


	public void setSequenceNum(String sequenceNum) {
		this.sequenceNum = sequenceNum;
	}


	public String getProductId() {
		return productId;
	}


	public void setProductId(String productId) {
		this.productId = productId;
	}


	public Double getAmount() {
		return amount;
	}


	public void setAmount(Double amount) {
		this.amount = amount;
	}


	public Double getChangeUserId() {
		return changeUserId;
	}


	public void setChangeUserId(Double changeUserId) {
		this.changeUserId = changeUserId;
	}


	public Double getChangeTs() {
		return changeTs;
	}


	public void setChangeTs(Double changeTs) {
		this.changeTs = changeTs;
	}


	public Double getUpcCode() {
		return upcCode;
	}


	public void setUpcCode(Double upcCode) {
		this.upcCode = upcCode;
	}	
	
	
}
