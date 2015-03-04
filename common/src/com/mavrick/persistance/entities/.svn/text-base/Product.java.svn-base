package com.mavrick.persistance.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * This entity represents a product.
 * @author oracle
 *
 */
@Entity
@Table(name = "MVK_PRODUCT")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE,region="Product")
public class Product implements MavrickEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="PRODUCT_ID")
     String productId;
	
	@Column(name="PRD_DISPLAY_NAME")
	 String productDescription;
	
	@Column(name="LAST_MODIFIED_DATE")
	Date lastModifiedDate;
	   
    @OneToMany
    @JoinTable(
    		name="MVK_PRD_SKU",
    		joinColumns={@JoinColumn(name="PRODUCT_ID", referencedColumnName="PRODUCT_ID")},
    		inverseJoinColumns={@JoinColumn(name="SKU_ID", referencedColumnName="SKU_ID")}
    		)
	private List<Sku> skus;
 
    
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public List<Sku> getSkus() {
		return skus;
	}

	public void setSkus(List<Sku> skus) {
		this.skus = skus;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}


    
    
	
}
