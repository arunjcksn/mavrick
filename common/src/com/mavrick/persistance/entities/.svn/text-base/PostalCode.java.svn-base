package com.mavrick.persistance.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "MVK_POSTAL_CODE", uniqueConstraints = {
		@UniqueConstraint(columnNames = "POSTAL_CODE")
		})
public class PostalCode implements MavrickEntity {

	@Id
	@Column(name="POSTAL_CODE")
	private String postalCode;
	
	@Column(name="ENABLED")
	private boolean enabledForDelivery;
	
	@Column(name="LOCATION")
	private String location;
	
	@Column(name="DISTRICT")
	private String district;
	
	
	@Column(name="STATE")
	private String state;


	public String getPostalCode() {
		return postalCode;
	}


	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}


	public boolean isEnabledForDelivery() {
		return enabledForDelivery;
	}


	public void setEnabledForDelivery(boolean enabledForDelivery) {
		this.enabledForDelivery = enabledForDelivery;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public String getDistrict() {
		return district;
	}


	public void setDistrict(String district) {
		this.district = district;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}
	
	
	
}
