package com.adidas.demo.product.client;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.adidas.demo.product.dto.Product;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ProductClientImpl implements ProductClient<Product> {

	private RestTemplate restTemplate;
	
	public ProductClientImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	@Override
	public Product getResourceFromUrl(String urlTemplate, String id) {
		try {
			ResponseEntity<Product> response = restTemplate.exchange(urlTemplate, HttpMethod.GET, null, Product.class, id);
			return response.getBody();
		} catch (HttpStatusCodeException hsce) {
			log.error("Response {} : {} for id {}", hsce.getStatusCode(), hsce.getResponseBodyAsString(), id);
			return null; // TODO throw custom exception
		}
	}

}
