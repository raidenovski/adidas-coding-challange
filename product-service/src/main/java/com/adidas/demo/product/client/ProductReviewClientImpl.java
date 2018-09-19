package com.adidas.demo.product.client;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.adidas.demo.product.dto.ProductReview;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ProductReviewClientImpl implements ProductClient<ProductReview> {

	private RestTemplate restTemplate;
	
	public ProductReviewClientImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	@Override
	public ProductReview getResourceFromUrl(String urlTemplate, String id) {
		try {
			ResponseEntity<ProductReview> response = restTemplate.exchange(urlTemplate, HttpMethod.GET, null, ProductReview.class, id);
			return response.getBody();
		} catch (HttpStatusCodeException hsce) {
			log.error("Response {} : {} for id {}", hsce.getStatusCode(), hsce.getResponseBodyAsString(), id);
			return null; // TODO throw custom exception
		}
	}

}
