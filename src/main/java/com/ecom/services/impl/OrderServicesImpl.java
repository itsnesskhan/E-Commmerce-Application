package com.ecom.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.dtos.OrderDto;
import com.ecom.model.Order;
import com.ecom.model.OrderItem;
import com.ecom.repository.OrderItemRepository;
import com.ecom.repository.OrderRepository;
import com.ecom.services.OrderServices;
import com.ecom.utills.Constants;
import com.ecom.utills.DateUtils;

@Service
@Transactional
public class OrderServicesImpl<T> implements OrderServices<T> {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private ModelMapper modelMapper;

	private static final Logger log = LoggerFactory.getLogger(OrderServicesImpl.class);

	@Override
	public T AddOrder(OrderDto orderDto) {
		log.info("OrderServicesImpl::addOrder == START");
		Order order = modelMapper.map(orderDto, Order.class);

		log.info("OrderServicesImpl::addOrder::order = {}", order);

		log.info("OrderServicesImpl::addOrder::saving order");
		order.setCreatedAt(DateUtils.getTimeStamp());
		order.setStatus(Constants.ORDER_STATUS_PENDING);
		order = orderRepository.save(order);

		log.info("OrderServicesImpl::addOrder::setting order id to orderItemspk");
		List<OrderItem> orderItems = order.getOrderItems();
		for (OrderItem orderItem : orderItems) {
			log.info("OrderServicesImpl::addOrder::setting orderId to orderItems");
			orderItem.getPk().setOrder(order);

			log.info("OrderServicesImpl::addOrder::saving orderItems");
			orderItem = orderItemRepository.save(orderItem);

//			order object already contains address item that's why thrwoing error when we try to add orderitmes
//			log.info("OrderServicesImpl::addOrder::adding orderItems to order");
//			order.getOrderItems().add(orderItem);
		}

		log.info("OrderServicesImpl::addOrder::saving orders again");
		order = orderRepository.save(order);

		log.info("OrderServicesImpl::addOrder::order after getting saved = {}", order);

		log.info("OrderServicesImpl::addOrder == END");

		return (T) modelMapper.map(order, OrderDto.class);
	}

	@Override

	public T getOrderBYId(Integer oid) {
		Order order = orderRepository.findById(oid).orElseThrow(() -> new RuntimeException("Order not found"));
		return (T) modelMapper.map(order, OrderDto.class);
	}

	@Override
	public T updateOrder(Integer oid, OrderDto orderDto) {
		log.info("OrderServicesImpl::updateOrder == START");

		log.info("OrderServicesImpl::updateOrder::geting order with id = {}", oid);
		Order order = orderRepository.findById(oid)
				.orElseThrow(() -> new RuntimeException("No                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   order found with Order_id " + oid));
		log.info("OrderServicesImpl::updateOrder::order return by id = {}", order);

		order.setStatus(orderDto.getStatus());
		order = orderRepository.save(order);

		log.info("OrderServicesImpl::updateOrder::order after getting saved = {}", order);

		log.info("OrderServicesImpl::updateOrder == END");

		return (T) modelMapper.map(order, orderDto.getClass());
	}

	@Override
	public T deleteOrder(Integer oid) {
		log.info("OrderServicesImpl::deleteOrder == START");

		log.info("OrderServicesImpl::deleteOrder::fetching order with oid = {}", oid);
		Order order = orderRepository.findById(oid)
				.orElseThrow(() -> new RuntimeException("No order found with id= {}" + oid));

		log.info("OrderServicesImpl::deleteOrder::order with id = {}", order);
		log.info("OrderServicesImpl::deleteOrder::deleting orderItems with this order");
		for (OrderItem item : order.getOrderItems()) {
			orderItemRepository.delete(item);
		}
		
		log.info("OrderServicesImpl::deleteOrder::deleting order with id = {}",oid);
		orderRepository.delete(order);

		log.info("OrderServicesImpl::deleteOrder == END");

		return null;
	}

}
