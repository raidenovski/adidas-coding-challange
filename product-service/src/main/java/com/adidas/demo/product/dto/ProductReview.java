package com.adidas.demo.product.dto;

import lombok.Data;

@Data
public class ProductReview {
	private String productId;
	private float averageReviewScore;
	private int numberOfReviews;
}
