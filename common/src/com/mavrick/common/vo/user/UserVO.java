package com.mavrick.common.vo.user;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotEmpty;

import com.mavrick.common.vo.MaverickVO;
import com.mavrick.persistance.entities.profile.Address;
import com.mavrick.persistance.entities.profile.UserPrimaryAddress;

public class UserVO implements MaverickVO{
	
	long id;
	
	String firstName;
	
	String lastName;
	
	String password;
	
	String email;
	
	Date lastLoggedInDate;
	
	Date lastEmailedDate;
	
	Date createdDate;
	
	
	List<MaverickVO> address;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getLastLoggedInDate() {
		return lastLoggedInDate;
	}
	public void setLastLoggedInDate(Date lastLoggedInDate) {
		this.lastLoggedInDate = lastLoggedInDate;
	}
	public Date getLastEmailedDate() {
		return lastEmailedDate;
	}
	public void setLastEmailedDate(Date lastEmailedDate) {
		this.lastEmailedDate = lastEmailedDate;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public List<MaverickVO> getAddress() {
		return address;
	}
	public void setAddress(List<MaverickVO> address) {
		this.address = address;
	}
	
	//change for order history starts
	private List orderVos;
	
	public List getOrderVos() {
		return orderVos;
	}
	public void setOrderVos(List orderVos) {
		this.orderVos = orderVos;
	}
	//change for order history ends
	
}
