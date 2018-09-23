package com.adidas.demo.product.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Exception that is used when some part of the ProductAggregate was not able to be retrieved from the API. This exception should be
 * handled in the service layer.
 *
 * @author urosh
 *
 */
@Data
@AllArgsConstructor
public class AggregateComponentNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7753878850686966273L;
	/**
	 * Reason of the exception.
	 */
	private String reason;
	/**
	 * Type of component that was not found.
	 */
	private AggregateComponent component;
	
	public static enum AggregateComponent {
		PRODUCT, REVIEW
	}
}
