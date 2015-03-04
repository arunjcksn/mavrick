package com.mavrick.fulfillment.user.entities;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.mavrick.persistance.entities.MavrickEntity;

@Entity
@Table(name="MVK_FULFILLMENT_USER")
public class FulfillmentUser implements MavrickEntity{
	

	@Id
	@Column(name = "CUSTOMER_ID")
	
	private String customerId;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "FISRST_NAME")
	private String firstName;
	
	@Column(name = "LAST_NAME")
	private String lastName;
	
	@Column(name = "ADDRESS_1")
	private String address1;
	
	@Column(name = "ADDRESS_2")
	private String address2;
	
	@Column(name = "CITY")
	private String city;
	
	@Column(name = "STATE_CODE")
	private String stateCode;
	
	@Column(name = "COUNTRY_CODE")
	private String countryCode;
	
	@Column(name = "MOB_PHONE_NUM")
	private String mobilePhonenumber;
	
	@Column(name = "DAY_PHONE_NUM")
	private String dayPhoneNumber;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "DELIVERY_INSTRN")
	private String deliveryInstruction;
	
	@Column(name = "LAST_MODIFIED_DATE")
	private Date lastModifiedDate;
	
	@Column(name = "ADDRESS_3")
	private String address3;
	
	@JsonIgnore
	@Column(name = "SALT")
	String salt;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getMobilePhonenumber() {
		return mobilePhonenumber;
	}

	public void setMobilePhonenumber(String mobilePhonenumber) {
		this.mobilePhonenumber = mobilePhonenumber;
	}

	public String getDayPhoneNumber() {
		return dayPhoneNumber;
	}

	public void setDayPhoneNumber(String dayPhoneNumber) {
		this.dayPhoneNumber = dayPhoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDeliveryInstruction() {
		return deliveryInstruction;
	}

	public void setDeliveryInstruction(String deliveryInstruction) {
		this.deliveryInstruction = deliveryInstruction;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date date) {
		this.lastModifiedDate = date;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setSalt(String salt) {
		// TODO Auto-generated method stub
		
	}

	public String getSalt() {
		return salt;
	}
	
	
	
	
}
