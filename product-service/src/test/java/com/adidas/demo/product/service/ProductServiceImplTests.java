package com.adidas.demo.product.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.adidas.demo.product.client.impl.ProductReviewClientImpl;
import com.adidas.demo.product.dto.ProductReview;
import com.adidas.demo.product.service.impl.ProductReviewServiceImpl;
import com.adidas.demo.product.utils.TestUtilsFactory;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTests {

	private ProductReviewServiceImpl testClass;
	@Mock
	private ProductReviewClientImpl client;
	
	@Before
	public void setup() {
		this.testClass = new ProductReviewServiceImpl(client);
		ReflectionTestUtils.setField(testClass, "productReviewApiUrlTemplate", "/product/{id}", String.class);
	}
	
	@Test
	public void givenValidProductid_whenGettingProductAggregate_thenCallServicesAndGetObjects() {
		ProductReview expected = TestUtilsFactory.getProductReview();
		when(client.getResourceFromUrl(anyString(), anyString())).thenReturn(expected);
		
		ProductReview actual = testClass.get(expected.getProductId());
		
		assertThat(actual.getAverageReviewScore()).isEqualTo(expected.getAverageReviewScore());
	}
}
