package com.mavrick.common.vo.order;

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

import com.mavrick.common.vo.MaverickVO;
import com.mavrick.persistance.entities.MavrickEntity;

public class OrderDeliveryDetailsVO implements MaverickVO {
	
	private int orderDelId;
	
	private int orderId;
	
	private String deliveryStopId;
	
	private double weight;
	
	private double volume;

	private String storeId;
	
	private Calendar delDate;
	
	private String delStartTime;
	
	private String delEndTime;
	
	private int doorStepTime;
	
	private int status;
	
	private int priceId;
	
	
	private PriceInfoVO shippingPriceInfo;
	
	public int getPriceId() {
		return priceId;
	}

	public void setPriceId(int priceId) {
		this.priceId = priceId;
	}

	public PriceInfoVO getShippingPriceInfo() {
		return shippingPriceInfo;
	}

	public void setShippingPriceInfo(PriceInfoVO shippingPriceInfo) {
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
