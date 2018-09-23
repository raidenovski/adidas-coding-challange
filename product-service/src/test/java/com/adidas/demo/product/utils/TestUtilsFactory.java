package com.adidas.demo.product.utils;

import com.adidas.demo.product.dto.Product;
import com.adidas.demo.product.dto.ProductAggregate;
import com.adidas.demo.product.dto.ProductReview;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtilsFactory {

	private static final String PRODUCT_ID= "C77154";
	private static final String MODEL_NUM = "JOA83";
	private static final String PRODUCT_TYPE = "inline";
	private static final int NUM_OF_REVIEWS = 3;
	private static final float AVERAGE_REVIEW_SCORE = 12.2f;
	
	private TestUtilsFactory() {}
	
	public static ProductAggregate getProductAggregate() {
		return ProductAggregate.builder()
				.product(getProduct())
				.productReview(getProductReview())
				.build();
	}
	
	public static ProductReview getProductReview() {
		return ProductReview.builder()
				.productId(PRODUCT_ID)
				.averageReviewScore(AVERAGE_REVIEW_SCORE)
				.numberOfReviews(NUM_OF_REVIEWS).build();
	}
	
	public static String getProductReviewJson() throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(getProductReview());
	}

	public static Product getProduct() {
		return Product.builder()
				.id(PRODUCT_ID)
				.modelNumber(MODEL_NUM)
				.productType(PRODUCT_TYPE)
				.metaData(null)
				.build();
	}
	
	public static String getProductJson() throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(getProduct());
	}
	
}
