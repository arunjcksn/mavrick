package com.mavrick.fulfillment.order.manager;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.mavrick.common.auth.BasicAuthenticationManager;
import com.mavrick.common.dao.BasicDAO;
import com.mavrick.common.exceptions.MavrickException;
import com.mavrick.common.exceptions.UserAuthenticationException;
import com.mavrick.common.json.JSONResponse;
import com.mavrick.common.session.Cart;
import com.mavrick.common.util.MavrickTools;
import com.mavrick.common.vo.MaverickVO;
import com.mavrick.common.vo.order.CommerceItemVO;
import com.mavrick.common.vo.order.OrderVO;
import com.mavrick.common.vo.order.PriceInfoVO;
import com.mavrick.fulfillment.order.entities.FulfillmentLoad;
import com.mavrick.fulfillment.order.entities.FulfillmentOrder;
import com.mavrick.fulfillment.order.entities.FulfillmentOrderAdjustment;
import com.mavrick.fulfillment.order.entities.FulfillmentOrderLine;
import com.mavrick.fulfillment.order.entities.FulfillmentPickOrderLine;
import com.mavrick.fulfillment.order.entities.FulfillmentSubstitutionLine;
import com.mavrick.fulfillment.order.vo.FulfillmentLoadVO;
import com.mavrick.fulfillment.order.vo.FulfillmentOrderAdjustmentVO;
import com.mavrick.fulfillment.order.vo.FulfillmentOrderLineVO;
import com.mavrick.fulfillment.order.vo.FulfillmentOrderVO;
import com.mavrick.fulfillment.order.vo.FulfillmentSubstitutionLineVO;
import com.mavrick.fulfillment.user.entities.FulfillmentUser;
import com.mavrick.persistance.entities.MavrickEntity;
import com.mavrick.persistance.entities.Sku;
import com.mavrick.persistance.entities.User;
import com.mavrick.persistance.entities.order.CommerceItem;
import com.mavrick.persistance.entities.order.Order;
import com.mavrick.persistance.entities.order.PriceInfo;


public class FulfillmentOrderManager {
	
	@Autowired
	BasicDAO basicDAO;
	
	protected Logger logger = Logger.getLogger(getClass());
	
	static final String JSON_RESPONSE_TYPE = "USER";
	
	
	private MavrickTools mvkTools;

	private String profileQuery;
	
	private String loadQuery;
	
	private String orderQuery;
	
	
	@Autowired
	private BasicAuthenticationManager fulfillmentAuthenticationManager;
	
	@Transactional
	public void updateOrderForPickedItem(String orderId,String productId, int qty) {
		FulfillmentPickOrderLine fulfillmentItemToUpdate = null;
		FulfillmentOrder order = (FulfillmentOrder) basicDAO.getEntityById(orderId,new FulfillmentOrder());
		
		List<FulfillmentPickOrderLine> pickLines = order.getFulfillmentPickLines();
		if (pickLines == null || pickLines.isEmpty()) {
			pickLines = new ArrayList<FulfillmentPickOrderLine>();
		} else {
			for (FulfillmentPickOrderLine fulfillmentPickOrderLine : pickLines) {
				if (fulfillmentPickOrderLine != null && fulfillmentPickOrderLine.getProductId() == productId) {
					fulfillmentItemToUpdate = fulfillmentPickOrderLine;
				}
			}
		}
		// logger.info("commerceitemsuid -- > "+commerceItemToUpdate.getSkuId());
		if (fulfillmentItemToUpdate == null) {

			
			fulfillmentItemToUpdate = new FulfillmentPickOrderLine();
			fulfillmentItemToUpdate.setProductId(productId);
			fulfillmentItemToUpdate.setPickQuantity(qty);
			fulfillmentItemToUpdate.setOrderId(orderId);
			fulfillmentItemToUpdate.setPickTimeStamp(new Date());
			fulfillmentItemToUpdate.setToteNumber(1234);
			fulfillmentItemToUpdate.setUpcCode("UPC CODE FROM THE SCANNER");
			fulfillmentItemToUpdate.setUserId("userid from the session");
			fulfillmentItemToUpdate.setUnitRetailAmount(1.0);
			fulfillmentItemToUpdate.setOverrideInd(true);
			
			order.getFulfillmentPickLines().add(fulfillmentItemToUpdate);
			basicDAO.addEntity(fulfillmentItemToUpdate);
			basicDAO.updateEntity(order);
			
		} else {
			
			 
			fulfillmentItemToUpdate.setPickTimeStamp(new Date());
			fulfillmentItemToUpdate.setToteNumber(1234);
			fulfillmentItemToUpdate.setUpcCode("UPC CODE FROM THE SCANNER");
			fulfillmentItemToUpdate.setUserId("userid from the session");
			fulfillmentItemToUpdate.setUnitRetailAmount(1.0);
			basicDAO.updateEntity(fulfillmentItemToUpdate);
			basicDAO.updateEntity(order);
			
		}

		
		
	}
	
