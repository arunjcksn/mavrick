package com.mavrick.manager.order;

import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.mavrick.common.catalog.CatalogManager;
import com.mavrick.common.constants.JSONConstants;
import com.mavrick.common.dao.BasicDAO;
import com.mavrick.common.exceptions.MavrickException;
import com.mavrick.common.exceptions.ResponseGenException;
import com.mavrick.common.json.EntityResponseGenerator;
import com.mavrick.common.json.annotation.Groups;
import com.mavrick.common.session.Cart;
import com.mavrick.common.session.UserProfile;
import com.mavrick.common.vo.MaverickVO;
import com.mavrick.common.vo.order.CommerceItemVO;
import com.mavrick.common.vo.order.OrderDeliveryDetailsVO;
import com.mavrick.common.vo.order.OrderShipAddressVO;
import com.mavrick.common.vo.order.OrderVO;
import com.mavrick.common.vo.order.PriceInfoVO;
import com.mavrick.manager.pricing.PricingEngineService;
import com.mavrick.manager.pricing.PricingType;
import com.mavrick.persistance.entities.MavrickEntity;
import com.mavrick.persistance.entities.Sku;
import com.mavrick.persistance.entities.User;
import com.mavrick.persistance.entities.order.CommerceItem;
import com.mavrick.persistance.entities.order.Order;
import com.mavrick.persistance.entities.order.OrderAuthStatus;
import com.mavrick.persistance.entities.order.OrderDeliveryDetails;
import com.mavrick.persistance.entities.order.OrderPayment;
import com.mavrick.persistance.entities.order.OrderShipAddress;
import com.mavrick.persistance.entities.order.PriceInfo;
import com.mavrick.persistance.entities.profile.Address;
import com.mavrick.persistance.entities.profile.UserPrimaryAddress;
import com.mavrick.shopping.shipping.DeliverySlotDetail;

public class OrderManager {

	@Autowired
	Cart cart;
	@Autowired
	BasicDAO basicDAO;
	@Autowired
	UserProfile profile;
	@Autowired
	PricingEngineService pricingService;
	

	protected Logger logger = Logger.getLogger(getClass());
	// @Autowired
	CatalogManager catalogManager;

	@Transactional
	public void updateOrderForAddItem(int skuId, int qty) {
		CommerceItem commerceItemToUpdate = null;
		Sku sku = this.getSkuDetails(skuId);
		List<String>orderExtendedProperties=getEntityResponseGenerator().getResponseGenTools().getExtendedProperties(cart.getOrder());
		
		Order order = (Order) basicDAO.getEntityById(cart.getOrder()
				.getOrderId(), cart.getOrder(),orderExtendedProperties);
		List<CommerceItem> commerceItems = order.getCommerceItems();
		if (commerceItems == null || commerceItems.isEmpty()) {
			commerceItems = new ArrayList<CommerceItem>();
		} else {
			for (CommerceItem commerceItem : commerceItems) {
				if (commerceItem != null && commerceItem.getSkuId() == skuId) {
					commerceItemToUpdate = commerceItem;
					break;
				}
			}
		}
		// logger.info("commerceitemsuid -- > "+commerceItemToUpdate.getSkuId());
		if (commerceItemToUpdate == null) {

			List<PriceInfo> itemPriceInfo = new ArrayList<PriceInfo>();
			PriceInfo priceInfo = new PriceInfo();
			priceInfo.setAmount(sku.getBasePrice() * qty);
			basicDAO.addEntity(priceInfo);
			commerceItemToUpdate = new CommerceItem();
			commerceItemToUpdate.setDisplayName(sku.getProductName());
			commerceItemToUpdate.setQty(qty);
			commerceItemToUpdate.setSkuId(skuId);
			commerceItemToUpdate.setOrderId(cart.getOrder().getOrderId());
			commerceItemToUpdate.setUnitPrice(sku.getBasePrice());

			commerceItemToUpdate.setPriceId(priceInfo.getPriceId());
			itemPriceInfo.add(priceInfo);
			commerceItemToUpdate.setItemPriceInfo(itemPriceInfo);
			cart.getCommerceItems().add(commerceItemToUpdate);
			order.setCommerceItems(cart.getCommerceItems());
		} else {
			 List<PriceInfo> itemPriceInfo = commerceItemToUpdate
			 .getItemPriceInfo();
			PriceInfo priceInfo = (PriceInfo) basicDAO.getEntityById(
					commerceItemToUpdate.getPriceId(), PriceInfo.class);
			priceInfo.setAmount(sku.getBasePrice()
					* (commerceItemToUpdate.getQty() + qty));
			basicDAO.updateEntity(priceInfo);
			commerceItemToUpdate.setDisplayName(sku.getProductName());
			commerceItemToUpdate.setUnitPrice(sku.getBasePrice());
			commerceItemToUpdate.setQty(commerceItemToUpdate.getQty() + qty);
			// commerceItemToUpdate.setPriceId(priceInfo.getPriceId());
			 commerceItemToUpdate.setItemPriceInfo(itemPriceInfo);
			 cart.setCommerceItems(commerceItems);
		}

		basicDAO.updateEntity(commerceItemToUpdate);
		
		order = repriceOrderForItemChange(order);
		basicDAO.updateEntity(order);
		cart.setOrder(order);
	}

