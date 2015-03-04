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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.mavrick.common.vo.MaverickVO;
import com.mavrick.persistance.entities.MavrickEntity;

public class OrderPaymentDetailVO implements MaverickVO {
	private int payDetailId;
	
	private int profileCcId;
	
	private int ccLastDigits;
	
	private Date expiryDate;

	public int getPayDetailId() {
		return payDetailId;
	}

	public void setPayDetailId(int payDetailId) {
		this.payDetailId = payDetailId;
	}

	public int getProfileCcId() {
		return profileCcId;
	}

	public void setProfileCcId(int profileCcId) {
		this.profileCcId = profileCcId;
	}

	public int getCcLastDigits() {
		return ccLastDigits;
	}

	public void setCcLastDigits(int ccLastDigits) {
		this.ccLastDigits = ccLastDigits;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

}
