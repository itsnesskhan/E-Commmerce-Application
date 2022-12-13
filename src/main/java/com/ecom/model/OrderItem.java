package com.ecom.model;

import java.beans.Transient;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class OrderItem {
	
	@EmbeddedId
	@JsonIgnore
	private OrderItemPK pk;
	
	private Integer quantity;
	
	@Transient
	public Product getProduct() {
		return pk.getProduct();
	}
	
	public OrderItem(Order order, Product product, Integer quantity) {
		OrderItemPK pk = new OrderItemPK(order, product);
		this.pk = pk;
		this.quantity = quantity;
	}
	
	
	
	
	
	@Transient
	public Double getTotalPrice() {
		return (double) (getProduct().getPrice() * quantity);
	}	

}
