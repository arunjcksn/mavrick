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
public class FulfillmentOrder implements MavrickEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
		@Id
		@Column(name = "ORDER_ID")
	private String orderId;
	
	@Column(name = "ORDER_SEQ_NUMBER")
	private int orderSequenceNumber;
	
	@Column(name = "USER_ID")
	private int userId;
	
	@Column(name = "LOAD_NUMBER")
	private int load_number;
	
	@Column(name = "DELIVERY_DATE")
	private Date deliveryDate;

	@Column(name = "STATUS")
	private String status;
	
	@Column(name="SUBSTITUTION_IND")
	private boolean subsitutionIndicator;
	
	@Column(name="CREATE_DATE")
	private Date createDate;
	
	@Column(name="WEB_AMT")
	private Double webAmount;
	
	@Column(name="POS_AMT")
	private Double posAmount;
	
	@Column(name="POS_TRANSACTION_NUMBER")
	private String postransactionNumber;
	
	@Column(name="POS_CREATE_DATE")
	private Date posCreateDate;
	
	@Column(name="DELIVERY_INSTRN")
	private String deliveryInstructions;
	
	@Column(name="CUSTOMER_SIGNTAURE_DATE")
	private Date customerSignatureDate;
	
	@Column(name="LATE_DELIVERY_IND")
	private boolean lateDeliveryInd;
	
	@Column(name="PICK_N_COLLECT_IND")
	private boolean pickNCollectInd;
	
	@Column(name="AUTHORISED_IND")
	private boolean authorisedInd;
	
	@Column(name="REJECT_REASON_CODE")
	private String rejectReasonCode;
	
	@Column(name="DELIVERY_CHARGE")
	private Double deliveryCharge;
	
	@Column(name="TAX_AMOUNT")
	private Double taxAmount;
	
	@Column(name="TOTAL_DISCOUNT_AMT")
	private Double totalDiscountAmount;
	
	@Column(name="PLAN_DELIVERY_TS")
	private Date plannedDeliveryTime;
	
	@Column(name="NOTIFY_FS_CODE")
	private int notifyFSCode;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orderId")
	private List<FulfillmentOrderLine> fulfillmentOrderLines;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orderId")
	private List<FulfillmentSubstitutionLine> fulfillmentSubLines;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orderId")
	private List<FulfillmentOrderAdjustment> fulfillmentAdjLines;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orderId")
	private List<FulfillmentPickOrderLine> fulfillmentPickOrderLine;
	
	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getOrderSequenceNumber() {
		return orderSequenceNumber;
	}

	public void setOrderSequenceNumber(int orderSequenceNumber) {
		this.orderSequenceNumber = orderSequenceNumber;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getLoad_number() {
		return load_number;
	}

	public void setLoad_number(int load_number) {
		this.load_number = load_number;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isSubsitutionIndicator() {
		return subsitutionIndicator;
	}

	public void setSubsitutionIndicator(boolean subsitutionIndicator) {
		this.subsitutionIndicator = subsitutionIndicator;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Double getWebAmount() {
		return webAmount;
	}

	public void setWebAmount(Double webAmount) {
		this.webAmount = webAmount;
	}

	public Double getPosAmount() {
		return posAmount;
	}

	public void setPosAmount(Double posAmount) {
		this.posAmount = posAmount;
	}

	public String getPostransactionNumber() {
		return postransactionNumber;
	}

	public void setPostransactionNumber(String postransactionNumber) {
		this.postransactionNumber = postransactionNumber;
	}

	public Date getPosCreateDate() {
		return posCreateDate;
	}

	public void setPosCreateDate(Date posCreateDate) {
		this.posCreateDate = posCreateDate;
	}

	public String getDeliveryInstructions() {
		return deliveryInstructions;
	}

	public void setDeliveryInstructions(String deliveryInstructions) {
		this.deliveryInstructions = deliveryInstructions;
	}

	public Date getCustomerSignatureDate() {
		return customerSignatureDate;
	}

	public void setCustomerSignatureDate(Date customerSignatureDate) {
		this.customerSignatureDate = customerSignatureDate;
	}

	public boolean isLateDeliveryInd() {
		return lateDeliveryInd;
	}

	public void setLateDeliveryInd(boolean lateDeliveryInd) {
		this.lateDeliveryInd = lateDeliveryInd;
	}

	public boolean isPickNCollectInd() {
		return pickNCollectInd;
	}

	public void setPickNCollectInd(boolean pickNCollectInd) {
		this.pickNCollectInd = pickNCollectInd;
	}

	public boolean isAuthorisedInd() {
		return authorisedInd;
	}

	public void setAuthorisedInd(boolean authorisedInd) {
		this.authorisedInd = authorisedInd;
	}

	public String getRejectReasonCode() {
		return rejectReasonCode;
	}

	public void setRejectReasonCode(String rejectReasonCode) {
		this.rejectReasonCode = rejectReasonCode;
	}

	public Double getDeliveryCharge() {
		return deliveryCharge;
	}

	public void setDeliveryCharge(Double deliveryCharge) {
		this.deliveryCharge = deliveryCharge;
	}

	public Double getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(Double taxAmount) {
		this.taxAmount = taxAmount;
	}

	public Double getTotalDiscountAmount() {
		return totalDiscountAmount;
	}

	public void setTotalDiscountAmount(Double totalDiscountAmount) {
		this.totalDiscountAmount = totalDiscountAmount;
	}

	public Date getPlannedDeliveryTime() {
		return plannedDeliveryTime;
	}

	public void setPlannedDeliveryTime(Date plannedDeliveryTime) {
		this.plannedDeliveryTime = plannedDeliveryTime;
	}

	public int getNotifyFSCode() {
		return notifyFSCode;
	}

	public void setNotifyFSCode(int notifyFSCode) {
		this.notifyFSCode = notifyFSCode;
	}

	public List<FulfillmentOrderLine> getFulfillmentOrderLines() {
		return fulfillmentOrderLines;
	}

	public void setFulfillmentOrderLines(
			List<FulfillmentOrderLine> fulfillmentOrderLines) {
		this.fulfillmentOrderLines = fulfillmentOrderLines;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

	public List<FulfillmentSubstitutionLine> getFulfillmentSubLines() {
		return fulfillmentSubLines;
	}

	public void setFulfillmentSubLines(
			List<FulfillmentSubstitutionLine> fulfillmentSubLines) {
		this.fulfillmentSubLines = fulfillmentSubLines;
	}

	public List<FulfillmentOrderAdjustment> getFulfillmentAdjLines() {
		return fulfillmentAdjLines;
	}

	public void setFulfillmentAdjLines(
			List<FulfillmentOrderAdjustment> fulfillmentAdjLines) {
		this.fulfillmentAdjLines = fulfillmentAdjLines;
	}

	public List<FulfillmentPickOrderLine> getFulfillmentPickLines() {
		// TODO Auto-generated method stub
		return fulfillmentPickOrderLine;
	}
	
	

	
}
