package com.adidas.demo.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ProductReview {
	private String productId;
	private float averageReviewScore;
	private int numberOfReviews;
}
