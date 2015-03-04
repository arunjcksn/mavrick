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
public class CatalogVO  implements MaverickVO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String catalogId;

	String catalogName;

	Date creationDate;

	Boolean enabled;

	Date startDate;

	Date endDate;
	
	Date lastModifiedDate;
	
	Collection<CategoryVO> allrootCategories ;

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

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Collection<CategoryVO> getAllrootCategories() {
		return allrootCategories;
	}

	public void setAllrootCategories(Collection<CategoryVO> allrootCategories) {
		this.allrootCategories = allrootCategories;
	}

	
	

}
