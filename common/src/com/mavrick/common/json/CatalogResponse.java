package com.mavrick.common.json;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.mavrick.persistance.entities.Category;

public class CatalogResponse extends JSONResponse {

	String catalogId;

	String catalogName;

	Date creationDate;

	Boolean enabled;

	Date startDate;

	Date endDate;

	Set<Category> allrootCategories = new HashSet<Category>();
	
	
	public CatalogResponse(String name) {
		super(name);
	}



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

	
	
}
