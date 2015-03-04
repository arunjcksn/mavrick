package com.mavrick.manager.pricing;

import java.util.Collection;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.mavrick.common.dao.BasicDAO;
import com.mavrick.persistance.entities.order.Order;
import com.mavrick.persistance.entities.order.PriceInfo;

public class ShippingEngine implements PricingEngine {
	Logger logger = Logger.getLogger(ShippingEngine.class);
	@Autowired
	BasicDAO basicDAO;

	PricingEngine nextEngine;

	public ShippingEngine() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int calculate(Map<String, Object> params) {
		int retValue = 0;
		PricingType type = null;
		Order currentOrder = null;
		double subTotal = 0D;
		PriceInfo orderSubTotal = null;
		PriceInfo orderShipTotal = null;
		logger.debug("Shipping pricing engine started");
		if (params != null && params.size() > 1) {
			type = (PricingType) params.get("type");
			logger.debug("Shipping pricing with type " + type);
			if (type == PricingType.ORDER_SUB_TOTAL
					|| type == PricingType.ORDER_TOTAL) {
				logger.debug("Valid item pricing with type for Shipping pricing engine");
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
					}
					if (orderSubTotal != null) {

						logger.debug("Shipping price object is not co creating a new one for this order "
								+ currentOrder.getOrderId());
						if (orderShipTotal == null) {
							orderShipTotal = new PriceInfo();
							orderShipTotal.setType("shiptotal");
							orderShipTotal.setAdjustment(0);
							orderShipTotal
									.setAmount(orderSubTotal.getAmount() * 0.05);
							basicDAO.addEntity(orderShipTotal);
							currentOrder.getOrderPrice().add(orderShipTotal);
						} else {
							orderShipTotal
									.setAmount(orderSubTotal.getAmount() * 0.05);
						}

					}
				}
				retValue = 1;
			}
		}
		// TODO Auto-generated method stub
		return (retValue > 0 ? getNextEngine().calculate(params) : retValue);
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
