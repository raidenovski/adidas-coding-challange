package com.adidas.demo.productreview.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.adidas.demo.productreview.entity.ProductReview;
import com.adidas.demo.productreview.service.ProductReviewService;
import com.adidas.demo.productreview.utils.TestUtilsFactory;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductReviewController.class)
public class ProductReviewControllerTests {

	private static final String USER = "adidas";
	private static final String PASS = "rocks";
	@Autowired
	private WebApplicationContext context;
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private ProductReviewService service;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(springSecurity())
				.build();
	}
	
	@Test
	public void givenValidProductId_whenGetEndpointIsCalled_thenReturnProductReview() throws Exception {
		ProductReview expected = TestUtilsFactory.getProductReview();
		given(service.get(anyString())).willReturn(expected);
		 
		mockMvc.perform(get("/review/" + expected.getProductId())
		.with(user(USER).password(PASS)))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.productId").value(expected.getProductId()))
		.andExpect(jsonPath("$.averageReviewScore").value(expected.getAverageReviewScore()));
	}
	
	@Test
	public void givenValidProductReview_whenCreateEndpointIsCalled_thenCallServiceAndReturnSavedProductReview() throws Exception {
		ArgumentCaptor<ProductReview> captor = ArgumentCaptor.forClass(ProductReview.class);
		String expected = TestUtilsFactory.getProductReviewJson();
		
		mockMvc.perform(post("/review/")
				.with(user(USER).password(PASS))
				.with(csrf())
				.content(expected)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated());
		
		verify(service).save(captor.capture());
		assertThat(expected).contains(captor.getValue().getProductId());
	}
	
	@Test
	public void givenValidProductReview_whenUpdateEndpointIsCalled_thenCallServiceWithValidParameters() throws Exception {
		ProductReview expected = TestUtilsFactory.getProductReview();
		String json = TestUtilsFactory.getProductReviewJson();
		
		mockMvc.perform(put("/review/" + expected.getProductId())
				.with(user(USER).password(PASS))
				.with(csrf())
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNoContent());
		
		verify(service).update(expected.getProductId(), expected);
	}
	
	@Test
	public void givenValidProductReview_whenDeleteEndpointIsCalled_thenCallServiceWithValidParameters() throws Exception {
		ProductReview expected = TestUtilsFactory.getProductReview();
		String json = TestUtilsFactory.getProductReviewJson();
		
		mockMvc.perform(delete("/review/" + expected.getProductId())
				.with(user(USER).password(PASS))
				.with(csrf())
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNoContent());
		
		verify(service).delete(expected.getProductId());
	}
	
	@Test
	public void givenUnauthenticatedRequest_whenGetEndpointIsCalled_thenReturn401() throws Exception {
		mockMvc.perform(get("/review/C12345"))
		.andExpect(status().isUnauthorized());
	}
	
	@Test
	public void givenInvalidCsrfToken_whenCreateEndpointIsCalled_thenReturn403() throws Exception {
		String json = TestUtilsFactory.getProductReviewJson();
		
		mockMvc.perform(post("/review/")
				.with(user(USER).password(PASS))
				.with(csrf().useInvalidToken())
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isForbidden());
	}
}
