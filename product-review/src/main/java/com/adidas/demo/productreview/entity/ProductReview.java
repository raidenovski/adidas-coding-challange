package com.adidas.demo.productreview.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class ProductReview {
	@Id
	private String productId;
	private float averageReviewScore;
	private int numberOfReviews;
}
