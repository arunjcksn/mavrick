package com.mavrick.persistance.entities.order;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.mavrick.persistance.entities.MavrickEntity;

@Entity
@Table(name = "MVK_SHIP_ADDR")
public class OrderShipAddress implements MavrickEntity {
	@Id
	@Column(name = "SHIP_ADDR_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int shipAddrId;
	
	@Column(name = "ORDER_ID")
	private int orderId;
	
	@Column(name = "FIRST_NAME")
	private String firstName;
	
	@Column(name = "LAST_NAME")
	private String lastName;
	
	@Column(name = "EMAIL")
	private String email;

	@Column(name = "ADDRESS_1")
	private String addressOne;
	
	@Column(name = "ADDRESS_2")
	private String addressTwo;
	@Column(name = "AREA")
	private String area;
	@Column(name = "POSTCODE")
	private String postcode;
	@Column(name = "PHONE_NUM")
	private String phoneNum;
	@Column(name = "MOBILE_PHONE_NUM")
	private String mobilePhoneNum;
	@Column(name = "ALT_PHONE_NUM")
	private String altPhoneNum;
	public int getShipAddrId() {
		return shipAddrId;
	}
	public void setShipAddrId(int shipAddrId) {
		this.shipAddrId = shipAddrId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddressOne() {
		return addressOne;
	}
	public void setAddressOne(String addressOne) {
		this.addressOne = addressOne;
	}
	public String getAddressTwo() {
		return addressTwo;
	}
	public void setAddressTwo(String addressTwo) {
		this.addressTwo = addressTwo;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getMobilePhoneNum() {
		return mobilePhoneNum;
	}
	public void setMobilePhoneNum(String mobilePhoneNum) {
		this.mobilePhoneNum = mobilePhoneNum;
	}
	public String getAltPhoneNum() {
		return altPhoneNum;
	}
	public void setAltPhoneNum(String altPhoneNum) {
		this.altPhoneNum = altPhoneNum;
	}
}
