package com.adidas.demo.product.exception;

import com.adidas.demo.product.dto.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Exception that is used when some part of a product aggregate cannot be found (Such as ProductReview). In that case, a Product is
 * returned to the client with a message that the review is currently not available. This exception should be handled in the controller layer.
 * 
 * @author urosh
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartialAggregateException extends RuntimeException {
	
	private String message;
	private Product product;

}
