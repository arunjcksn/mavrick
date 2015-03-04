package com.mavrick.manager.pricing;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.mavrick.common.dao.BasicDAO;
import com.mavrick.persistance.entities.order.CommerceItem;
import com.mavrick.persistance.entities.order.Order;
import com.mavrick.persistance.entities.order.PriceInfo;

public class ItemPricingEngine implements PricingEngine{
	static Logger logger = Logger.getLogger(ItemPricingEngine.class);
	
	@Autowired
	BasicDAO basicDAO;

	PricingEngine nextEngine;
	
	public ItemPricingEngine() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int calculate(Map<String, Object> params) {
		int retValue = 1;
		PricingType type = null;
		Order currentOrder = null;
		double subTotal = 0D;
		PriceInfo orderSubTotal = null;
		logger.debug("item pricing engine started");
		if(params != null && params.size() > 1) {
			type = (PricingType)params.get("type");
			logger.debug("item pricing with type " + type);
			if (type == PricingType.ORDER_SUB_TOTAL ||  type == PricingType.ORDER_TOTAL) {
				logger.debug("Valid item pricing with type for item pricing engine");
				currentOrder = (Order) params.get("order");
				if (currentOrder != null && currentOrder.getCommerceItems() != null && currentOrder.getCommerceItems().size() > 0) {
					logger.debug("Valid item pricing with one or more items");
					List<CommerceItem> commerceItems = currentOrder.getCommerceItems();
					for (CommerceItem ci:commerceItems) {
						subTotal= subTotal+ci.getUnitPrice()*ci.getQty();
					}
					logger.debug("Item total = " + subTotal);
					Collection<PriceInfo> orderPriceInfos = currentOrder.getOrderPrice();
					
					for (PriceInfo orderPriceInfo:orderPriceInfos) {
						orderSubTotal = orderSubTotal== null ? ("subtotal".equals(orderPriceInfo.getType()) ? orderPriceInfo: null):orderSubTotal;			
					}
					if (orderSubTotal != null) {
						logger.debug("subtotal price object is found for this order " + currentOrder.getOrderId());
						orderSubTotal.setAmount(subTotal);
					} else {
						logger.debug("subtotal price object is not co creating a new one for this order " + currentOrder.getOrderId());
						orderSubTotal = new PriceInfo(); 
						orderSubTotal.setAdjustment(0);
						orderSubTotal.setAmount(subTotal);
						orderSubTotal.setType("subtotal");
						basicDAO.addEntity(orderSubTotal);
						currentOrder.getOrderPrice().add(orderSubTotal);
					}
					//basicDAO
				}
				
			}
		}
		// TODO Auto-generated method stub
		return (retValue > 0? getNextEngine().calculate(params):retValue);
	}

	@Override
	public void setNextEngine(PricingEngine pe) {
		this.nextEngine = pe;
		
	}

	@Override
	public PricingEngine getNextEngine() {
		return nextEngine;
	}

}
