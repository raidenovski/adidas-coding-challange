package com.adidas.demo.product.service;

import com.adidas.demo.product.exception.AggregateComponentNotFoundException;

public interface ProductService<T> {
	
	T get(String id) throws AggregateComponentNotFoundException;

}
