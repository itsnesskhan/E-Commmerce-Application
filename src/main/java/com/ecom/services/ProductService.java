package com.ecom.services;

import java.util.List;

import com.ecom.dtos.ProductDto;
import com.ecom.helper.ProductRespone;

public interface ProductService {

	ProductDto addProduct(ProductDto productDto);
	
	ProductRespone getProducts(int pageNumber, int pageSize, String shortBy, String shortDirection);
	
	List<ProductDto> searchProduct(String key);
}
