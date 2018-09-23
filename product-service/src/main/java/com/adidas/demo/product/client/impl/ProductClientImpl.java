package com.adidas.demo.product.client.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.adidas.demo.product.client.ProductClient;
import com.adidas.demo.product.dto.Product;
import com.adidas.demo.product.exception.AggregateComponentNotFoundException;
import com.adidas.demo.product.exception.AggregateComponentNotFoundException.AggregateComponent;
import com.adidas.demo.product.utils.JsonUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ProductClientImpl implements ProductClient<Product> {

    @Value("classpath:product.json")
    private Resource productJson;
    
	private RestTemplate restTemplate;
	
	public ProductClientImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	 
	/* 
	 * TODO Issue - Rest call to `https://www.adidas.co.uk/api/products/{id}` doesn't get response and hangs indefinitely. 
	 * Haven't managed to fix it so the response was stubbed from a json stored on the classpath. 
	 */
	@Override
	public Product getResourceFromUrl(String urlTemplate, String id) {
		try {
			// ResponseEntity<Product> response = restTemplate.exchange(urlTemplate, HttpMethod.GET, null, Product.class, id);
			// log.info("Response: {} - {}", response.getStatusCode(), response.getBody());
			// return response.getBody();
			return stubbedResponse();
		} catch (HttpStatusCodeException hsce) {
			log.error("Response {} : {} for id {}", hsce.getStatusCode(), hsce.getResponseBodyAsString(), id);
			throw new AggregateComponentNotFoundException(hsce.getMessage(), AggregateComponent.PRODUCT);
		} catch (Exception e) {
			log.error("{} when calling product review API. Caused by id {}", id);
			throw new AggregateComponentNotFoundException(e.getMessage(), AggregateComponent.PRODUCT);
		}
	}

	private Product stubbedResponse() {
		try {
			String jsonResponse = JsonUtils.parseJsonToString(productJson.getInputStream());
			return new ObjectMapper().readValue(jsonResponse, Product.class);
		} catch (Exception e) {
			log.error("{} when parsing json: {}", e.getMessage());
			return null;
		}
	}
}
