package com.adidas.demo.productreview.dto;



import java.util.List;

import com.adidas.demo.productreview.entity.ProductReview;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class ProductReviewData {

	List<ProductReview> data;
}
