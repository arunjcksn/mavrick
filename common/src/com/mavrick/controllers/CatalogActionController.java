package com.mavrick.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mavrick.common.catalog.CatalogManager;
import com.mavrick.common.dao.BasicDAO;
import com.mavrick.common.exceptions.MavrickException;
import com.mavrick.common.exceptions.ResponseGenException;
import com.mavrick.common.json.EntityResponseGenerator;
import com.mavrick.common.json.JSONResponse;
import com.mavrick.common.json.ResponseGeneratorTools;
import com.mavrick.common.json.annotation.Groups;
import com.mavrick.common.vo.MaverickVO;
import com.mavrick.persistance.entities.Sku;

 

@Controller
@RequestMapping("/catalog")
public class CatalogActionController extends MavrickController {
	static final String JSON_RESPONSE_TYPE = "CATALOG";
	
	@Autowired
	CatalogManager catalogManager;
	
	@Autowired
	private BasicDAO basicDAO;
	@Autowired
	private EntityResponseGenerator entityResponseGenerator;
	
	
	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public @ResponseBody
	JSONResponse createUser(HttpServletRequest req,HttpServletResponse res) throws ResponseGenException {
		JSONResponse jsn=new JSONResponse(JSON_RESPONSE_TYPE);
		
		return getCatalogManager().getDefaultCatalog();
		
	}
	
	@RequestMapping(value = "/displayItem", method = RequestMethod.GET)
	public @ResponseBody
	JSONResponse getItems(HttpServletRequest req,HttpServletResponse res) {
		JSONResponse jsn=new JSONResponse(JSON_RESPONSE_TYPE);
		
		Sku sku=getCatalogManager().getSkuDetails("sku1001");
		//MavrickEntity catalog=getCatalogManager().getDefaultCatalog();
		
		try {
			List<MaverickVO> vos= entityResponseGenerator.generateResponse(sku, Groups.BASIC, "sku");
			jsn.setResponseVOs(vos);
		} catch (ResponseGenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//jsn.setEntity(sku);
		jsn.setStatusCode("200");
		jsn.setStatusMessage("View Categories API executed successfully");
		return jsn;
		
	}
	
	@RequestMapping(value = "/displayProduct", method = RequestMethod.GET)
	public @ResponseBody
	JSONResponse getProduct(HttpServletRequest req,HttpServletResponse res) throws ResponseGenException {
		String productId = req.getParameter("productId");
		return getCatalogManager().getProductDetails(productId);
		
	}
	public CatalogManager getCatalogManager() {
		return catalogManager;
	}

	public void setCatalogManager(CatalogManager catalogManager) {
		this.catalogManager = catalogManager;
	}

	public BasicDAO getBasicDAO() {
		return basicDAO;
	}

	public void setBasicDAO(BasicDAO basicDAO) {
		this.basicDAO = basicDAO;
	}
	
	@RequestMapping(value = "/flushCache", method = RequestMethod.GET)
	public @ResponseBody
	JSONResponse flushCache(HttpServletRequest req,HttpServletResponse res) throws ResponseGenException {
		String productId = req.getParameter("productId");
		return getCatalogManager().flush2ndLevelCache();
		
	}
	
	// changes for category view api starts

	@RequestMapping(value = "/categoriesView", method = RequestMethod.GET)
	public @ResponseBody
	JSONResponse categoryView(HttpServletRequest req, HttpServletResponse res,
			String categoryId) throws ResponseGenException {
		JSONResponse jsn = new JSONResponse(JSON_RESPONSE_TYPE);

		return getCatalogManager().getCategory(categoryId);

	}
	@RequestMapping(value = "/categoryProducts", method = RequestMethod.GET)
	public @ResponseBody JSONResponse getCategoryProducts(String categoryId)throws MavrickException{
	
		JSONResponse jsn = new JSONResponse(JSON_RESPONSE_TYPE);
		if(null!=categoryId&&!StringUtils.isEmpty(categoryId)){
			getCatalogManager().getProductsForCategory(categoryId, jsn);	
		}else{
			addError("Category id null or empty");
			throw new MavrickException(ACTION_FAILED, getErrors());
		}
		return jsn;
	}
	
	

	// change for category view api ends
	
	
	//changes for getMedia starts
	
	@RequestMapping(value = "/getMedia", method = RequestMethod.GET)
	public @ResponseBody
	JSONResponse getMedia(HttpServletRequest req, HttpServletResponse res,
			String mediaId) throws ResponseGenException {
		JSONResponse jsn = new JSONResponse(JSON_RESPONSE_TYPE);

		return getCatalogManager().getMedia(mediaId);

	}
	
	//changes for get media ends
	
	
	
}
