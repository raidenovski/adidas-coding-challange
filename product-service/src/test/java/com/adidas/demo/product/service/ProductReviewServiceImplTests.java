package com.adidas.demo.product.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.adidas.demo.product.client.impl.ProductClientImpl;
import com.adidas.demo.product.dto.Product;
import com.adidas.demo.product.service.impl.ProductServiceImpl;
import com.adidas.demo.product.utils.TestUtilsFactory;

@RunWith(MockitoJUnitRunner.class)
public class ProductReviewServiceImplTests {

	private ProductServiceImpl testClass;
	@Mock
	private ProductClientImpl client;
	
	@Before
	public void setup() {
		this.testClass = new ProductServiceImpl(client);
		ReflectionTestUtils.setField(testClass, "productApiUrlTemplate", "/product/{id}", String.class);
	}
	
	@Test
	public void givenValidProductid_whenGettingProductAggregate_thenCallServicesAndGetObjects() {
		Product expected = TestUtilsFactory.getProduct();
		given(client.getResourceFromUrl(anyString(), anyString())).willReturn(expected);
		
		Product actual = testClass.get(expected.getId());
		
		assertThat(actual.getModelNumber()).isEqualTo(expected.getModelNumber());
	}
}
