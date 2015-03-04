package com.mavrick.persistance.entities.order;

import java.util.Calendar;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.mavrick.persistance.entities.MavrickEntity;

@Entity
@Table(name = "MVK_ORDER_DEL_DETAILS")
public class OrderDeliveryDetails implements MavrickEntity {
	
	@Id
	@Column(name = "ORDER_DEL_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int orderDelId;
	
	@Column(name = "ORDER_ID")
	private int orderId;
	
	@Column(name = "DELIVERY_STOP_ID")
	private String deliveryStopId;
	
	@Column(name = "WEIGHT")
	private double weight;
	
	@Column(name = "VOLUME")
	private double volume;

	@Column(name = "STORE_ID")
	private String storeId;
	
	@Column(name = "DEL_DATE")
	private Calendar delDate;
	
	@Column(name = "DEL_START_TIME")
	private String delStartTime;
	
	@Column(name = "DEL_END_TIME")
	private String delEndTime;
	
	@Column(name = "DST")
	private int doorStepTime;
	
	@Column(name = "STATUS")
	private int status;
	
	@Column(name = "PRICE_ID")
	private int priceId;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "priceId")
	private List<PriceInfo> shippingPriceInfo;
	
	public int getPriceId() {
		return priceId;
	}

	public void setPriceId(int priceId) {
		this.priceId = priceId;
	}

	public List<PriceInfo> getShippingPriceInfo() {
		return shippingPriceInfo;
	}

	public void setShippingPriceInfo(List<PriceInfo> shippingPriceInfo) {
		this.shippingPriceInfo = shippingPriceInfo;
	}

	

	public int getOrderDelId() {
		return orderDelId;
	}

	public void setOrderDelId(int orderDelId) {
		this.orderDelId = orderDelId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getDeliveryStopId() {
		return deliveryStopId;
	}

	public void setDeliveryStopId(String deliveryStopId) {
		this.deliveryStopId = deliveryStopId;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public Calendar getDelDate() {
		return delDate;
	}

	public void setDelDate(Calendar delDate) {
		this.delDate = delDate;
	}

	public String getDelStartTime() {
		return delStartTime;
	}

	public void setDelStartTime(String delStartTime) {
		this.delStartTime = delStartTime;
	}

	public String getDelEndTime() {
		return delEndTime;
	}

	public void setDelEndTime(String delEndTime) {
		this.delEndTime = delEndTime;
	}

	public int getDoorStepTime() {
		return doorStepTime;
	}

	public void setDoorStepTime(int doorStepTime) {
		this.doorStepTime = doorStepTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
}
