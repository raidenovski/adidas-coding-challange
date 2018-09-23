package com.adidas.demo.product.client;

import com.adidas.demo.product.exception.AggregateComponentNotFoundException;

public interface ProductClient<T> {

	T getResourceFromUrl(String urlTemplate, String param) throws AggregateComponentNotFoundException;
}
