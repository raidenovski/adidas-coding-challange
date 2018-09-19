package com.adidas.demo.productreview.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.adidas.demo.productreview.dto.ProductReviewData;
import com.adidas.demo.productreview.entity.ProductReview;
import com.adidas.demo.productreview.service.ProductReviewService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/review")
public class ProductReviewController {

	private ProductReviewService service;
	
	public ProductReviewController(ProductReviewService service) {
		this.service = service;
	}
	
	@GetMapping("/{product_id}")
	public ProductReview get(@PathVariable("product_id") String productId) {
		log.info("Handle request: get with id {}");
		return service.get(productId);
	}
	
	@GetMapping("/getAll")
	public ProductReviewData getAll() {
		log.info("Handle request: get all product reviews");
		return service.getAll();
	}
	
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public ProductReview save(@RequestBody ProductReview productReview) {
		log.info("Handle request: save new {}", productReview.toString());
		return service.save(productReview);
	}
	
	@PutMapping("/{product_id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ProductReview update(@PathVariable("product_id") String productId, @RequestBody ProductReview update) {
		log.info("Handle request: update id {} with {}", productId, update.toString());
		ProductReview productReview = service.update(productId, update);
		return service.save(productReview);
	}
	
	@DeleteMapping("/{product_id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("product_id") String productId, @RequestBody ProductReview productReview) {
		log.info("Handle request: delete {}", productReview.toString());
		// TODO pass object to service?
		service.delete(productId);
	}
}
