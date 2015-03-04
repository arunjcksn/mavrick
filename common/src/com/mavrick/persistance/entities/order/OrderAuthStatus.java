package com.mavrick.persistance.entities.order;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.mavrick.persistance.entities.MavrickEntity;

@Entity
@Table(name = "MVK_ORDER_AUTH_STATUS")
public class OrderAuthStatus implements MavrickEntity {
	@Id
	@Column(name = "ORDER_AUTH_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int orderAuthId;
	
	@Column(name = "PAYMENT_ID")
	private int paymentId;
	
	@Column(name = "AMOUNT")
	private double amount;
	
	@Column(name = "AUTH_ID")
	private int authId;

	@Column(name = "AUTH_STATUS")
	private String authStatus;
	
	@Column(name = "SETLMNT_STATUS")
	private String settlmntStatus;

	public int getOrderAuthId() {
		return orderAuthId;
	}

	public void setOrderAuthId(int orderAuthId) {
		this.orderAuthId = orderAuthId;
	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getAuthId() {
		return authId;
	}

	public void setAuthId(int authId) {
		this.authId = authId;
	}

	public String getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(String authStatus) {
		this.authStatus = authStatus;
	}

	public String getSettlmntStatus() {
		return settlmntStatus;
	}

	public void setSettlmntStatus(String settlmntStatus) {
		this.settlmntStatus = settlmntStatus;
	}
	
}
