package com.ecom.controller;

import static com.ecom.utills.Messages.ORDER_CREATED;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.dtos.OrderDto;
import com.ecom.response.ResponseHandler;
import com.ecom.services.OrderServices;
import com.ecom.utills.Messages;

@RestController
@RequestMapping("/order")
public class OrderController<T> {

	@Autowired
	private OrderServices<T> orderServices;
	
	@PostMapping()
	public ResponseEntity<?> addOrder(@RequestBody OrderDto orderDto){
		T addOrder = orderServices.AddOrder(orderDto);
		return ResponseHandler.responseBuilder(ORDER_CREATED, HttpStatus.CREATED, addOrder);
		
	}
	
	
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getOrder(@PathVariable Integer id){
		T order = orderServices.getOrderBYId(id);
		return ResponseHandler.responseBuilder(Messages.ORDER_DETAILS, HttpStatus.OK, order);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateOrder(@PathVariable Integer id ,@RequestBody OrderDto orderDto){
		T addOrder = orderServices.updateOrder(id,orderDto);
		return ResponseEntity.ok(addOrder);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteOrder(@PathVariable Integer id){
		orderServices.deleteOrder(id);
		return ResponseEntity.ok(Map.entry("Order Deleted Succesfully!", HttpStatus.OK));
	}
}
