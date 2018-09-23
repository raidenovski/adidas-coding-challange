package com.adidas.demo.product.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(Include.NON_NULL)
public class Product {

	private String id;
	private String name;
	@JsonAlias("model_number")
	private String modelNumber;
	@JsonAlias("product_type")
	private String productType;
	@JsonAlias("meta_data")
	private MetaData metaData;
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public class MetaData {
	
		@JsonAlias("page_title")
		private String pageTitle;
		@JsonAlias("site_name")
		private String siteName;
		private String keywords;
		private String description;
		private String canonical;
	}
}
