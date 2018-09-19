package com.adidas.demo.product.client;

public interface ProductClient<T> {

	T getResourceFromUrl(String urlTemplate, String param); // ?? throw custom exception
}
