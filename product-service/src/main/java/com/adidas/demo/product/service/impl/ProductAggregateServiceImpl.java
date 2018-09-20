package com.adidas.demo.product.service.impl;

import org.springframework.stereotype.Service;

import com.adidas.demo.product.dto.Product;
import com.adidas.demo.product.dto.ProductAggregate;
import com.adidas.demo.product.dto.ProductReview;
import com.adidas.demo.product.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductAggregateServiceImpl implements ProductService<ProductAggregate> {
	
	private ProductService<Product> productService;
	private ProductService<ProductReview> productReviewService;
	
	public ProductAggregateServiceImpl(ProductServiceImpl productService, 
			ProductReviewServiceImpl productReviewService) {
		this.productService = productService;
		this.productReviewService = productReviewService;
	}
	
	@Override
	public ProductAggregate get(String id) {
		ProductReview productReview = productReviewService.get(id);
		Product product = productService.get(id);
	
		return ProductAggregate.builder()
				.product(product)
				.productReview(productReview)
				.build();
	}
}
