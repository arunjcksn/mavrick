package com.mavrick.fulfillment.order.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.mavrick.persistance.entities.MavrickEntity;


@Entity
@Table(name = "MVK_ORDER_DISCOUNT")
public class FulfillmentOrderDiscount implements MavrickEntity{

	@Id
	@Column(name="ORDER_ID")
	private String orderId;
	
	@Id
	@Column(name="DISCOUNT_SEQ_NUM")
	private int discountSeqNumber;
	
	@Id
	@Column(name="STORE_PROMOTION_ID")
	private String storePromotionId;
	
	@Id
	@Column(name="DISCOUNT_AMT")
	private Double amount;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getDiscountSeqNumber() {
		return discountSeqNumber;
	}

	public void setDiscountSeqNumber(int discountSeqNumber) {
		this.discountSeqNumber = discountSeqNumber;
	}

	public String getStorePromotionId() {
		return storePromotionId;
	}

	public void setStorePromotionId(String storePromotionId) {
		this.storePromotionId = storePromotionId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	
	
	
}
