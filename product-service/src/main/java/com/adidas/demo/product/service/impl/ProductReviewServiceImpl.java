package com.adidas.demo.product.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.adidas.demo.product.client.ProductClient;
import com.adidas.demo.product.client.impl.ProductReviewClientImpl;
import com.adidas.demo.product.dto.ProductReview;
import com.adidas.demo.product.service.ProductService;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class ProductReviewServiceImpl implements ProductService<ProductReview> {

	@Value("${api.product-review.url}")
	private String productReviewApiUrlTemplate;
	private ProductClient<ProductReview> client;
	
	public ProductReviewServiceImpl(ProductReviewClientImpl client) {
		this.client = client;
	}
	
	@Override
	public ProductReview get(String id) {
		log.info("Get ProductReview with id {}", id);
		return client.getResourceFromUrl(productReviewApiUrlTemplate, id);
	}

}
