package com.adidas.demo.productreview.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.adidas.demo.productreview.entity.ProductReview;
import com.adidas.demo.productreview.repository.ProductReviewRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductReviewService {

	private ProductReviewRepository repository;

	public ProductReviewService(ProductReviewRepository repository) {
		this.repository = repository;
	}
	
	@Cacheable("productReviews")
	public ProductReview get(String productId) {
		return repository.findById(productId).orElseThrow(() -> new EntityNotFoundException(productId));
	}

	public ProductReview save(ProductReview productReview) {
		return repository.save(productReview);
	}

	@CachePut("productReviews")
	public ProductReview update(String productId, ProductReview update) {
		ProductReview productReview = repository.findById(productId).orElseThrow(() -> new EntityNotFoundException(productId));
		productReview.setNumberOfReviews(update.getNumberOfReviews());
		productReview.setAverageReviewScore(update.getAverageReviewScore()); 
		return productReview;
	}

	@CacheEvict(value = "productReviews", key = "#productReview.productId")
	public void delete(String productId) {
		repository.deleteById(productId);
	}

}