	@Transactional
	public FulfillmentOrderVO buildOrderVO(String orderId) throws MavrickException {
		FulfillmentOrderVO orderVO = new FulfillmentOrderVO();

		FulfillmentOrder order = (FulfillmentOrder) basicDAO.getEntityById(orderId,new FulfillmentOrder());
		// Order order=cart.getOrder();
		
		if(order!=null){
		orderVO.setOrderId(order.getOrderId());
		orderVO.setOrderSequenceNumber(order.getOrderSequenceNumber());
		orderVO.setUserId(order.getUserId());
		orderVO.setLoad_number(order.getLoad_number());
		orderVO.setDeliveryDate(order.getDeliveryDate());
		orderVO.setStatus(order.getStatus());
		orderVO.setSubsitutionIndicator(order.isSubsitutionIndicator());
		orderVO.setCreateDate(order.getCreateDate());
		orderVO.setWebAmount(order.getWebAmount());
		orderVO.setPosAmount(order.getPosAmount());
		orderVO.setPostransactionNumber(order.getPostransactionNumber());
		orderVO.setPosCreateDate(order.getCreateDate());
		orderVO.setDeliveryInstructions(order.getDeliveryInstructions());
		orderVO.setCustomerSignatureDate(order.getCustomerSignatureDate());

		orderVO.setLateDeliveryInd(order.isLateDeliveryInd());

		orderVO.setPickNCollectInd(order.isPickNCollectInd());

		orderVO.setAuthorisedInd(order.isAuthorisedInd());

		orderVO.setRejectReasonCode(order.getRejectReasonCode());

		orderVO.setDeliveryCharge(order.getDeliveryCharge());
		
		orderVO.setTaxAmount(order.getTaxAmount());
		
		orderVO.setTotalDiscountAmount(order.getTotalDiscountAmount());
		
		orderVO.setPlannedDeliveryTime(order.getPlannedDeliveryTime());
		
		orderVO.setNotifyFSCode(order.getNotifyFSCode());
		
		if(order.getFulfillmentOrderLines()!=null&& !order.getFulfillmentOrderLines().isEmpty())
		{
			orderVO.setFulfillmentOrderLines(buildOrderFulfillmentVO(order));
		}
		
		if(order.getFulfillmentSubLines()!=null && !order.getFulfillmentSubLines().isEmpty())
		{
			orderVO.setFulfillmentSubLines(buildOrderSubVO(order));
		}

		if(order.getFulfillmentAdjLines()!=null && !order.getFulfillmentAdjLines().isEmpty())
		{
		buildOrderAdjVO(order);
		}}
		else{
			logger.info("order not found in the database");
			throw new MavrickException("error code for fulorder","order not found in the database");
		}
		return orderVO;
	}

