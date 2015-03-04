package com.mavrick.fulfillment.order.vo;

import com.mavrick.common.vo.MaverickVO;

public class FulfillmentOrderAdjustmentVO implements MaverickVO {
	
	private String orderId;

	private int costAdjustmentReasonCode;

	private String sequenceNum;

	private String productId;

	private Double amount;

	private Double changeUserId;

	private Double changeTs;

	private Double upcCode;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String string) {
		this.orderId = string;
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
