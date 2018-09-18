package com.adidas.demo.productreview.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.adidas.demo.productreview.entity.ProductReview;

/**
 * Repository interface that implements spring data {@link CrudRepository} which can be easily replaced by our very own
 * repository implementation as long as we maintain the four basic CRUD operations.
 * 
 * @author urosh
 *
 */
@Repository
public interface ProductReviewRepository extends CrudRepository<ProductReview, String> {

}
