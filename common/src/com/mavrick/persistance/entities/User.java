package com.mavrick.persistance.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

import com.mavrick.common.json.annotation.Groups;
import com.mavrick.common.json.annotation.ResponseGroup;
import com.mavrick.common.vo.MaverickVO;
import com.mavrick.persistance.entities.profile.Address;
import com.mavrick.persistance.entities.profile.UserPrimaryAddress;

/**
 * This VO represent the attributes of a user. TODO -->ID generation needs to be
 * defined..
 * 
 * @author oracle
 * 
 */
@Entity
@Table(name = "MVK_USER", uniqueConstraints = {
		@UniqueConstraint(columnNames = "ID"),
		@UniqueConstraint(columnNames = "EMAIL") })
public class User implements MavrickEntity,MaverickVO {
		@TableGenerator(name="USER_GENERATOR",
			    table="GENERATED_KEYS",
			    pkColumnName="PK_COLUMN",
			    valueColumnName="VALUE_COLUMN",
			    pkColumnValue="USER_ID",
			    allocationSize=100
				)
		@Id
		@GeneratedValue(strategy = GenerationType.TABLE, generator="USER_GENERATOR")
		@Column(name="ID")
	int id;
	@NotEmpty
	@Column(name = "FIRST_NAME")
	String firstName;
	@NotEmpty
	@Column(name = "LAST_NAME")
	String lastName;

	@NotEmpty
	@JsonIgnore
	@Column(name = "PASSWORD", nullable = false)
	String password;

	@NotEmpty
	@Column(name = "EMAIL")
	String email;

	@Column(name = "LAST_LOGGED_IN_DATE")
	Date lastLoggedInDate;

	@Column(name = "LAST_EMAILED_DATE")
	Date lastEmailedDate;

	@Column(name = "CREATED_DATE")
	Date createdDate;

	@JsonIgnore
	@Column(name = "SALT")
	String salt;

	@JsonIgnore
	@Transient
	String confirmPassword;
	@ResponseGroup(fieldType=Groups.EXTENED)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "profileId")
	List<Address> address;
	
	
	@ResponseGroup(fieldType=Groups.EXTENED)
	@OneToOne(fetch = FetchType.LAZY,mappedBy="user",cascade = CascadeType.ALL)
	private UserPrimaryAddress primaryAddress;

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public UserPrimaryAddress getPrimaryAddress() {
		return primaryAddress;
	}

	public void setPrimaryAddress(UserPrimaryAddress primaryAddress) {
		this.primaryAddress = primaryAddress;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
