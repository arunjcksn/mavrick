package com.mavrick.manager.pricing;

import java.util.Collection;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.mavrick.common.dao.BasicDAO;
import com.mavrick.persistance.entities.order.Order;
import com.mavrick.persistance.entities.order.PriceInfo;

public class OrderPricingEngine implements PricingEngine {

	Logger logger = Logger.getLogger(OrderPricingEngine.class);
	@Autowired
	BasicDAO basicDAO;

	PricingEngine nextEngine;

	public OrderPricingEngine() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int calculate(Map<String, Object> params) {
		int retValue = 0;
		PricingType type = null;
		Order currentOrder = null;
		double subTotal = 0D;
		double taxTotal = 0D;
		double shipTotal = 0D;
		PriceInfo orderSubTotal = null;
		PriceInfo orderShipTotal = null;
		PriceInfo orderTaxTotal = null;
		PriceInfo orderTotal = null;
		logger.debug("OrderPricingEngine started");
		if (params != null && params.size() > 1) {
			type = (PricingType) params.get("type");
			logger.debug("Order pricing with type " + type);
			if (type == PricingType.ORDER_SUB_TOTAL
					|| type == PricingType.ORDER_TOTAL) {
				logger.debug("Valid Order pricing with type for Shipping pricing engine");
				currentOrder = (Order) params.get("order");
				if (currentOrder != null
						&& currentOrder.getCommerceItems() != null
						&& currentOrder.getCommerceItems().size() > 0) {
					logger.debug("Valid Shipping pricing with one or more items");
					Collection<PriceInfo> orderPriceInfos = currentOrder
							.getOrderPrice();

					for (PriceInfo orderPriceInfo : orderPriceInfos) {
						orderSubTotal = orderSubTotal == null ? ("subtotal"
								.equals(orderPriceInfo.getType()) ? orderPriceInfo
								: null)
								: orderSubTotal;
						orderShipTotal = orderShipTotal == null ? ("shiptotal"
								.equals(orderPriceInfo.getType()) ? orderPriceInfo
								: null)
								: orderShipTotal;
						orderTaxTotal = orderTaxTotal == null ? ("taxtotal"
								.equals(orderPriceInfo.getType()) ? orderPriceInfo
								: null)
								: orderTaxTotal;
						orderTotal = orderTotal == null ? ("ordertotal"
								.equals(orderPriceInfo.getType()) ? orderPriceInfo
								: null)
								: orderTotal;
					}
					if (orderSubTotal != null) {
						subTotal = orderSubTotal.getAmount();
					}
					if (orderShipTotal != null) {
						shipTotal = orderShipTotal.getAmount();
					}
					if (orderTaxTotal != null) {
						taxTotal = orderTaxTotal.getAmount();
					}
					if (orderTotal == null) {
						orderTotal = new PriceInfo();
						orderTotal.setType("ordertotal");
						orderTotal.setAdjustment(0);
						orderTotal.setAmount(subTotal + taxTotal + shipTotal);
						basicDAO.addEntity(orderTotal);
						currentOrder.getOrderPrice().add(orderTotal);
					} else {

					orderTotal.setAmount(subTotal + taxTotal + shipTotal);
					}

					
				}
				retValue = 1;
			}
		}
		return retValue;
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