	@Transactional
	private List<FulfillmentSubstitutionLineVO> buildOrderSubVO(
			FulfillmentOrder order) {
		// TODO Auto-generated method stub
		
		List <FulfillmentSubstitutionLine>fulfillmentSubLines=order.getFulfillmentSubLines();
		
		List<FulfillmentSubstitutionLineVO>fulfillmentSubLineVOS=new ArrayList<FulfillmentSubstitutionLineVO>();
		
		for(FulfillmentSubstitutionLine fulfillmentSubLine:fulfillmentSubLines)
		{
			FulfillmentSubstitutionLineVO fulfillmentSubstitutionLineVO=new FulfillmentSubstitutionLineVO();
			fulfillmentSubstitutionLineVO.setOrderId(fulfillmentSubLine.getOrderId());
			fulfillmentSubstitutionLineVO.setPickQuantity(fulfillmentSubLine.getPickQuantity());
			fulfillmentSubstitutionLineVO.setPickTimeStamp(fulfillmentSubLine.getPickTimeStamp());
			fulfillmentSubstitutionLineVO.setProductDescription(fulfillmentSubLine.getProductDescription());
			fulfillmentSubstitutionLineVO.setProductId(fulfillmentSubLine.getProductId());
			fulfillmentSubstitutionLineVO.setToteNumber(fulfillmentSubLine.getToteNumber());
			fulfillmentSubstitutionLineVO.setUnitRetailAmount(fulfillmentSubLine.getUnitRetailAmount());
			fulfillmentSubstitutionLineVO.setUpcNum(fulfillmentSubLine.getUpcNum());
			fulfillmentSubstitutionLineVO.setUserId(fulfillmentSubLine.getUserId());
			fulfillmentSubLineVOS.add(fulfillmentSubstitutionLineVO);
			
		}
		return fulfillmentSubLineVOS;
	}

	@Transactional
	private List<FulfillmentOrderLineVO> buildOrderFulfillmentVO(FulfillmentOrder order) {
		
		List <FulfillmentOrderLine>fulfillmentOrderLines=order.getFulfillmentOrderLines();
		
		List<FulfillmentOrderLineVO>fulfillmentOrderLineVOS=new ArrayList<FulfillmentOrderLineVO>();
		
		
		for(FulfillmentOrderLine fulfillmentOrderLine:fulfillmentOrderLines)
		{
			FulfillmentOrderLineVO fulfillmentOrderLineVO=new FulfillmentOrderLineVO();
			fulfillmentOrderLineVO.setProductId(fulfillmentOrderLine.getProductId());
			fulfillmentOrderLineVO.setOrderId(fulfillmentOrderLine.getOrderId());
			fulfillmentOrderLineVO.setDescription(fulfillmentOrderLine.getDescription());
			fulfillmentOrderLineVO.setOrderQuantity(fulfillmentOrderLine.getOrderQuantity());
			fulfillmentOrderLineVO.setOrderWeight(fulfillmentOrderLine.getOrderWeight());
			fulfillmentOrderLineVO.setProductTypeCode(fulfillmentOrderLine.getProductTypeCode());
			fulfillmentOrderLineVO.setSalesUnit(fulfillmentOrderLine.getSalesUnit());
			fulfillmentOrderLineVO.setStatus(fulfillmentOrderLine.getStatus());
			fulfillmentOrderLineVO.setSubsitutionQuantity(fulfillmentOrderLine.getSubsitutionQuantity());
			fulfillmentOrderLineVO.setSubsRetailAmount(fulfillmentOrderLine.getSubsitutionQuantity());
			fulfillmentOrderLineVO.setSubstituionInd(fulfillmentOrderLine.isSubstituionInd());
			fulfillmentOrderLineVO.setSystemMessage(fulfillmentOrderLine.getSystemMessage());
			fulfillmentOrderLineVO.setUnitRetailAmount(fulfillmentOrderLine.getUnitRetailAmount());
			fulfillmentOrderLineVO.setWebUnitRetailAmount(fulfillmentOrderLine.getUnitRetailAmount());
			fulfillmentOrderLineVO.setWeightedItemInd(fulfillmentOrderLine.isWeightedItemInd());
			
			fulfillmentOrderLineVOS.add(fulfillmentOrderLineVO);
			
			
			
		}
	
		return fulfillmentOrderLineVOS;
		// TODO Auto-generated method stub
		
	}
	@Transactional
	private List<FulfillmentOrderAdjustmentVO> buildOrderAdjVO(
			FulfillmentOrder order) {
		// TODO Auto-generated method stub
		
		List <FulfillmentOrderAdjustment>fulfillmentAdjLines=order.getFulfillmentAdjLines();
		
		List<FulfillmentOrderAdjustmentVO>fulfillmentAdjLineVOS=new ArrayList<FulfillmentOrderAdjustmentVO>();
		
		for(FulfillmentOrderAdjustment fulfillmentAdjLine:fulfillmentAdjLines)
		{
			FulfillmentOrderAdjustmentVO fulfillmentAdjLineVO=new FulfillmentOrderAdjustmentVO();
			fulfillmentAdjLineVO.setOrderId(fulfillmentAdjLine.getOrderId());
			fulfillmentAdjLineVO.setAmount(fulfillmentAdjLine.getAmount());
			fulfillmentAdjLineVO.setChangeTs(fulfillmentAdjLine.getChangeTs());
			fulfillmentAdjLineVO.setChangeUserId(fulfillmentAdjLine.getChangeUserId());
			fulfillmentAdjLineVO.setCostAdjustmentReasonCode(fulfillmentAdjLine.getCostAdjustmentReasonCode());
			fulfillmentAdjLineVO.setProductId(fulfillmentAdjLine.getProductId());
			fulfillmentAdjLineVO.setSequenceNum(fulfillmentAdjLine.getSequenceNum());
			fulfillmentAdjLineVO.setUpcCode(fulfillmentAdjLine.getUpcCode());
			
			fulfillmentAdjLineVOS.add(fulfillmentAdjLineVO);
		}
		return fulfillmentAdjLineVOS;
	}

