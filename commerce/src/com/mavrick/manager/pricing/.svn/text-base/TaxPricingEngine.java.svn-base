package com.mavrick.manager.pricing;

import java.util.Collection;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.mavrick.common.dao.BasicDAO;
import com.mavrick.persistance.entities.order.Order;
import com.mavrick.persistance.entities.order.PriceInfo;

public class TaxPricingEngine implements PricingEngine {
	Logger logger = Logger.getLogger(TaxPricingEngine.class);
	@Autowired
	BasicDAO basicDAO;

	PricingEngine nextEngine;

	public TaxPricingEngine() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int calculate(Map<String, Object> params) {
		int retValue = 1;
		PricingType type = null;
		Order currentOrder = null;
		PriceInfo orderSubTotal = null;
		PriceInfo orderTaxTotal = null;
		logger.debug("Tax pricing engine started");
		if (params != null && params.size() > 1) {
			type = (PricingType) params.get("type");
			logger.debug("Tax pricing with type " + type);
			if (type == PricingType.ORDER_SUB_TOTAL
					|| type == PricingType.ORDER_TOTAL) {
				logger.debug("Valid Tax pricing with type for item pricing engine");
				currentOrder = (Order) params.get("order");
				if (currentOrder != null
						&& currentOrder.getCommerceItems() != null
						&& currentOrder.getCommerceItems().size() > 0) {
					logger.debug("Valid item pricing with one or more items");
					Collection<PriceInfo> orderPriceInfos = currentOrder
							.getOrderPrice();

					for (PriceInfo orderPriceInfo : orderPriceInfos) {
						orderSubTotal = orderSubTotal == null ? ("subtotal"
								.equals(orderPriceInfo.getType()) ? orderPriceInfo
								: null)
								: orderSubTotal;
						orderTaxTotal = orderTaxTotal == null ? ("taxtotal"
								.equals(orderPriceInfo.getType()) ? orderPriceInfo
								: null)
								: orderTaxTotal;
					}
					if (orderSubTotal != null) {
						logger.debug("Tax price object is not co creating a new one for this order "
								+ currentOrder.getOrderId());
						if (orderTaxTotal == null) {
							orderTaxTotal = new PriceInfo();
							orderTaxTotal.setType("taxtotal");
							orderTaxTotal.setAdjustment(0);
							orderTaxTotal
									.setAmount(orderSubTotal.getAmount() * 0.1);
							basicDAO.addEntity(orderTaxTotal);
							currentOrder.getOrderPrice().add(orderTaxTotal);
						} else {

							orderTaxTotal
									.setAmount(orderSubTotal.getAmount() * 0.1);
						}

					}
				}

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
