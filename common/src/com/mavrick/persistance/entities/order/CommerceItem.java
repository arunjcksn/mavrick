package com.mavrick.persistance.entities.order;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.mavrick.common.json.annotation.Groups;
import com.mavrick.common.json.annotation.ResponseGroup;
import com.mavrick.persistance.entities.MavrickEntity;

/**
 * This entity represents a SKU
 * 
 * @author oracle
 * 
 */
@Entity
@Table(name = "MVK_ORDER_ITEM")
public class CommerceItem implements MavrickEntity {
	@Id
	@Column(name = "COMMERCE_ITEM_ID")
	private int commerceItemId;
	@Column(name = "DISPLAY_NAME")
	private String displayName;
	@Column(name = "ORDER_ID")
	private int orderId;
	@Column(name = "SKU_ID")
	private int skuId;
	
	@Column(name = "QTY")
	private int qty;
	
	@Column(name = "UNIT_PRICE")
	private double unitPrice;
	
	@Column(name = "PRICE_INFO")
	private int priceId;
	
	@ResponseGroup(fieldType=Groups.EXTENED)
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "priceId")
	private List<PriceInfo> itemPriceInfos;

	public List<PriceInfo> getItemPriceInfo() {
		return itemPriceInfos;
	}

	public void setItemPriceInfo(List<PriceInfo> itemPriceInfos) {
		this.itemPriceInfos = itemPriceInfos;
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
	

	//added sku parameters for the commerce item starts
	
	
	
	//added sku parameters for the commerce item ends
}