	@Transactional
	private List<CommerceItemVO> buildFufillmentLineVO() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<MaverickVO> buildUserVO() {
		// TODO Auto-generated method stub
		return null;
	}

	public JSONResponse loginUser(FulfillmentUser usr) {
		// TODO Auto-generated method stub
		JSONResponse jsn = new JSONResponse(JSON_RESPONSE_TYPE);
		FulfillmentUser dbUser = null;

		if(getProfileQuery()==null){
			logger.info("profile query is null");
			
		}
		else{
			
		
		List<MavrickEntity> entity = basicDAO.runQuery(
				getProfileQuery().replaceAll("User", "FulfillmentUser") + "'" + usr.getEmail() + "'");
		if (null != entity && !entity.isEmpty()) {
			dbUser = (FulfillmentUser) entity.get(0);
		}
		/**
		 * TODO JSON generation should move to a generator.
		 * 
		 */

		try {
			if (getFulfillmentAuthenticationManager().validateUserByPassword(
					usr.getPassword(), dbUser)) {

				jsn.setEntity(dbUser);
				jsn.setStatusCode(getMvkTools().getLocalizedMessage(
						"status.code.success", null));
				jsn.setStatusMessage(getMvkTools().getLocalizedMessage(
						"status.api.execution.success",
						new Object[] { "Login User" }));
			} else {
				jsn.setStatusCode(getMvkTools().getLocalizedMessage(
						"status.code.failure", null));
				jsn.setStatusMessage(getMvkTools().getLocalizedMessage(
						"status.api.execution.failed",
						new Object[] { "Login User" }));
				Map<String, Object> mp = new HashMap<String, Object>();
				mp.put("errorCode", "MVK_AUTH_FAILURE");
				mp.put("errorMessage",
						getMvkTools().getLocalizedMessage("profile.auth.failed",
								null));
				List<Map<String, Object>> addObjects = new ArrayList<Map<String, Object>>();
				addObjects.add(mp);
				jsn.setAdditionalObjects(addObjects);

			}
		} catch (UserAuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return jsn;
		
		
	}

	public MavrickTools getMvkTools() {
		return mvkTools;
	}

	public void setMvkTools(MavrickTools mvkTools) {
		this.mvkTools = mvkTools;
	}

	public String getProfileQuery() {
		return profileQuery;
	}

	public void setProfileQuery(String profileQuery) {
		this.profileQuery = profileQuery;
	}

	public BasicAuthenticationManager getFulfillmentAuthenticationManager() {
		return fulfillmentAuthenticationManager;
	}

	public void setFulfillmentAuthenticationManager(
			BasicAuthenticationManager fulfillmentAuthenticationManager) {
		this.fulfillmentAuthenticationManager = fulfillmentAuthenticationManager;
	}

	public JSONResponse createUser(FulfillmentUser usr) throws UserAuthenticationException {
		// TODO Auto-generated method stub
		boolean success = false;
		JSONResponse jsn = new JSONResponse(JSON_RESPONSE_TYPE);
		usr.setLastModifiedDate(new Date());

		logger.info("Begin hashing password.");
		try {
			fulfillmentAuthenticationManager.hashPasswordForUpdate(usr);
		} catch (UserAuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("Calling DAO to save entity.. in the fulfillmentordermanager");
		if(getProfileQuery()==null){
			logger.info("profile query is null");
		}
		else{
		
		
		List<MavrickEntity> obj = basicDAO.runQuery(getProfileQuery().replaceAll("User", "FulfillmentUser")  + "'"
				+ usr.getEmail() + "'");
		if (null != obj && !obj.isEmpty()) {

			throw new UserAuthenticationException("MVK_USR_CRT_ERR",
					getMvkTools().getLocalizedMessage("profile.exisit", null));
		}
		
		logger.info("adding the entity");
		basicDAO.addEntity(usr);
		logger.info("done adding the entity");
		success = true;
		
		
		if (success) {
			jsn.setEntity(usr);
			jsn.setStatusCode(getMvkTools().getLocalizedMessage(
					"status.code.success", null));
			jsn.setStatusMessage(getMvkTools().getLocalizedMessage(
					"status.api.execution.success",
					new Object[] { "Register User" }));
		} else {

			jsn.setStatusCode(getMvkTools().getLocalizedMessage(
					"status.code.failure", null));
			jsn.setStatusMessage(getMvkTools().getLocalizedMessage(
					"status.api.execution.failed",
					new Object[] { "Register User" }));

		}
		}
		return jsn;
	}

	public void updateOrderForSubItem(String orderId, String productId, int qty) {
		// TODO Auto-generated method stub
		FulfillmentSubstitutionLine fulfillmentItemToUpdate = null;
		FulfillmentOrder order = (FulfillmentOrder) basicDAO.getEntityById(orderId,new FulfillmentOrder());
		
		List<FulfillmentSubstitutionLine> subLines = order.getFulfillmentSubLines();
		if (subLines == null || subLines.isEmpty()) {
			subLines = new ArrayList<FulfillmentSubstitutionLine>();
		} else {
			for (FulfillmentSubstitutionLine fulfillmentSubstitutionLine : subLines) {
				if (fulfillmentSubstitutionLine != null && fulfillmentSubstitutionLine.getProductId() == productId) {
					fulfillmentItemToUpdate = fulfillmentSubstitutionLine;
				}
			}
		}
		// logger.info("commerceitemsuid -- > "+commerceItemToUpdate.getSkuId());
		if (fulfillmentItemToUpdate == null) {

			
			fulfillmentItemToUpdate = new FulfillmentSubstitutionLine();
			fulfillmentItemToUpdate.setProductId(productId);
			fulfillmentItemToUpdate.setPickQuantity(qty);
			fulfillmentItemToUpdate.setOrderId(orderId);
			fulfillmentItemToUpdate.setPickTimeStamp(new Date());
			//fulfillmentItemToUpdate.setToteNumber(1234);
			//fulfillmentItemToUpdate.setUpcCode("UPC CODE FROM THE SCANNER");
			fulfillmentItemToUpdate.setUserId("userid from the session");
			fulfillmentItemToUpdate.setUnitRetailAmount(1.0);
			//fulfillmentItemToUpdate.setOverrideInd(true);
			
			//order.getFulfillmentPickLines().add(fulfillmentItemToUpdate);
			basicDAO.addEntity(fulfillmentItemToUpdate);
			basicDAO.updateEntity(order);
			
		} else {
			
			 
			fulfillmentItemToUpdate.setPickTimeStamp(new Date());
			//fulfillmentItemToUpdate.setToteNumber(1234);
			//fulfillmentItemToUpdate.setUpcCode("UPC CODE FROM THE SCANNER");
			fulfillmentItemToUpdate.setUserId("userid from the session");
			fulfillmentItemToUpdate.setUnitRetailAmount(1.0);
			basicDAO.updateEntity(fulfillmentItemToUpdate);
			basicDAO.updateEntity(order);
			
		}
	}

	public MaverickVO buildLoadVO(String loadId,String loadDeliveryDate) throws MavrickException {
		// TODO Auto-generated method stub
		FulfillmentLoadVO fulfillmentLoadVO=new FulfillmentLoadVO();
		
		
		List<MavrickEntity>fulfillmentLoadList = basicDAO.runQuery(getLoadQuery());
		
		FulfillmentLoad fulfillmentLoad=null;
		for(MavrickEntity mavrickEntity:fulfillmentLoadList){
			FulfillmentLoad loadFromDatabase=(FulfillmentLoad)mavrickEntity;
			String dateformat="YYYY-MM-DD";
			SimpleDateFormat sdf=new SimpleDateFormat();
			String deliveryDateFromLoad=sdf.format(loadFromDatabase.getDeliveryDate());
			
			if(deliveryDateFromLoad==loadDeliveryDate){
			
				fulfillmentLoad=loadFromDatabase;
			}
		}
		
		if(fulfillmentLoad!=null){
			fulfillmentLoadVO.setLoadId(fulfillmentLoad.getLoadId());
			fulfillmentLoadVO.setDeliveryDate(fulfillmentLoad.getDeliveryDate());
			fulfillmentLoadVO.setFulfillmentOrder(fulfillmentLoad.getFulfillmentOrder());
			fulfillmentLoadVO.setLoadStatus(fulfillmentLoad.getLoadStatus());
			fulfillmentLoadVO.setMobilePhoneNum(fulfillmentLoad.getMobilePhoneNum());
			fulfillmentLoadVO.setUserId(fulfillmentLoad.getUserId());
			fulfillmentLoadVO.setBeginMileage(fulfillmentLoad.getBeginMileage());
			fulfillmentLoadVO.setEndMileage(fulfillmentLoad.getEndMileage());
			
			
			
		}
		else{
			logger.info("load not found in the database for the given date");
			throw new MavrickException("error code for fulorder","load not found in the database for the given date");
		}
		return fulfillmentLoadVO;
		
	}

	public String getLoadQuery() {
		return loadQuery;
	}

	public void setLoadQuery(String loadQuery) {
		this.loadQuery = loadQuery;
	}

	public MaverickVO getOrdersForLoad(String loadId, String deliveryDate,String status) {
		// TODO Auto-generated method stub
		
		String query=getOrderQuery();
	
		
		List<MavrickEntity>orderList=basicDAO.runQuery(getOrderQuery());
		for(MavrickEntity fo:orderList){
		
		
		
		
		
		
		}
		
		
		
		
		
		return null;
	}

	public String getOrderQuery() {
		return orderQuery;
	}

	public void setOrderQuery(String orderQuery) {
		this.orderQuery = orderQuery;
	}

	
}
