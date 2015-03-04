package com.mavrick.fulfillment.order.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.mavrick.persistance.entities.MavrickEntity;

@Entity
@Table(name = "MVK_FULFILLMENT_ORDER")
public class FulfillmentLoad implements MavrickEntity {

	@Id
	@Column(name = "LOAD_ID")
	private String loadId;

	@Column(name = "USER_ID")
	private int userId;

	@Column(name = "DELIVERY_DATE")
	private Date deliveryDate;

	@Column(name = "LOAD_STATUS")
	private String loadStatus;

	@Column(name = "BEGIN_MILEAGE")
	private double beginMileage;

	@Column(name = "END_MILEAGE")
	private double endMileage;

	@Column(name = "MOB_PHONE_NUM")
	private String mobilePhoneNum;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "loadId")
	private List<FulfillmentOrder> fulfillmentOrder;

	public String getLoadId() {
		return loadId;
	}

	public void setLoadId(String loadId) {
		this.loadId = loadId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getLoadStatus() {
		return loadStatus;
	}

	public void setLoadStatus(String loadStatus) {
		this.loadStatus = loadStatus;
	}

	public double getBeginMileage() {
		return beginMileage;
	}

	public void setBeginMileage(double beginMileage) {
		this.beginMileage = beginMileage;
	}

	public double getEndMileage() {
		return endMileage;
	}

	public void setEndMileage(double endMileage) {
		this.endMileage = endMileage;
	}

	public String getMobilePhoneNum() {
		return mobilePhoneNum;
	}

	public void setMobilePhoneNum(String mobilePhoneNum) {
		this.mobilePhoneNum = mobilePhoneNum;
	}

	public List<FulfillmentOrder> getFulfillmentOrder() {
		return fulfillmentOrder;
	}

	public void setFulfillmentOrder(List<FulfillmentOrder> fulfillmentOrder) {
		this.fulfillmentOrder = fulfillmentOrder;
	}

}
