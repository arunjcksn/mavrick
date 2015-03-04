package com.mavrick.common.vo;

import java.util.Collection;
import java.util.Date;
import java.util.List;


public class ProductVO implements MaverickVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String productId;
	
	private String productDescription;
	
	Date lastModifiedDate;
	
	private Collection<SkuVO> skus;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
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

	public Collection<SkuVO> getSkus() {
		return skus;
	}

	public void setSkus(Collection<SkuVO> skus) {
		this.skus = skus;
	}




	
	
	
	
}
