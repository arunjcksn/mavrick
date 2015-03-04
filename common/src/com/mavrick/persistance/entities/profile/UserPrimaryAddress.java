package com.mavrick.persistance.entities.profile;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.mavrick.persistance.entities.MavrickEntity;
import com.mavrick.persistance.entities.User;

/**
 * This VO represent the attributes of a user. TODO -->ID generation needs to be
 * defined..
 * 
 * @author oracle
 * 
 */
@Entity
@Table(name = "MVK_USER_PRM_ADDR", uniqueConstraints = { @UniqueConstraint(columnNames = "ID") })
public class UserPrimaryAddress implements MavrickEntity {
	@Id
	@Column(name = "ID")
	@GeneratedValue(generator="gen")
    @GenericGenerator(name="gen", strategy="foreign", parameters=@Parameter(name="property", value="user"))
	int id;
	
	@Column(name = "SHIP_ADDR_ID")
	int shipAddrId;
	
	@Column(name = "BILL_ADDR_ID")
	int billAddrId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getShipAddrId() {
		return shipAddrId;
	}

	public void setShipAddrId(int shipAddrId) {
		this.shipAddrId = shipAddrId;
	}

	public int getBillAddrId() {
		return billAddrId;
	}

	public void setBillAddrId(int billAddrId) {
		this.billAddrId = billAddrId;
	}
	
	@JsonIgnore
	@OneToOne
	@PrimaryKeyJoinColumn
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}