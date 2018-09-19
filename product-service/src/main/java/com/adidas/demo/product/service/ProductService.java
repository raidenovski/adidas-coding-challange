package com.adidas.demo.product.service;

public interface ProductService<T> {
	
	T get(String id);

}
