package com.mavrick.persistance.entities.order;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.mavrick.persistance.entities.MavrickEntity;

@Entity
@Table(name = "MVK_ORDER_PRICE")
public class PriceInfo implements MavrickEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2867896702070429317L;

	@Id
	@Column(name = "PRICE_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int priceId;
	
	@Column(name = "AMOUNT")
	private double amount;
	
	@Column(name = "ADJUSTMENT")
	private int adjustment;
	
	@Column(name = "TYPE")
	private String type="item";

	public int getPriceId() {
		return priceId;
	}

	public void setPriceId(int priceId) {
		this.priceId = priceId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getAdjustment() {
		return adjustment;
	}

	public void setAdjustment(int adjustment) {
		this.adjustment = adjustment;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
