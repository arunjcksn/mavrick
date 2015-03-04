package com.mavrick.persistance.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import com.mavrick.common.json.annotation.Groups;
import com.mavrick.common.json.annotation.ResponseGroup;

/**
 * This entity represents a category.
 * 
 * @author oracle
 * 
 */

@Entity
@Table(name = "MVK_CATEGORY")
public class Category implements MavrickEntity {

	@Id
	@Column(name = "CAT_ID")
	String categoryId;

	@Column(name = "CAT_TITLE")
	String categoryTitle;

	@Column(name = "CAT_DESCRIPTION")
	String categoryDescription;

	@Column(name = "CAT_TYPE")
	String categoryType;


	@ResponseGroup(fieldType=Groups.EXTENED)
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinTable(name = "MVK_CATEGORY_CHLD_CATS", joinColumns = { @JoinColumn(name = "CAT_ID") }, inverseJoinColumns = { @JoinColumn(name = "CHILD_CAT_ID") })
	Set<Category> childCategories = new HashSet<Category>();
	
	@ResponseGroup(fieldType=Groups.EXTENED)
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinTable(name = "MVK_CAT_PRDS", joinColumns = { @JoinColumn(name = "CAT_ID") }, inverseJoinColumns = { @JoinColumn(name = "PRD_ID") })
	Set<Product> products= new HashSet<Product>(); 
	
	

	@Column(name = "DISPLAY_NAME")
	String displayName;

	@Column(name = "DISPLAY_RANK")
	int displayRank;

	@Column(name = "CREATED_DATE_TIME")
	Date createdDate;

	@Column(name = "UPDATED_DATE_TIME")
	Date updatedDate;
	

	@Column(name="LAST_MODIFIED_DATE")
	Date lastModifiedDate;

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryTitle() {
		return categoryTitle;
	}

	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	 

	public Set<Category> getChildCategories() {
		return childCategories;
	}

	public void setChildCategories(Set<Category> childCategories) {
		this.childCategories = childCategories;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public int getDisplayRank() {
		return displayRank;
	}

	public void setDisplayRank(int displayRank) {
		this.displayRank = displayRank;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

}
