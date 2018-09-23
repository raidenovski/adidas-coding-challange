package com.adidas.demo.product.service.impl;

import org.springframework.stereotype.Service;

import com.adidas.demo.product.dto.Product;
import com.adidas.demo.product.dto.ProductAggregate;
import com.adidas.demo.product.dto.ProductReview;
import com.adidas.demo.product.exception.AggregateComponentNotFoundException;
import com.adidas.demo.product.exception.PartialAggregateException;
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
	public ProductAggregate get(String id) throws AggregateComponentNotFoundException {
		ProductReview productReview = null;
		
		Product product = productService.get(id);
		try {
			productReview = productReviewService.get(id);
		} catch (AggregateComponentNotFoundException acne) {
			log.error("Could not get product review for product with id {}", id);
			throw new PartialAggregateException("No reviews are available for this product at the moment", product);
		}
	
		return ProductAggregate.builder()
				.product(product)
				.productReview(productReview)
				.build();
	}
}
