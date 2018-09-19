package com.adidas.demo.productreview.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.persistence.EntityNotFoundException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.adidas.demo.productreview.service.ProductReviewService;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductReviewController.class)
public class ProductReviewExceptionHandlerTests {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private ProductReviewService service;
	
	@Test
	public void givenInvalidProductId_whenHandlingRequest_thenReturnErrorMessage() throws Exception {
		given(service.get(anyString())).willThrow(new EntityNotFoundException());
		
		mockMvc.perform(get("/review/C123"))
		.andExpect(status().isNotFound());
	}
}
