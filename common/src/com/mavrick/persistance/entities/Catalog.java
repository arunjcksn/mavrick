package com.mavrick.persistance.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.mavrick.common.json.annotation.Groups;
import com.mavrick.common.json.annotation.ResponseGroup;

/**
 * This entity represents the product catalog.
 * @author oracle
 *
 */

@Entity
@Table(name="MVK_CATALOG")
public class Catalog implements MavrickEntity {

	@Id
	@Column(name = "CATALOG_ID")
	String catalogId;

	@Column(name = "CATALOG_NAME")
	String catalogName;

	@Column(name = "CREATION_DATE")
	Date creationDate;

	@Column(name = "ENABLED")
	Boolean enabled;

	@Column(name = "START_DATE")
	Date startDate;

	@Column(name = "END_DATE")
	Date endDate;
	
	@Column(name="LAST_MODIFIED_DATE")
	Date lastModifiedDate;
	
	//Need to find a better approach 
	@ResponseGroup(fieldType=Groups.EXTENED)
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinTable(name = "MVK_CATALOG_ROOT_CATS", joinColumns = { @JoinColumn(name = "CATALOG_ID") }, inverseJoinColumns = { @JoinColumn(name = "CATEGORY_ID") })
	Set<Category> allrootCategories = new HashSet<Category>();

	public String getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}

	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Set<Category> getAllrootCategories() {
		return allrootCategories;
	}

	public void setAllrootCategories(Set<Category> allrootCategories) {
		this.allrootCategories = allrootCategories;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

}
