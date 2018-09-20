package com.adidas.demo.product.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.adidas.demo.product.dto.ProductAggregate;
import com.adidas.demo.product.service.impl.ProductAggregateServiceImpl;
import com.adidas.demo.product.utils.TestUtilsFactory;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductAggregateController.class)
public class ProductAggregateControllerTests {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private ProductAggregateServiceImpl service;

	@Test
	public void givenValidProductId_whenGetEndpointIsCalled_thenCallServiceAndReturnProductAggregate() throws Exception {
		ProductAggregate expected = TestUtilsFactory.getProductAggregate();
		
		given(service.get(anyString())).willReturn(expected);
		
		mockMvc.perform(get("/product/" + expected.getProduct().getId()))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.product").exists())
		.andExpect(jsonPath("$.productReview.productId").exists());
	}
}
