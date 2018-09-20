package com.adidas.demo.productreview.dto;

import com.adidas.demo.productreview.entity.ProductReview;

import lombok.Value;

@Value
public class ProductReviewDto {
	private String productId;
	private float averageReviewScore;
	private int numberOfReviews;
	
	public ProductReviewDto(ProductReview productReview) {
		this.productId = productReview.getProductId();
		this.averageReviewScore = productReview.getAverageReviewScore();
		this.numberOfReviews = productReview.getNumberOfReviews();
	}
}
