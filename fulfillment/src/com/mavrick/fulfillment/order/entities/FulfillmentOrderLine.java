package com.mavrick.fulfillment.order.entities;

import java.sql.Date;






import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.util.List;

import com.mavrick.persistance.entities.MavrickEntity;



/**
 * This entity represents a order line item
 * 
 * @author oracle
 * 
 */
@Entity
@Table(name = "MVK_ORDER_LINE")
public class FulfillmentOrderLine implements MavrickEntity {
	@Id
	@Column(name = "PRODUCT_ID")
	private String productId;
	@Column(name = "ORDER_ID")
	private String orderId;
	@Column(name = "PRODUCT_TYPE_CODE")
	private int productTypeCode;
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "DESCRITPTION")
	private String description;
	
	@Column(name = "ORDER_QTY")
	private int orderQuantity;
	
	@Column(name = "ORDER_WEIGHT")
	private Double orderWeight;	
	
	
	@Column(name = "SALES_UNIT")
	private Double salesUnit;	
	
	@Column(name = "SUBSTITUTION_QTY")
	private Double subsitutionQuantity;	
	
	
	@Column(name = "UNIT_RETAIL_AMOUNT")
	private Double unitRetailAmount;	
	
	@Column(name = "SUBSTITUTE_RETAIL_AMOUNT")
	private Double subsRetailAmount;	
	
	@Column(name = "WEB_UNIT_RETAIL_AMOUNT")
	private Double webUnitRetailAmount;	
	
	@Column(name = "SUBSTITUTION_IND") 
	private boolean substituionInd;
	
	@Column(name = "SYSTEM_MESSAGE") 
	private String systemMessage;
	
	@Column(name = "WEIGHTED_ITEM_IND") 
	private boolean weightedItemInd;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getProductTypeCode() {
		return productTypeCode;
	}

	public void setProductTypeCode(int productTypeCode) {
		this.productTypeCode = productTypeCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public Double getOrderWeight() {
		return orderWeight;
	}

	public void setOrderWeight(Double orderWeight) {
		this.orderWeight = orderWeight;
	}

	public Double getSalesUnit() {
		return salesUnit;
	}

	public void setSalesUnit(Double salesUnit) {
		this.salesUnit = salesUnit;
	}

	public Double getSubsitutionQuantity() {
		return subsitutionQuantity;
	}

	public void setSubsitutionQuantity(Double subsitutionQuantity) {
		this.subsitutionQuantity = subsitutionQuantity;
	}

	public Double getUnitRetailAmount() {
		return unitRetailAmount;
	}

	public void setUnitRetailAmount(Double unitRetailAmount) {
		this.unitRetailAmount = unitRetailAmount;
	}

	public Double getSubsRetailAmount() {
		return subsRetailAmount;
	}

	public void setSubsRetailAmount(Double subsRetailAmount) {
		this.subsRetailAmount = subsRetailAmount;
	}

	public Double getWebUnitRetailAmount() {
		return webUnitRetailAmount;
	}

	public void setWebUnitRetailAmount(Double webUnitRetailAmount) {
		this.webUnitRetailAmount = webUnitRetailAmount;
	}

	public boolean isSubstituionInd() {
		return substituionInd;
	}

	public void setSubstituionInd(boolean substituionInd) {
		this.substituionInd = substituionInd;
	}

	public String getSystemMessage() {
		return systemMessage;
	}

	public void setSystemMessage(String systemMessage) {
		this.systemMessage = systemMessage;
	}

	public boolean isWeightedItemInd() {
		return weightedItemInd;
	}

	public void setWeightedItemInd(boolean weightedItemInd) {
		this.weightedItemInd = weightedItemInd;
	}
	
	
	
	
	

}

