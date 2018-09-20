package com.adidas.demo.product.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Product {

	private String id;
	private String name;
	@JsonAlias("model_number")
	private String modelNumber;
	@JsonAlias("product_type")
	private String productType;
	@JsonAlias("meta_data")
	private String metaData;
}
