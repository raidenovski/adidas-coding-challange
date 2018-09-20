package com.adidas.demo.productreview.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.adidas.demo.productreview.entity.ProductReview;
import com.adidas.demo.productreview.repository.ProductReviewRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ProductReviewServiceCacheTests {

	private static final float AVERAGE_REVIEW_SCORE = 12.3f;
	private static final int NUM_OF_REVIEWS = 3;
	
	@Autowired
	private ProductReviewService service;
	@SpyBean
	private ProductReviewRepository repository;
	@Autowired
    private CacheManager cacheManager;

    @Before
    public void evictAllCaches() {
        cacheManager.getCacheNames().parallelStream().forEach(name -> cacheManager.getCache(name).clear());
    }
    
    
    @Test
    @Ignore
    public void whenGettingProductReviewMultipletimes_thenReturnedCachedInstance() {
    	String productId = "C77154";
    	
    	ProductReview productFromDB =  service.get(productId);
    	
    	assertThat(productFromDB.getAverageReviewScore()).isEqualTo(AVERAGE_REVIEW_SCORE);
    	assertThat(productFromDB.getNumberOfReviews()).isEqualTo(NUM_OF_REVIEWS);
    	
    	ProductReview productFromCache =  service.get(productId);
    	
    	assertThat(productFromCache.getAverageReviewScore()).isEqualTo(AVERAGE_REVIEW_SCORE);
    	assertThat(productFromCache.getNumberOfReviews()).isEqualTo(NUM_OF_REVIEWS);
    	
    	verify(repository, times(1)).findById(productId);
    }

}
