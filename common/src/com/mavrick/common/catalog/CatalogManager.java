package com.mavrick.common.catalog;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.mavrick.common.constants.JSONConstants;
import com.mavrick.common.dao.BasicDAO;
import com.mavrick.common.exceptions.MavrickException;
import com.mavrick.common.exceptions.ResponseGenException;
import com.mavrick.common.json.EntityResponseGenerator;
import com.mavrick.common.json.JSONResponse;
import com.mavrick.common.json.ResponseGenerator;
import com.mavrick.common.json.annotation.Groups;
import com.mavrick.common.vo.MaverickVO;
import com.mavrick.common.vo.MediaVO;
import com.mavrick.common.vo.SkuVO;
import com.mavrick.persistance.entities.Catalog;
import com.mavrick.persistance.entities.Category;
import com.mavrick.persistance.entities.MavrickEntity;
import com.mavrick.persistance.entities.Media;
import com.mavrick.persistance.entities.Product;
import com.mavrick.persistance.entities.Sku;

public class CatalogManager {

	private static final String JSON_RESPONSE_TYPE = "CATALOG";

	BasicDAO basicDAO;

	String catalogClassName;

	String defaultCatalogId;

	String offersForDayQuery;
	
	String productsQuery;
	
	@Autowired
	private SessionFactory sessionFactory;
	

	@Transactional
	public JSONResponse getDefaultCatalog() throws ResponseGenException {
		Catalog cat = new Catalog();
		MavrickEntity catalog = basicDAO.getEntityById(getDefaultCatalogId(),
				cat);
		ResponseGenerator catalogResponseGenerator = null;// ResponseGenerationFactory.findGenerator(JSONConstants.CATALOG_RESPONSE_GEN);

		return catalogResponseGenerator.generateResponse(catalog,
				JSONConstants.COMMON_STRATEGY_DEFLT_CATLG);
	}

	public Sku getSkuDetails(int skuId) {

		Sku sku = null;
		List<MavrickEntity> entity = getBasicDAO().runQuery(
				"from Sku where skuId='" + skuId + "'");
		if (null != entity && !entity.isEmpty()) {
			sku = (Sku) entity.get(0);
		}
		return sku;
	}

	public Sku getSkuDetails(String skuId) {

		Sku sku = null;
		List<MavrickEntity> entity = getBasicDAO().runQuery(
				"from Sku where skuId='" + skuId + "'");
		if (null != entity && !entity.isEmpty()) {
			sku = (Sku) entity.get(0);
		}
		return sku;
	}

	@Transactional
	public JSONResponse getProductDetails(String productId)
			throws ResponseGenException {
		Product product = new Product();
		MavrickEntity mavrickEntity = basicDAO
				.getEntityById(productId, product);
		ResponseGenerator catalogResponseGenerator = null;// ResponseGenerationFactory.findGenerator(JSONConstants.CATALOG_RESPONSE_GEN);

		return catalogResponseGenerator.generateResponse(mavrickEntity,
				JSONConstants.COMMON_STRATEGY_GET_PRODUCT);
	}

	@Transactional
	public JSONResponse flush2ndLevelCache() throws ResponseGenException {
		JSONResponse jsonResponse = new JSONResponse();
		basicDAO.flush2ndLevelCache();
		jsonResponse.setStatusMessage("Flushed cache");
		return jsonResponse;
	}

	BasicDAO getBasicDAO() {
		return basicDAO;
	}

	public void setBasicDAO(BasicDAO basicDAO) {
		this.basicDAO = basicDAO;
	}

	public String getCatalogClassName() {
		return catalogClassName;
	}

	public void setCatalogClassName(String catalogClassName) {
		this.catalogClassName = catalogClassName;
	}

	public String getDefaultCatalogId() {
		return defaultCatalogId;
	}

	public void setDefaultCatalogId(String defaultCatalogId) {
		this.defaultCatalogId = defaultCatalogId;
	}

	// change for view categories api starts
	/**
	 * 
	 * @param catId
	 * @param jsn
	 * @throws MavrickException
	 */
	public void getProductsForCategory(String catId, JSONResponse jsn)
			throws MavrickException {
		List<String> params = new ArrayList<String>();
		if (null != catId) {
			params.add(catId);
			List<MaverickVO> skus = basicDAO.runNativeQuery(productsQuery, params,Sku.class);
			if (skus != null) {
				jsn.getResponsePayload().addAll(skus);
			}
		}
	}

	/**
	 * 
	 * @return
	 * @throws ResponseGenException
	 */

