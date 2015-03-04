package com.mavrick.common.vo.order;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.mavrick.common.vo.MaverickVO;
import com.mavrick.persistance.entities.MavrickEntity;

public class OrderVO implements MaverickVO{
	private int orderId;
	
	private int userId;
		
	private Date submittedDate;

	private String state;
	
	private List<CommerceItemVO> commerceItems;
	
	private PriceInfoVO orderPrice;
	
	public PriceInfoVO getOrderSubTotalPrice() {
		return orderSubTotalPrice;
	}

	public void setOrderSubTotalPrice(PriceInfoVO orderSubTotalPrice) {
		this.orderSubTotalPrice = orderSubTotalPrice;
	}

	public PriceInfoVO getOrderShippingTotalPrice() {
		return orderShippingTotalPrice;
	}

	public void setOrderShippingTotalPrice(PriceInfoVO orderShippingTotalPrice) {
		this.orderShippingTotalPrice = orderShippingTotalPrice;
	}

	public PriceInfoVO getOrderTaxTotalPrice() {
		return orderTaxTotalPrice;
	}

	public void setOrderTaxTotalPrice(PriceInfoVO orderTaxTotalPrice) {
		this.orderTaxTotalPrice = orderTaxTotalPrice;
	}

	private PriceInfoVO orderSubTotalPrice;
	private PriceInfoVO orderShippingTotalPrice;
	private PriceInfoVO orderTaxTotalPrice;

	

	
	private OrderDeliveryDetailsVO deliveryDetailsVO;
	
	private OrderShipAddressVO shipAddressVO;
	
	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public double getShippingTotal() {
		return shippingTotal;
	}

	public void setShippingTotal(double shippingTotal) {
		this.shippingTotal = shippingTotal;
	}

	public double getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(double orderTotal) {
		this.orderTotal = orderTotal;
	}

	public double getTaxTotal() {
		return taxTotal;
	}

	public void setTaxTotal(double taxTotal) {
		this.taxTotal = taxTotal;
	}

	private double subTotal;
	private double shippingTotal;
	private double orderTotal;
	private double taxTotal;
	/*
	
	private OrderPayment orderPayment;
	*/


	
	
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}


	public Date getSubmittedDate() {
		return submittedDate;
	}

	public void setSubmittedDate(Date date) {
		this.submittedDate = date;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<CommerceItemVO> getCommerceItems() {
		return commerceItems;
	}

	public void setCommerceItems(List<CommerceItemVO> commerceItems) {
		this.commerceItems = commerceItems;
	}
	
	public PriceInfoVO getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(PriceInfoVO orderPrice) {
		this.orderPrice = orderPrice;
	}

	public OrderDeliveryDetailsVO getDeliveryDetailsVO() {
		return deliveryDetailsVO;
	}

	public void setDeliveryDetailsVO(OrderDeliveryDetailsVO deliveryDetailsVO) {
		this.deliveryDetailsVO = deliveryDetailsVO;
	}

	public OrderShipAddressVO getShipAddressVO() {
		return shipAddressVO;
	}

	public void setShipAddressVO(OrderShipAddressVO shipAddressVO) {
		this.shipAddressVO = shipAddressVO;
	}
}
