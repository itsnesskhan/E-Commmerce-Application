package com.ecom.services;

import com.ecom.dtos.OrderDto;

public interface OrderServices<T> {

	T AddOrder(OrderDto orderDto);
	
	T getOrderBYId(Integer oid);
	
	T updateOrder(Integer oid, OrderDto orderDto);
	
	T deleteOrder(Integer oid);
}
