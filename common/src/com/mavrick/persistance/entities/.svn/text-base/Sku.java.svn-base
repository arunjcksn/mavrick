package com.mavrick.persistance.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.mavrick.common.json.annotation.ResponseGroup;

/**
 * This entity represents a SKU
 * 
 * @author oracle
 * 
 */

@Entity
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE,region="Sku")
@Table(name = "MVK_SKU")
public class Sku implements MavrickEntity {
	@Id
	@Column(name = "SKU_ID")
	@ResponseGroup
	 String skuId;
	
	@Column(name = "DISPLAY_NAME")
	@ResponseGroup
	private String productName;
	
	@ResponseGroup
	@Column(name = "START_DATE")
	private Date startDate;
	@ResponseGroup
	@Column(name = "END_DATE")
	private Date endDate;
	@ResponseGroup
	@Column(name="PRODUCT_INSTRUCTION")//change i to sku_desciption
	private String productDescription;
	@ResponseGroup
	@Column(name="PRODUCT_TYPE")//change to prd_type
	private String productType;
	@ResponseGroup
	@Column(name="ITEM_TYPE")//change it to sku_type
	private String itemType;
	@ResponseGroup
	@Column(name="PRD_BRAND")
	private String productBrand;
	@ResponseGroup
	@Column(name="IS_ACTIVE")	
	private String isActive;
	@ResponseGroup
	@Column(name="IS_STORE_ONLY")	
	private String isStoreOnly;
	@ResponseGroup
	@Column(name="IMAGE")	
	private String image;
	
	@Column(name="THUMBNAIL")	
	private String thumbnail;
	@ResponseGroup
	@Column(name="VIDEO")
	private String video;
	@ResponseGroup
	@Column(name="FEAUTURED_TYPE")//change it tomfeatured_prd??
	
	private String featuredProduct;
	@ResponseGroup
	@Column(name="BASE_PRICE")
	private Double basePrice;
	@ResponseGroup
	@Column(name="OFFER_PRICE")
	private Double offerPrice;
	@ResponseGroup
	@Column(name="SPECIAL_PACKAGE")
	private String specialPackaging;
	@ResponseGroup
	@Column(name="PACKAGE_DESCRIPTION")
	private String packagingDescription;
	@ResponseGroup
	@Column(name="KEYWORDS")
	private String keywords;
	@ResponseGroup
	@Column(name="SALES_UOM")
	private String salesUom;
	@ResponseGroup
	@Column(name="SALES_VALUE")
	private String salesValue;
	@ResponseGroup
	@Column(name="MAX_ORDER_QTY")
	private Integer maxOrderQty;
	@ResponseGroup
	@Column(name="ADDITIONAL_INFO")
	private String additionalInfo;
	@ResponseGroup
	@Column(name="RELATED_PRODUCTS")
	private String relatedProducts;
	@ResponseGroup
	@Column(name="OFFER_TYPE")
	private String offerType;
	
	@Column(name="LAST_MODIFIED_DATE")
	Date lastModifiedDate;
	
	
	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	

	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}



	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getProductBrand() {
		return productBrand;
	}

	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getIsStoreOnly() {
		return isStoreOnly;
	}

	public void setIsStoreOnly(String isStoreOnly) {
		this.isStoreOnly = isStoreOnly;
	}

	public String getFeaturedProduct() {
		return featuredProduct;
	}

	public void setFeaturedProduct(String featuredProduct) {
		this.featuredProduct = featuredProduct;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public double getOfferPrice() {
		return offerPrice;
	}

	public void setOfferPrice(double offerPrice) {
		this.offerPrice = offerPrice;
	}

	public String getSpecialPackaging() {
		return specialPackaging;
	}

	public void setSpecialPackaging(String specialPackaging) {
		this.specialPackaging = specialPackaging;
	}

	public String getPackagingDescription() {
		return packagingDescription;
	}

	public void setPackagingDescription(String packagingDescription) {
		this.packagingDescription = packagingDescription;
	}

	public String getSalesUom() {
		return salesUom;
	}

	public void setSalesUom(String salesUom) {
		this.salesUom = salesUom;
	}

	public String getSalesValue() {
		return salesValue;
	}

	public void setSalesValue(String salesValue) {
		this.salesValue = salesValue;
	}

	public int getMaxOrderQty() {
		return maxOrderQty;
	}

	public void setMaxOrderQty(int maxOrderQty) {
		this.maxOrderQty = maxOrderQty;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public String getRelatedProducts() {
		return relatedProducts;
	}

	public void setRelatedProducts(String relatedProducts) {
		this.relatedProducts = relatedProducts;
	}

	public String getOfferType() {
		return offerType;
	}

	public void setOfferType(String offerType) {
		this.offerType = offerType;
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

	//Gurucharan created date and updated change for sku starts
	
	@ResponseGroup
	@Column(name = "CREATED_DATE_TIME")
	private Date createDate;
	
	@ResponseGroup
	@Column(name = "UPDATED_DATE_TIME")
	private Date updateDate;


	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public void setBasePrice(Double basePrice) {
		this.basePrice = basePrice;
	}

	public void setOfferPrice(Double offerPrice) {
		this.offerPrice = offerPrice;
	}

	public void setMaxOrderQty(Integer maxOrderQty) {
		this.maxOrderQty = maxOrderQty;
	}
	
	//Gurucharan created date and updated change for sku ends
}
