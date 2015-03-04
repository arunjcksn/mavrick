package com.mavrick.common.session;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.mavrick.persistance.entities.order.CommerceItem;
import com.mavrick.persistance.entities.order.Order;

@Component
@Scope(value="session",proxyMode=ScopedProxyMode.TARGET_CLASS)
public class Cart implements SessionObject{
private Order order;


List<CommerceItem> commerceItems;


public Order getOrder() {
	return order;
}


public void setOrder(Order order) {
	this.order = order;
}


public List<CommerceItem> getCommerceItems() {
	return commerceItems;
}


public void setCommerceItems(List<CommerceItem> commerceItems) {
	this.commerceItems = commerceItems;
}
}