	@Transactional
	private Order repriceOrderForItemChange(Order order) {
		
		List <String>extendedList=getEntityResponseGenerator().getResponseGenTools().getExtendedProperties(cart.getOrder());
		Order order1 = (Order) basicDAO.getEntityById(cart.getOrder()
				.getOrderId(), cart.getOrder(),extendedList);
/*		// order=cart.getOrder();
		List<CommerceItem> commerceItems = cart.getCommerceItems();
		PriceInfo priceInfo = (PriceInfo) basicDAO.getEntityById(cart
				.getOrder().getPriceId(), PriceInfo.class);
		PriceInfo itemPriceInfo = null;
		double amount = 0;
		for (CommerceItem commerceItem : commerceItems) {
			itemPriceInfo = (PriceInfo) basicDAO.getEntityById(
					commerceItem.getPriceId(), PriceInfo.class);
			amount = amount + itemPriceInfo.getAmount();
		}
		priceInfo.setAmount(amount);
		basicDAO.updateEntity(priceInfo);
		// priceInfos.add(priceInfo);
		// order.setOrderPrice(priceInfos);
		// cart.setOrder(order);*/
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("order",order1);
		params.put("type",PricingType.ORDER_TOTAL);
		
		pricingService.calculate(params);
		return order;
	}

	public CatalogManager getCatalogManager() {
		return catalogManager;
	}

