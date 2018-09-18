package com.adidas.demo.productreview.utils;

import com.adidas.demo.productreview.entity.ProductReview;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtilsFactory {

	private TestUtilsFactory() {}
	
	public static ProductReview getProductReview() {
		return ProductReview.builder()
				.productId("C77154")
				.averageReviewScore(12.2f)
				.numberOfReviews(3).build();
	}

	public static String getProductReviewJson() throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(getProductReview());
	}
}
