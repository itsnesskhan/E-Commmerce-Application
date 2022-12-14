package com.ecom.services.impl;

import static com.ecom.utills.Constants.SHORT_DIRECTION_ASC;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.ecom.dtos.ProductDto;
import com.ecom.helper.ProductRespone;
import com.ecom.model.Product;
import com.ecom.repository.ProductRepository;
import com.ecom.services.ProductService;
import com.ecom.utills.Constants;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ProductDto addProduct(ProductDto productDto) {
		Product product = modelMapper.map(productDto, Product.class);
		product = productRepository.save(product);
		return modelMapper.map(product, productDto.getClass());
	}

	@Override
	public ProductRespone getProducts(int pageNumber, int pageSize, String shortBy, String shortDirection) {
		Sort sort = (shortDirection.equalsIgnoreCase(SHORT_DIRECTION_ASC)) ? Sort.by(shortBy).ascending()
				: Sort.by(shortBy).descending();

		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

		Page<Product> findAll = productRepository.findAll(pageable);
		List<Product> allProducts = findAll.getContent();
		List<ProductDto> list = allProducts.stream().map(item -> modelMapper.map(item, ProductDto.class))
				.collect(Collectors.toList());

		ProductRespone productRespone = new ProductRespone();
		productRespone.setProducts(list);
		productRespone.setPageNumber(findAll.getNumber());
		productRespone.setPageSize(findAll.getSize());
		productRespone.setTotalPage(findAll.getTotalPages());
		productRespone.setTotalProducts(findAll.getTotalElements());
		productRespone.setLastPage(findAll.isLast());
		return productRespone;
	}

	@Override
	public List<ProductDto> searchProduct(String key) {
		List<Product> products = productRepository.findByNameContaining(key);
		return products.stream().map(pro -> modelMapper.map(pro, ProductDto.class)).collect(Collectors.toList());
	}

	@Override
	public ProductDto updateProduct(Integer id, ProductDto productDto) {
		Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

		product.setName(productDto.getName());
		product.setPrice(productDto.getPrice());
		product.setDescription(productDto.getDescription());
		product.setQuantity(productDto.getQuantity());

		product = productRepository.save(product);
		return modelMapper.map(product, productDto.getClass());
	}

	@Override
	public ProductDto getProductById(Integer id) {
		Product product = productRepository.findById(id).orElseThrow(()-> new RuntimeException("No product found"));
		return modelMapper.map(product, ProductDto.class);
	}

}
