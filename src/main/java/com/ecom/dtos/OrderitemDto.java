package com.ecom.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class OrderitemDto {
	
	private OrderItemPkDto pk;
		
	private long quantity;

	public OrderitemDto(OrderItemPkDto pk, long quantity) {
	
		this.pk = pk;
		this.quantity = quantity;
	}
	
	
	

}