	public JSONResponse getCategory(String categoryId)
			throws ResponseGenException {

		JSONResponse jsn = new JSONResponse(JSON_RESPONSE_TYPE);
		List<MaverickVO> vos = new ArrayList<MaverickVO>();

		if (categoryId == null) {
			Catalog cat = new Catalog();
			List<String> catalogExendedProperties = getEntityResponseGenerator()
					.getResponseGenTools().getExtendedProperties(cat);
			MavrickEntity catalog = basicDAO.getEntityById(
					getDefaultCatalogId(), cat, catalogExendedProperties, true);

			vos = getEntityResponseGenerator().generateResponse(catalog,
					Groups.EXTENED, JSONConstants.CATALOG);

		} else {
			Category cat = new Category();
			List<String> catExendedProperties = getEntityResponseGenerator()
					.getResponseGenTools().getExtendedProperties(cat);

			MavrickEntity category = basicDAO.getEntityById(categoryId, cat,
					catExendedProperties, true);

			/*
			 * ResponseGenerator catalogResponseGenerator =
			 * ResponseGenerationFactory
			 * .findGenerator(JSONConstants.CATALOG_RESPONSE_GEN);
			 * 
			 * return catalogResponseGenerator.generateResponse(category,
			 * JSONConstants.COMMON_STRATEGY_DEFLT_CATLG);
			 */

			vos = getEntityResponseGenerator().generateResponse(category,
					Groups.EXTENED, JSONConstants.CATEGORY);

		}
		jsn.setResponseVOs(vos);
		return jsn;
	}

	// change for view categories api ends

	// changes for get media starts
	public JSONResponse getMedia(String mediaId) {

		MavrickEntity enitityReturnedFromDb = basicDAO.getEntityById(mediaId,
				new Media());
		JSONResponse jsonResponse = new JSONResponse();
		List<MaverickVO> responseVOs = new ArrayList<MaverickVO>();

		if (enitityReturnedFromDb != null) {
			Media mEntity = (Media) enitityReturnedFromDb;

			MediaVO mediaVO = new MediaVO();

			mediaVO.setMediaId(mEntity.getMediaId());
			mediaVO.setCreateDate(mEntity.getCreateDate());
			mediaVO.setMediaType(mEntity.getMediaType());
			mediaVO.setParentId(mEntity.getParentId());
			mediaVO.setSequenceNum(mEntity.getSequenceNum());
			mediaVO.setUpdatedDate(mEntity.getUpdatedDate());
			mediaVO.setMediaContent(mEntity.getMediaContent());

			responseVOs.add(mediaVO);
			jsonResponse.setResponseVOs(responseVOs);

			jsonResponse.setStatusCode("200");
			jsonResponse.setStatusMessage("Get Media api executed succesfully");

		} else {

			jsonResponse.setStatusCode("500");
			jsonResponse.setStatusMessage("Get Media api executed with error");
		}

		return jsonResponse;
	}

	public JSONResponse getOffersForDay(String mediaId) {

		JSONResponse jsonResponse = new JSONResponse();
		List<MaverickVO> responseVOs = new ArrayList<MaverickVO>();

		List<MavrickEntity> enititiesReturnedFromDb = basicDAO
				.runQuery(getOffersForDayQuery());

		if (enititiesReturnedFromDb != null
				&& !enititiesReturnedFromDb.isEmpty()) {

			for (MavrickEntity mEntity : enititiesReturnedFromDb) {
				SkuVO skuVO = new SkuVO();
				Sku sku = (Sku) mEntity;

				skuVO.setSkuId(sku.getSkuId());

				responseVOs.add(skuVO);

			}

			jsonResponse.setResponseVOs(responseVOs);

			jsonResponse.setStatusCode("200");
			jsonResponse.setStatusMessage("Get Media api executed succesfully");

		} else {

			jsonResponse.setStatusCode("500");
			jsonResponse.setStatusMessage("Get Media api executed with error");
		}

		return jsonResponse;
	}

	public String getOffersForDayQuery() {
		return offersForDayQuery;
	}

	public void setOffersForDayQuery(String offersForDayQuery) {
		this.offersForDayQuery = offersForDayQuery;
	}

	// changes for get media ends

	// changes for lazy loading starts guru

	@Autowired
	EntityResponseGenerator entityResponseGenerator;

	public EntityResponseGenerator getEntityResponseGenerator() {
		return entityResponseGenerator;
	}

	public void setEntityResponseGenerator(
			EntityResponseGenerator entityResponseGenerator) {
		this.entityResponseGenerator = entityResponseGenerator;
	}
	// changes for lazy loading ends guru

	public String getProductsQuery() {
		return productsQuery;
	}

	public void setProductsQuery(String productsQuery) {
		this.productsQuery = productsQuery;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
