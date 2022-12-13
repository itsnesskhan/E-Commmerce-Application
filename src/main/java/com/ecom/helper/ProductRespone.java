package com.ecom.helper;

import java.util.List;

import com.ecom.dtos.ProductDto;

import lombok.Data;

@Data
public class ProductRespone {

	private List<ProductDto> products;
	
	private int pageNumber;
	
	private int pageSize;
	
	private long totalProducts;
	
	private int totalPage;
	
	private boolean lastPage;
}
