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
@Table(name = "MVK_ORDER_PAYMENT")
public class OrderPayment implements MavrickEntity {
	@Id
	@Column(name = "PAYMENT_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int paymentId;
	
	@Column(name = "ORDER_ID")
	private int orderId;
	
	@Column(name = "PAYMENT_TYPE")
	private int paymentType;
	
	
	@Column(name = "AMOUNT")
	private double amount;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "paymentId")
	private List<OrderAuthStatus> orderAuthStatus;

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(int paymentType) {
		this.paymentType = paymentType;
	}

	

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public List<OrderAuthStatus> getOrderAuthStatus() {
		return orderAuthStatus;
	}

	public void setOrderAuthStatus(List<OrderAuthStatus> orderAuthStatus) {
		this.orderAuthStatus = orderAuthStatus;
	}
	
	
}
