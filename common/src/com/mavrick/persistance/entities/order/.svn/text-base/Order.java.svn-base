package com.mavrick.persistance.entities.order;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.mavrick.common.json.annotation.Groups;
import com.mavrick.common.json.annotation.ResponseGroup;
import com.mavrick.persistance.entities.MavrickEntity;

@Entity
@Table(name = "MVK_ORDER")
public class Order implements MavrickEntity {
	@TableGenerator(name="USER_GENERATOR",
	    table="GENERATED_KEYS",
	    pkColumnName="PK_COLUMN",
	    valueColumnName="VALUE_COLUMN",
	    pkColumnValue="ORDER_ID",
	    allocationSize=100
		)
		@Id
		@GeneratedValue(strategy = GenerationType.TABLE, generator="USER_GENERATOR")
		@Column(name = "ORDER_ID")
	private int orderId;
	
	@Column(name = "USER_ID")
	private int userId;
	
	@Column(name = "PRICE_INFO")
	private int priceId;
	
	@Column(name = "SUBMITTED_DATE")
	private Date submittedDate;

	@Column(name = "STATE")
	private String state;
	
	@OneToMany
    @JoinTable(
            name="MVK_ORDER_PRICE_REL",
            joinColumns = @JoinColumn( name="ORDER_ID"),
            inverseJoinColumns = @JoinColumn( name="PRICE_ID")
    )
	@ResponseGroup(fieldType=Groups.EXTENED)
	private Collection<PriceInfo> orderPrice;
	
	public Collection<PriceInfo> getOrderPrice() {
		if (orderPrice == null) {
			orderPrice = new ArrayList<PriceInfo>();
		}
		return orderPrice;
	}

	public void setOrderPrice(Collection<PriceInfo> orderPrice) {
		this.orderPrice = orderPrice;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orderId")
	@ResponseGroup(fieldType=Groups.EXTENED)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<CommerceItem> commerceItems;
	
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "orderId")
	@ResponseGroup(fieldType=Groups.EXTENED)
	private List<OrderDeliveryDetails> deliveryDetails;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orderId")
	@ResponseGroup(fieldType=Groups.EXTENED)
	private List<OrderPayment> orderPayment;
	
	public List<OrderPayment> getOrderPayment() {
		return orderPayment;
	}

	public void setOrderPayment(List<OrderPayment> orderPayment) {
		this.orderPayment = orderPayment;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orderId")
	private List<OrderShipAddress> shipAddress;
	
	


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

	public int getPriceId() {
		return priceId;
	}

	public void setPriceId(int priceId) {
		this.priceId = priceId;
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

	public List<CommerceItem> getCommerceItems() {
		return commerceItems;
	}

	public void setCommerceItems(List<CommerceItem> commerceItems) {
		this.commerceItems = commerceItems;
	}
	
	public List<OrderDeliveryDetails> getDeliveryDetails() {
		return deliveryDetails;
	}

	public void setDeliveryDetails(List<OrderDeliveryDetails> deliveryDetails) {
		this.deliveryDetails = deliveryDetails;
	}

	public List<OrderShipAddress> getShipAddress() {
		return shipAddress;
	}

	public void setShipAddress(List<OrderShipAddress> shipAddress) {
		this.shipAddress = shipAddress;
	}

	
}
