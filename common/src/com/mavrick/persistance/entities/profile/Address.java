package com.mavrick.persistance.entities.profile;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

import com.mavrick.persistance.entities.MavrickEntity;

/**
 * This VO represent the attributes of a user. TODO -->ID generation needs to be
 * defined..
 * 
 * @author oracle
 * 
 */
@Entity
@Table(name = "MVK_USER_ADDR", uniqueConstraints = { @UniqueConstraint(columnNames = "ADDR_ID") })
public class Address implements MavrickEntity {
	
	@TableGenerator(name="USER_GENERATOR",
		    table="GENERATED_KEYS",
		    pkColumnName="PK_COLUMN",
		    valueColumnName="VALUE_COLUMN",
		    pkColumnValue="ADDR_ID",
		    allocationSize=100
			)
			@Id
			@GeneratedValue(strategy = GenerationType.TABLE, generator="USER_GENERATOR")
	

	@Column(name = "ADDR_ID")
	
	int addressId;

	//represent the profile id
	@Column(name = "PROFILE_ID")
	int profileId;

	@Column(name = "NICK_NAME")
	String nickName;

	@Column(name = "ADDRESS1")
	String address1;

	@Column(name = "address2")
	String address2;

	@Column(name = "AREA")
	String area;

	@Column(name = "CITY")
	String city;

	@Column(name = "POSTCODE")
	String postcode;

	@Column(name = "EMAIL")
	String email;

	@Column(name = "PHONE_NUM")
	private String phoneNum;
	@Column(name = "MOBILE_PHONE_NUM")
	private String mobilePhoneNum;
	@Column(name = "ALT_PHONE_NUM")
	private String altPhoneNum;

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int userId) {
		this.profileId = userId;
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

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getAltPhoneNum() {
		return altPhoneNum;
	}

	public void setAltPhoneNum(String altPhoneNum) {
		this.altPhoneNum = altPhoneNum;
	}

}
