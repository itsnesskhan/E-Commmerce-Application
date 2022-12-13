package com.ecom.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

	private Integer pid;
	
	private String name;
	
	private long price;
	
	private String description;
	
	private long quantity;
	
}
