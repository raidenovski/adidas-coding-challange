package com.adidas.demo.product.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.adidas.demo.product.dto.Product;
import com.adidas.demo.product.dto.ProductAggregate;
import com.adidas.demo.product.dto.ProductReview;
import com.adidas.demo.product.service.impl.ProductAggregateServiceImpl;
import com.adidas.demo.product.service.impl.ProductReviewServiceImpl;
import com.adidas.demo.product.service.impl.ProductServiceImpl;
import com.adidas.demo.product.utils.TestUtilsFactory;

@RunWith(MockitoJUnitRunner.class)
public class ProductAggregateServiceImplTests {

	private ProductAggregateServiceImpl testClass;
	@Mock
	private ProductServiceImpl productService;
	@Mock
	private ProductReviewServiceImpl productReviewService;
	
	@Before
	public void setup() {
		this.testClass = new ProductAggregateServiceImpl(productService, productReviewService);
	}
	
	@Test
	public void givenValidProductid_whenGettingProductAggregate_thenCallServicesAndGetObjects() {
		Product product = TestUtilsFactory.getProduct();
		ProductReview productReview = TestUtilsFactory.getProductReview();
		given(productService.get(anyString())).willReturn(product);
		given(productReviewService.get(anyString())).willReturn(productReview);
		
		ProductAggregate actual = testClass.get(product.getId());
		
		assertThat(actual.getProduct().getModelNumber()).isEqualTo(product.getModelNumber());
		assertThat(actual.getProductReview().getAverageReviewScore()).isEqualTo(productReview.getAverageReviewScore());
	}
}
