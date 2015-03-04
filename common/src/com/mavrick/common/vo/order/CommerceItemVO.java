package com.mavrick.common.vo.order;

import java.sql.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.util.List;

import com.mavrick.common.vo.MaverickVO;
import com.mavrick.persistance.entities.MavrickEntity;

/**
 * This entity represents a SKU
 * 
 * @author oracle
 * 
 */

public class CommerceItemVO implements MaverickVO {
	
	private int commerceItemId;
	
	private String displayName;
	
	private int orderId;
	
	private int skuId;
	
	
	private int qty;
	
	
	private double unitPrice;
	
	
	private int priceId;
	
	
	private PriceInfoVO itemPriceInfoVO;

	public PriceInfoVO getItemPriceInfo() {
		return itemPriceInfoVO;
	}

	public void setItemPriceInfo(PriceInfoVO itemPriceInfoVO) {
		this.itemPriceInfoVO = itemPriceInfoVO;
	}

	public int getCommerceItemId() {
		return commerceItemId;
	}

	public void setCommerceItemId(int commerceItemId) {
		this.commerceItemId = commerceItemId;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getSkuId() {
		return skuId;
	}

	public void setSkuId(int skuId) {
		this.skuId = skuId;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getPriceId() {
		return priceId;
	}

	public void setPriceId(int priceId) {
		this.priceId = priceId;
	}
	

}

