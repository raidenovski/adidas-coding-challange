package com.adidas.demo.product.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductReview {
	
	@JsonIgnore
	private String productId;
	private float averageReviewScore;
	private int numberOfReviews;
}
