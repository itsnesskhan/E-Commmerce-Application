package com.ecom.dtos;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItemPkDto implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	private OrderDto order;
	
	private ProductDto product;
	
	
	
	
	
}
