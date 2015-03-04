/**
 * 
 */
package com.mavrick.common.vo;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

/**
 * @author vrajeev
 *
 */
public class CategoryVO implements MaverickVO {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String categoryId;
	
	String categoryTitle;
	
	String categoryDescription;
	
	String categoryType;
	
	String displayName;
	
	int displayRank;
	
	Date createdDate;
	
	Date updatedDate;
	
	//change for view categories api starts
	
	
	Collection<CategoryVO> childCategories;
	
	Collection<ProductVO> products;

	Date lastModifiedDate;
	//change for viewcategories api ends

	public Collection<CategoryVO> getChildCategories() {
		return childCategories;
	}

	public void setChildCategories(Collection<CategoryVO> childCategories) {
		this.childCategories = childCategories;
	}

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

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public Collection<ProductVO> getProducts() {
		return products;
	}

	public void setProducts(Collection<ProductVO> products) {
		this.products = products;
	}
	
	
}
