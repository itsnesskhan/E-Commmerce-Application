package com.ecom.controller;

import static com.ecom.utills.Constants.DEFAULT_PAGE_NUMBER;
import static com.ecom.utills.Constants.DEFAULT_PAGE_SIZE;
import static com.ecom.utills.Constants.SHORT_DIRECTION_ASC;
import static com.ecom.utills.Constants.SORT_BY_ID;
import static com.ecom.utills.Messages.ALL_PRODUCTS_MSG;
import static com.ecom.utills.Messages.N0_MATCH_FOUND;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.dtos.ProductDto;
import com.ecom.helper.ProductRespone;
import com.ecom.response.ResponseHandler;
import com.ecom.services.ProductService;
import com.ecom.utills.Messages;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping
	public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto){
		ProductDto product = productService.addProduct(productDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(product);
	}
	
	@GetMapping
	public ResponseEntity<Object> getProducts(
			@RequestParam(name = "pageNumber", defaultValue = DEFAULT_PAGE_NUMBER, required = true) Integer pageNumber,
			@RequestParam(name = "pageSize", defaultValue = DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(name = "sortBy", defaultValue = SORT_BY_ID, required = false) String name,
			@RequestParam(name = "direction", defaultValue = SHORT_DIRECTION_ASC, required = false) String direction){
		ProductRespone productRespone = productService.getProducts(pageNumber,pageSize,name, direction);
		return ResponseHandler.responseBuilder(ALL_PRODUCTS_MSG, HttpStatus.OK, productRespone);
	}
	
	@GetMapping("/search/{key}")
	public ResponseEntity<Object> searchProduct(@PathVariable String key){
		List<ProductDto> searchProduct = productService.searchProduct(key);
		if (!searchProduct.isEmpty()) {
			return ResponseHandler.responseBuilder(Messages.ALL_PRODUCTS_MSG, HttpStatus.OK, searchProduct);			
		}
		
		return ResponseHandler.responseBuilder(N0_MATCH_FOUND, HttpStatus.NOT_FOUND, searchProduct);
	}
}
