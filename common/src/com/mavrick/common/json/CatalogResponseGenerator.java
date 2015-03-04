/**
 * 
 */
package com.mavrick.common.json;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.mavrick.common.constants.JSONConstants;
import com.mavrick.common.exceptions.ResponseGenException;
import com.mavrick.common.vo.CatalogVO;
import com.mavrick.common.vo.CategoryVO;
import com.mavrick.common.vo.MaverickVO;
import com.mavrick.common.vo.ProductVO;
import com.mavrick.common.vo.SkuVO;
import com.mavrick.persistance.entities.Catalog;
import com.mavrick.persistance.entities.Category;
import com.mavrick.persistance.entities.MavrickEntity;
import com.mavrick.persistance.entities.Product;
import com.mavrick.persistance.entities.Sku;

/**
 * @author vrajeev
 *
 */
public class CatalogResponseGenerator implements ResponseGenerator {

	@Override
	public JSONResponse generateResponse(MavrickEntity entity,String strategy) throws ResponseGenException {

		JSONResponse jsonResponse = null;
		/*if (JSONConstants.COMMON_STRATEGY_DEFLT_CATLG.equals(strategy) && entity instanceof Catalog) {
			Catalog cat = (Catalog) entity;
		    jsonResponse = new JSONResponse();
		    List<MaverickVO>catalogVOs = new ArrayList<MaverickVO>();
			CatalogVO catalogVO = new CatalogVO();
			catalogVO.setCatalogId(cat.getCatalogId());
			catalogVO.setCatalogName(cat.getCatalogName());
			catalogVO.setCreationDate(cat.getCreationDate());
			catalogVO.setEndDate(cat.getEndDate());
			catalogVO.setStartDate(cat.getStartDate());
			
			
			
			for (Category category : cat.getAllrootCategories()) {
				CategoryVO categoryVO = new CategoryVO();
				categoryVO.setCategoryId(category.getCategoryId());
				categoryVO.setDisplayName(category.getDisplayName());
				*//**categoryVO.setEnabled(category.getEnabled());
				categoryVO.setEndDate(category.getEndDate());
				categoryVO.setStartDate(category.getStartDate());*//*
				
				if (catalogVO.getCategoryVOs() != null){
					catalogVO.getCategoryVOs().add(categoryVO);
				} else {
					List<CategoryVO> categoryVOs =new ArrayList<CategoryVO>();
					categoryVOs.add(categoryVO);
					catalogVO.setCategoryVOs(categoryVOs);
				}
				
			}
			catalogVOs.add(catalogVO);
			jsonResponse.setResponseVOs(catalogVOs);
			jsonResponse.setStatusCode("200");
			jsonResponse.setStatusMessage("View Categories API executed successfully");		
			
		} else if (JSONConstants.COMMON_STRATEGY_GET_PRODUCT.equals(strategy) && entity instanceof Product){
			Product product = (Product)entity;
		    jsonResponse = new JSONResponse();
		    List<MaverickVO>productVOs = new ArrayList<MaverickVO>();
		    ProductVO productVO = new ProductVO();
		    productVO.setProductId(product.getProductId());
		    for (Sku sku : product.getSkus()) {
		    	SkuVO skuVO = new SkuVO();
		    	//skuVO.setDisplayName(sku.getProductName());
		    	skuVO.setSkuId(sku.getSkuId());
		    	//skuVO.setStatus(sku.getAvailStatus());
		    	skuVO.setAdditionalInfo(sku.getAdditionalInfo());
		    	skuVO.setBasePrice(sku.getBasePrice());
		    	skuVO.setFeaturedProduct(sku.getFeaturedProduct());
		    	skuVO.setImage(sku.getImage());
		    	skuVO.setIsActive(sku.getIsActive());
		    	skuVO.setIsStoreOnly(sku.getIsStoreOnly());
		    	skuVO.setItemType(sku.getItemType());
		    	skuVO.setKeywords(sku.getKeywords());
		    	skuVO.setMaxOrderQty(sku.getMaxOrderQty());
		    	skuVO.setOfferPrice(sku.getOfferPrice());
		    	skuVO.setPackagingDescription(sku.getPackagingDescription());
		    	skuVO.setProductBrand(sku.getProductBrand());
		    	skuVO.setProductDescription(sku.getProductDescription());
		    	skuVO.setProductType(sku.getProductType());
		    	skuVO.setRelatedProducts(sku.getRelatedProducts());
		    	skuVO.setSalesUom(sku.getSalesUom());
		    	skuVO.setSalesValue(sku.getSalesValue());
		    	skuVO.setSkuId(sku.getSkuId());
		    	skuVO.setSpecialPackaging(sku.getSpecialPackaging());
		    	skuVO.setThumbnail(sku.getThumbnail());
		    	skuVO.setVideo(sku.getVideo());
		    	
		    	
		    	
		    	if (productVO.getSkus() != null && productVO.getSkus().size() > 0) {
		    		productVO.getSkus().add(skuVO);
		    	}else {
					List<SkuVO> SkuVOs =new ArrayList<SkuVO>();
					SkuVOs.add(skuVO);
					productVO.setSkus(SkuVOs);

		    	}
		    }

		    productVOs.add(productVO);
		    jsonResponse.setResponseVOs(productVOs);
			jsonResponse.setStatusCode("200");
			jsonResponse.setStatusMessage("View Categories API executed successfully");		
			jsonResponse.setEntityName("Product");
		} 
		//changes for view categories api starts
				else if(JSONConstants.COMMON_STRATEGY_DEFLT_CATLG.equals(strategy) && entity instanceof Category){
					Category cat = (Category) entity;
				    jsonResponse = new JSONResponse();
				   
					CategoryVO categoryVos=buildCategoryVO(cat);
					//categoryVO.setParentCategory(cat.getParentCategory());
					
					
					List<CategoryVO>cvos=new ArrayList<CategoryVO>();
					
					for (Category category : cat.getChildCategories()) {
						CategoryVO categoryVO1 = new CategoryVO();
						categoryVO1.setCategoryId(category.getCategoryId());
						categoryVO1.setDisplayName(category.getDisplayName());
						*//**
						 * categoryVO1.setEnabled(category.getEnabled());
						categoryVO1.setEndDate(category.getEndDate());
						categoryVO1.setStartDate(category.getStartDate());*//*
						if (categoryVO1.getChildCategories() != null){
							categoryVO1.getChildCategories().add(categoryVO1);
						} else {
							Set<CategoryVO> categoryVOs =new HashSet<CategoryVO>();
							categoryVOs.add(categoryVO1);
							categoryVO1.setChildCategories(categoryVOs);
						}
						
					}
					//categoryVOs.add(categoryVO1);
					//jsonResponse.setResponseVOs(cvos);
					jsonResponse.setStatusCode("200");
					jsonResponse.setStatusMessage("View Categories API executed successfully");	
				}
				//changes for view categories api ends
		
		else {
			
		}
		*/
		
		return jsonResponse;
	}

	private CategoryVO buildCategoryVO(Category parentCategory) {
		 	List<MaverickVO>categoryVos = new ArrayList<MaverickVO>();
		    CategoryVO categoryVO = new CategoryVO();
			categoryVO.setCategoryId(parentCategory.getCategoryId());
			categoryVO.setDisplayName(parentCategory.getDisplayName());
			/*categoryVO.setEnabled(cat.getEnabled());
			categoryVO.setEndDate(cat.getEndDate());
			categoryVO.setStartDate(cat.getStartDate());
			*/
			categoryVO.setCategoryDescription(parentCategory.getCategoryDescription());
			categoryVO.setCategoryTitle(parentCategory.getCategoryTitle());
			categoryVO.setCategoryType(parentCategory.getCategoryType());
			categoryVO.setCreatedDate(parentCategory.getCreatedDate());
			categoryVO.setDisplayRank(parentCategory.getDisplayRank());
			categoryVO.setUpdatedDate(parentCategory.getUpdatedDate());
			/*
			if(parentCategory.getParentCategory()!=null){
				//CategoryVO categoryVo2=buildCategoryVO(parentCategory.getParentCategory());
				//categoryVO.setParentCategory(categoryVo2);
			}*/
		return categoryVO;
	}

}