	public void setCatalogManager(CatalogManager catalogManager) {
		this.catalogManager = catalogManager;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCar(Cart cart) {
		this.cart = cart;
	}

	public BasicDAO getBasicDAO() {
		return basicDAO;
	}

	public void setBasicDAO(BasicDAO basicDAO) {
		this.basicDAO = basicDAO;
	}

	public UserProfile getProfile() {
		return profile;
	}

	public void setProfile(UserProfile profile) {
		this.profile = profile;
	}

	private Sku getSkuDetails(int skuId) {

		Sku sku = null;
		List<MavrickEntity> entity = getBasicDAO().runQuery(
				"from Sku where skuId=" + skuId);
		if (null != entity && !entity.isEmpty()) {
			sku = (Sku) entity.get(0);
		}
		return sku;
	}

	@Transactional
	public OrderVO buildOrderVO(Cart cart) {
		OrderVO orderVO = new OrderVO();
		PriceInfoVO orderPriceVO = null;

		Order order = (Order) basicDAO.getEntityById(cart.getOrder()
				.getOrderId(), cart.getOrder());
		// Order order=cart.getOrder();
		orderVO.setOrderId(order.getOrderId());
		orderVO.setState(order.getState());
		orderVO.setSubmittedDate(order.getSubmittedDate());
		orderVO.setUserId(order.getUserId());
		if (order.getCommerceItems() != null
				&& !order.getCommerceItems().isEmpty()) {
			orderVO.setCommerceItems(buildCommerceItemVO());
		}
		if (order.getDeliveryDetails() != null
				&& !order.getDeliveryDetails().isEmpty()) {
		orderVO.setDeliveryDetailsVO(buildDeliveryDetailsVO());
		orderVO.setShipAddressVO(buildShipAddressVO());
		}
		List<PriceInfo> priceInfoLists = (List<PriceInfo>) order.getOrderPrice();
		for (PriceInfo orderPriceInfo:priceInfoLists) {
			orderPriceVO = new PriceInfoVO();
			orderPriceVO.setAmount(orderPriceInfo.getAmount());
			if ("subtotal".equals(orderPriceInfo.getType())) {
				orderVO.setOrderSubTotalPrice(orderPriceVO);
				
			} else if ("shiptotal".equals(orderPriceInfo.getType())) {
				orderVO.setOrderShippingTotalPrice(orderPriceVO);

				
			} else if ("taxtotal".equals(orderPriceInfo.getType())) {
				orderVO.setOrderTaxTotalPrice(orderPriceVO);
				
			} else if ("ordertotal".equals(orderPriceInfo.getType())) {
				orderVO.setOrderPrice(orderPriceVO);
				
			}
		}

		return orderVO;
	}

	
	/*@Transactional
	public OrderVO buildOrderVO(int orderId) {
		OrderVO orderVO = new OrderVO();
		PriceInfoVO orderPriceVO = null;

		Order order = (Order) basicDAO.getEntityById(orderId,cart.getOrder());
		orderVO.setOrderId(order.getOrderId());
		orderVO.setState(order.getState());
		orderVO.setSubmittedDate(order.getSubmittedDate());
		orderVO.setUserId(order.getUserId());
		if (order.getCommerceItems() != null
				&& !order.getCommerceItems().isEmpty()) {
			orderVO.setCommerceItems(buildCommerceItemVO());
		}
		if (order.getDeliveryDetails() != null
				&& !order.getDeliveryDetails().isEmpty()) {
		orderVO.setDeliveryDetailsVO(buildDeliveryDetailsVO());
		orderVO.setShipAddressVO(buildShipAddressVO());
		}
		List<PriceInfo> priceInfoLists = (List<PriceInfo>) order.getOrderPrice();
		for (PriceInfo orderPriceInfo:priceInfoLists) {
			orderPriceVO = new PriceInfoVO();
			orderPriceVO.setAmount(orderPriceInfo.getAmount());
			if ("subtotal".equals(orderPriceInfo.getType())) {
				orderVO.setOrderSubTotalPrice(orderPriceVO);
				
			} else if ("shiptotal".equals(orderPriceInfo.getType())) {
				orderVO.setOrderShippingTotalPrice(orderPriceVO);

				
			} else if ("taxtotal".equals(orderPriceInfo.getType())) {
				orderVO.setOrderTaxTotalPrice(orderPriceVO);
				
			} else if ("ordertotal".equals(orderPriceInfo.getType())) {
				orderVO.setOrderPrice(orderPriceVO);
				
			}
		}

		return orderVO;
	}*/
	
	@Transactional
	private List<CommerceItemVO> buildCommerceItemVO() {
		Order order = (Order) basicDAO.getEntityById(cart.getOrder()
				.getOrderId(), cart.getOrder());
		//order = cart.getOrder();
		List<CommerceItem> commerceItems = order.getCommerceItems();
		List<CommerceItemVO> commerceItemVOs = new ArrayList<CommerceItemVO>();
		for (CommerceItem commerceItem : commerceItems) {
			CommerceItemVO commerceItemVO = new CommerceItemVO();
			commerceItemVO.setCommerceItemId(commerceItem.getCommerceItemId());
			commerceItemVO.setDisplayName(commerceItem.getDisplayName());
			commerceItemVO.setOrderId(commerceItem.getOrderId());
			commerceItemVO.setPriceId(commerceItem.getPriceId());
			commerceItemVO.setQty(commerceItem.getQty());
			commerceItemVO.setSkuId(commerceItem.getSkuId());
			commerceItemVO.setUnitPrice(commerceItem.getUnitPrice());
			

			PriceInfo priceInfo = (PriceInfo) basicDAO.getEntityById(commerceItem.getPriceId(), PriceInfo.class);
			PriceInfoVO priceInfoVO = new PriceInfoVO();
			priceInfoVO.setAdjustment(priceInfo.getAdjustment());
			priceInfoVO.setAmount(priceInfo.getAmount());
			priceInfoVO.setPriceId(priceInfo.getPriceId());

			commerceItemVO.setItemPriceInfo(priceInfoVO);
			commerceItemVOs.add(commerceItemVO);
		}
		return commerceItemVOs;
	}
	
	@Transactional
	private OrderDeliveryDetailsVO buildDeliveryDetailsVO() {
		Order order = (Order) basicDAO.getEntityById(cart.getOrder()
				.getOrderId(), cart.getOrder());
		//order = cart.getOrder();
		List<OrderDeliveryDetails> deliveryDetails  = order.getDeliveryDetails();
		OrderDeliveryDetailsVO deliveryDetailsVO=new OrderDeliveryDetailsVO();
		for (OrderDeliveryDetails orderDeliveryDetail : deliveryDetails) {
			
			deliveryDetailsVO.setOrderDelId(orderDeliveryDetail.getOrderDelId());
			deliveryDetailsVO.setOrderId(orderDeliveryDetail.getOrderId());
			deliveryDetailsVO.setDeliveryStopId(orderDeliveryDetail.getDeliveryStopId());
			deliveryDetailsVO.setDelDate(orderDeliveryDetail.getDelDate());
			
			deliveryDetailsVO.setDelStartTime(orderDeliveryDetail.getDelStartTime());
			deliveryDetailsVO.setDelEndTime(orderDeliveryDetail.getDelEndTime());
			deliveryDetailsVO.setDoorStepTime(orderDeliveryDetail.getDoorStepTime());
			
			deliveryDetailsVO.setPriceId(orderDeliveryDetail.getPriceId());
			
			deliveryDetailsVO.setStatus(orderDeliveryDetail.getStatus());
			deliveryDetailsVO.setStoreId(orderDeliveryDetail.getStoreId());
			deliveryDetailsVO.setVolume(orderDeliveryDetail.getVolume());
			deliveryDetailsVO.setWeight(orderDeliveryDetail.getWeight());

			PriceInfo priceInfo = (PriceInfo) basicDAO.getEntityById(orderDeliveryDetail.getPriceId(), PriceInfo.class);
			PriceInfoVO priceInfoVO = new PriceInfoVO();
			priceInfoVO.setAdjustment(priceInfo.getAdjustment());
			priceInfoVO.setAmount(priceInfo.getAmount());
			priceInfoVO.setPriceId(priceInfo.getPriceId());

			deliveryDetailsVO.setShippingPriceInfo(priceInfoVO);
			
		}
		return deliveryDetailsVO;
	}

	@Transactional
	private OrderShipAddressVO buildShipAddressVO() {
		Order order = (Order) basicDAO.getEntityById(cart.getOrder()
				.getOrderId(), cart.getOrder());
		//order = cart.getOrder();
		List<OrderShipAddress> shipAddresses  = order.getShipAddress();
		OrderShipAddressVO orderShipAddressVO =new OrderShipAddressVO();
		for (OrderShipAddress shipAddress : shipAddresses) {
			
			orderShipAddressVO.setShipAddrId(shipAddress.getShipAddrId());
			orderShipAddressVO.setOrderId(shipAddress.getOrderId());
			orderShipAddressVO.setFirstName(shipAddress.getFirstName());
			orderShipAddressVO.setLastName(shipAddress.getLastName());
			orderShipAddressVO.setEmail(shipAddress.getEmail());
			orderShipAddressVO.setAddressOne(shipAddress.getAddressOne());
			orderShipAddressVO.setAddressTwo(shipAddress.getAddressTwo());
			orderShipAddressVO.setArea(shipAddress.getArea());
			orderShipAddressVO.setPostcode(shipAddress.getPostcode());
			orderShipAddressVO.setPhoneNum(shipAddress.getPhoneNum());
			orderShipAddressVO.setMobilePhoneNum(shipAddress.getMobilePhoneNum());
			orderShipAddressVO.setAltPhoneNum(shipAddress.getAltPhoneNum());
			
		}
		return orderShipAddressVO;
	}
	@Transactional
	public void bookDeliverySlotForOrder(DeliverySlotDetail deliverySlotDetail)
			throws MavrickException {
		boolean success = true;
		Order order = (Order) basicDAO.getEntityById(cart.getOrder()
				.getOrderId(), cart.getOrder());
		/*********************************************************/
		// send delivery slot update request to delivery slot service
		// get the success or failure flag
		/***************************************************************/
		List<PriceInfo> shippingPriceInfo = new ArrayList<PriceInfo>();
		PriceInfo priceInfo = null;
		List<OrderDeliveryDetails> deliveryDetailList = order
				.getDeliveryDetails();
		if (success) {
			OrderDeliveryDetails deliveryDetails = null;
			if (deliveryDetailList != null && !deliveryDetailList.isEmpty()) {
				deliveryDetails = deliveryDetailList.get(0);
			} else {
				deliveryDetailList = new ArrayList<OrderDeliveryDetails>();
			}
			if (deliveryDetails == null) {
				deliveryDetails = new OrderDeliveryDetails();
				shippingPriceInfo = new ArrayList<PriceInfo>();
				priceInfo = new PriceInfo();
				shippingPriceInfo.add(priceInfo);
				deliveryDetailList.add(deliveryDetails);
				basicDAO.addEntity(priceInfo);
			} else {
				// send request to cancel the slot already existing
				priceInfo = (PriceInfo) basicDAO.getEntityById(
						deliveryDetails.getPriceId(), PriceInfo.class);
			}
			deliveryDetails.setDelDate(new GregorianCalendar());
			deliveryDetails.setDelEndTime(deliverySlotDetail.getEndTime());
			deliveryDetails.setDelStartTime(deliverySlotDetail.getStartTime());
			deliveryDetails.setDeliveryStopId(deliverySlotDetail.getStopId());
			deliveryDetails.setDoorStepTime(100);// calculate DST
			deliveryDetails.setOrderId(cart.getOrder().getOrderId());
			deliveryDetails.setStatus(1);
			deliveryDetails.setStoreId(deliverySlotDetail.getStoreId());
			deliveryDetails.setWeight(2.0);// calculate weight
			deliveryDetails.setVolume(2.0);
			;// calculate volume
			// ////////////////////////////////
			priceInfo.setAmount(deliverySlotDetail.getSlotPrice());
			// ///////////////////////////////////////
			deliveryDetails.setPriceId(priceInfo.getPriceId());
			// itemPriceInfo.add(priceInfo);
			deliveryDetails.setShippingPriceInfo(shippingPriceInfo);
			basicDAO.updateEntity(priceInfo);
			basicDAO.updateEntity(deliveryDetails);
			////////////////////////////////////////////////////////////////
			/* POPULATE SHIPPING ADDRESS */
			User user= (User) basicDAO.getEntityById(
					order.getUserId(), User.class);
			UserPrimaryAddress userPrimaryAddress=user.getPrimaryAddress();
			
			Address address = (Address) basicDAO.getEntityById(
					userPrimaryAddress.getShipAddrId(), Address.class);
			
			List<OrderShipAddress> shipAddresses=order.getShipAddress();
			OrderShipAddress  orderShipAddress=null;
			if(shipAddresses==null ||shipAddresses.isEmpty()){
				shipAddresses=new ArrayList<OrderShipAddress>();
				orderShipAddress=new OrderShipAddress();
				shipAddresses.add(orderShipAddress);
			}else{
				orderShipAddress=shipAddresses.get(0);
			}
			
			orderShipAddress.setOrderId(cart.getOrder().getOrderId());
			orderShipAddress.setEmail(user.getEmail());
			orderShipAddress.setAddressOne(address.getAddress1());
			orderShipAddress.setAddressTwo(address.getAddress2());
			orderShipAddress.setArea(address.getArea());
			orderShipAddress.setFirstName(user.getFirstName());
			orderShipAddress.setLastName(user.getLastName());
			orderShipAddress.setPostcode(address.getPostcode());
			orderShipAddress.setPhoneNum(address.getPhoneNum());
			orderShipAddress.setMobilePhoneNum(address.getMobilePhoneNum());
			orderShipAddress.setAltPhoneNum(address.getAltPhoneNum());
			basicDAO.updateEntity(orderShipAddress);
			
			order.setShipAddress(shipAddresses);			
			
			///////////////////////////////////////////////////////////////
			basicDAO.updateEntity(order);
			cart.setOrder(order);
		} else {
			throw new MavrickException("MVK_SLOT_BOOK_FAILED",
					"Slot_Booking__Failed");
		}

	}
	@Transactional
	public boolean confirmOrder(String payMode, String nickName, String cvv) throws MavrickException{
		
		Order order = (Order) basicDAO.getEntityById(cart.getOrder()
				.getOrderId(), cart.getOrder());
		List<OrderPayment> orderPayments = order.getOrderPayment();
		
		OrderPayment orderPayment = null;
		if (orderPayments != null && !orderPayments.isEmpty()) {
			orderPayment = orderPayments.get(0);
		} else {
			orderPayments = new ArrayList<OrderPayment>();
		}
		if (orderPayment == null) {
			orderPayment = new OrderPayment();
			basicDAO.addEntity(orderPayment);
			orderPayments.add(orderPayment);
			
		}
		PriceInfo priceInfo = (PriceInfo) basicDAO.getEntityById(order.getPriceId(), PriceInfo.class);
		orderPayment.setAmount(priceInfo.getAmount());
		orderPayment.setOrderId(order.getOrderId());
					
		if(payMode.equals("CASH")){
			orderPayment.setPaymentType(0);
		}else{
			boolean authSuccess=true;
			orderPayment.setPaymentType(1);
			////////////////////////////////////////////
			//get details of card based on card nickname
			////////////////////////////////////////////
			List<OrderAuthStatus> authStatusList=orderPayment.getOrderAuthStatus();
			OrderAuthStatus authStatus=null;
			if(authStatusList !=null && !authStatusList.isEmpty()){
			authStatus=authStatusList.get(0);
			}else{
				authStatusList=new ArrayList<OrderAuthStatus>();
				authStatus=new OrderAuthStatus();
				authStatusList.add(authStatus);
			}
			
			/*
			 * CALL PAYMENT SERVICE WITH CARD DETAILS AND CVV
			 * 
			 */
			
			if(authSuccess){
				authStatus.setAmount(priceInfo.getAmount());
				authStatus.setAuthId(0);
				authStatus.setAuthStatus("SUCCESS");
				authStatus.setPaymentId(orderPayment.getPaymentId());
				authStatus.setSettlmntStatus(null);
				basicDAO.updateEntity(authStatus);
			}else{
				throw new MavrickException("MVK_ERROR_PAYMENT", "Payment Authorization failed");
			}
			orderPayment.setOrderAuthStatus(authStatusList);
			
			/////////////////////////////////////////
		}
		basicDAO.updateEntity(orderPayment);
	// ////////////////////////////////////////////////////

	order.setOrderPayment(orderPayments);
	order.setState("SUBMITTED");
	basicDAO.updateEntity(order);
		
		return true;
	}
	
	//changes for order history start

	
		private String orderQuery;
		
		
	@Transactional
	public List<MaverickVO> getOrders(int profileId) {
		User user = (User) getProfile().getUserEntity();
		
		user = (User) basicDAO.getEntityById(profileId, user);
		List<MavrickEntity> entity = getBasicDAO().runQuery(
				getOrderQuery() + "'" + user.getId() + "'");
		List<MaverickVO> orderVos = new ArrayList<MaverickVO>();
		if (null != entity && !entity.isEmpty()) {

			for (MavrickEntity mEntity : entity) {

				Order order = (Order) mEntity;
				OrderVO orderVO = buildOrderVO(order.getOrderId());

				orderVos.add(orderVO);

			}

		}

		return orderVos;
	}

		public OrderVO buildOrderVO(int orderId) {
			OrderVO orderVO = new OrderVO();
			PriceInfoVO orderPriceVO = null;

			List<MaverickVO>vos=null;
			List <String>orderAllExtendedProperties=getEntityResponseGenerator().getResponseGenTools().getExtendedProperties(cart.getOrder());
			Order order = (Order) basicDAO.getEntityById(orderId, cart.getOrder(), orderAllExtendedProperties);
			try {
				vos=getEntityResponseGenerator().generateResponse(order, Groups.EXTENED, JSONConstants.ORDER);
				
				
			} catch (ResponseGenException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Order order=cart.getOrder();
			orderVO.setOrderId(order.getOrderId());
			orderVO.setState(order.getState());
			orderVO.setSubmittedDate(order.getSubmittedDate());
			orderVO.setUserId(order.getUserId());
			if (order.getCommerceItems() != null
					&& !order.getCommerceItems().isEmpty()) {
				orderVO.setCommerceItems(buildCommerceItemVO());
			}
			if (order.getDeliveryDetails() != null
					&& !order.getDeliveryDetails().isEmpty()) {
			orderVO.setDeliveryDetailsVO(buildDeliveryDetailsVO());
			orderVO.setShipAddressVO(buildShipAddressVO());
			}
			List<PriceInfo> priceInfoLists = (List<PriceInfo>) order.getOrderPrice();
			for (PriceInfo orderPriceInfo:priceInfoLists) {
				orderPriceVO = new PriceInfoVO();
				orderPriceVO.setAmount(orderPriceInfo.getAmount());
				if ("subtotal".equals(orderPriceInfo.getType())) {
					orderVO.setOrderSubTotalPrice(orderPriceVO);
					
				} else if ("shiptotal".equals(orderPriceInfo.getType())) {
					orderVO.setOrderShippingTotalPrice(orderPriceVO);

					
				} else if ("taxtotal".equals(orderPriceInfo.getType())) {
					orderVO.setOrderTaxTotalPrice(orderPriceVO);
					
				} else if ("ordertotal".equals(orderPriceInfo.getType())) {
					orderVO.setOrderPrice(orderPriceVO);
					
				}
			}

			return orderVO;
		}

		public String getOrderQuery() {
			return orderQuery;
		}

		public void setOrderQuery(String orderQuery) {
			this.orderQuery = orderQuery;
		}

		public void updateOrderForUpdateItem(int skuId, int qty,
				int quantityFlag) throws MavrickException {
			// TODO Auto-generated method stub
			
			CommerceItem commerceItemToUpdate = null;
			int newquantity=0;
			
			Sku sku = this.getSkuDetails(skuId);
			List <String>extendedList=getEntityResponseGenerator().getResponseGenTools().getExtendedProperties(cart.getOrder());
			
			Order order = (Order) basicDAO.getEntityById(cart.getOrder()
					.getOrderId(), cart.getOrder(),extendedList);
			
			List<CommerceItem> commerceItems = order.getCommerceItems();
			if (commerceItems == null || commerceItems.isEmpty()) {
				commerceItems = new ArrayList<CommerceItem>();
			} else {
				for (CommerceItem commerceItem : commerceItems) {
					if (commerceItem != null && commerceItem.getSkuId() == skuId) {
						commerceItemToUpdate = commerceItem;
						break;
					}
				}
			}
			// logger.info("commerceitemsuid -- > "+commerceItemToUpdate.getSkuId());
			if (commerceItemToUpdate == null) {

				logger.info("Commerce item doesnt exist");
				throw new MavrickException("MVK_ERROR_ORDER", "Commerce item doesnt exist for update");
				
			} else {
				if(quantityFlag==0){
					
					int quantity=commerceItemToUpdate.getQty();
					
					if(quantity==1){
						updateOrderForRemoveItem(commerceItemToUpdate.getSkuId());
					}
					else if(quantity>1){
						newquantity=commerceItemToUpdate.getQty()-1;
						commerceItemToUpdate.setQty(newquantity);
						List<PriceInfo> itemPriceInfo = commerceItemToUpdate
								 .getItemPriceInfo();
								PriceInfo priceInfo = (PriceInfo) basicDAO.getEntityById(
										commerceItemToUpdate.getPriceId(), PriceInfo.class);
								priceInfo.setAmount(sku.getBasePrice()
										* newquantity);
								basicDAO.updateEntity(priceInfo);
								basicDAO.updateEntity(commerceItemToUpdate);
						
					}
				 
				
				 cart.setCommerceItems(commerceItems);
				}
				else{
					updateOrderForAddItem(skuId, 1);
				}
			}

			
			
			order = repriceOrderForItemChange(order);
			basicDAO.updateEntity(order);
			cart.setOrder(order);
			
		}
		
		public void removeItem(int commerceItemId) throws MavrickException {
			List <String>extendedList=getEntityResponseGenerator().getResponseGenTools().getExtendedProperties(cart.getOrder());

			CommerceItem itemToRemove = null;
			Order order = (Order) basicDAO.getEntityById(cart.getOrder()
					.getOrderId(), cart.getOrder(),extendedList);
			
			List<CommerceItem> commerceItems = order.getCommerceItems();
			if (commerceItems != null && !commerceItems.isEmpty()) {
				for (CommerceItem commerceItem : commerceItems) {
					if (commerceItem != null && commerceItem.getCommerceItemId() == commerceItemId) {
						itemToRemove = commerceItem;
						break;
					}
				}
			}
			if (itemToRemove != null) {
				List<PriceInfo> priceInfos = itemToRemove.getItemPriceInfo();
				for (PriceInfo pInfo:priceInfos) {
					basicDAO.deleteEntity(pInfo);
				}
				Collection<PriceInfo> oPriceInfos = order.getOrderPrice();
				for (PriceInfo oPInfo:oPriceInfos) {
					basicDAO.deleteEntity(oPInfo);
				}
				basicDAO.deleteEntity(itemToRemove);
				basicDAO.updateEntity(order);
				order = repriceOrderForItemChange(order);
				basicDAO.updateEntity(order);
				cart.setOrder(order);
			}
		}		

		private void updateOrderForRemoveItem(int skuId) {
			// TODO Auto-generated method stub
			CommerceItem commerceItemToUpdate = null;
			int newquantity=0;
			
			Sku sku = this.getSkuDetails(skuId);
			Order order = (Order) basicDAO.getEntityById(cart.getOrder()
					.getOrderId(), cart.getOrder());
			List<CommerceItem> commerceItems = order.getCommerceItems();
			if (commerceItems == null || commerceItems.isEmpty()) {
				commerceItems = new ArrayList<CommerceItem>();
			} else {
				for (CommerceItem commerceItem : commerceItems) {
					if (commerceItem != null && commerceItem.getSkuId() == skuId) {
						commerceItemToUpdate = commerceItem;
						break;
					}
				}
			}
			
			if(commerceItemToUpdate==null){
				
			}
			else{
				basicDAO.deleteEntity(commerceItemToUpdate);
				repriceOrderForItemChange(order);
				cart.setCommerceItems(order.getCommerceItems());
			}
			
		}
		
		//changes for order history ends
		
		//Gurucharan changes for view order starts 
		@Autowired
		EntityResponseGenerator entityResponseGenerator;

		public EntityResponseGenerator getEntityResponseGenerator() {
			return entityResponseGenerator;
		}

		public void setEntityResponseGenerator(
				EntityResponseGenerator entityResponseGenerator) {
			this.entityResponseGenerator = entityResponseGenerator;
		}
		
		//Gurucharan changes for view order starts 
		
}
