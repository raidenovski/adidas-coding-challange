package com.adidas.demo.productreview.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.adidas.demo.productreview.dto.ProductReviewData;
import com.adidas.demo.productreview.entity.ProductReview;
import com.adidas.demo.productreview.repository.ProductReviewRepository;
import com.adidas.demo.productreview.utils.TestUtilsFactory;

@RunWith(MockitoJUnitRunner.class)
public class ProductReviewServiceTests {

	private ProductReviewService testClass;
	@Mock
	private ProductReviewRepository repository;
	
	@Before
	public void setup() {
		this.testClass = new ProductReviewService(repository);
	}
	
	@Test
	public void givenValidProductId_whenGettingProductReview_thenCallRepositoryAndRetreiveProductReview() {
		ProductReview expected = TestUtilsFactory.getProductReview();
		given(repository.findById(anyString())).willReturn(Optional.of(expected));
		
		ProductReview actual = testClass.get(expected.getProductId());
		
		assertThat(actual.getAverageReviewScore()).isEqualTo(expected.getAverageReviewScore());
	}
	
	@Test
	public void whenAllProductReviewsAreObtained_thenReturnProductReviewData() {
		List<ProductReview> list = new ArrayList<>();
		list.add(TestUtilsFactory.getProductReview());
		list.add(TestUtilsFactory.getProductReview());
		list.add(TestUtilsFactory.getProductReview());
		int expected = 3;
		given(repository.findAll()).willReturn(list);
		
		ProductReviewData actual = testClass.getAll();
		
		assertThat(actual.getData().size()).isEqualTo(expected);
	}
	
	@Test
	public void givenNewProductReview_whenSavingNewEntity_thenCallRepository() {
		ProductReview expected = TestUtilsFactory.getProductReview();
		given(repository.save(Mockito.any(ProductReview.class))).willReturn(expected);
		
		ProductReview actual = testClass.save(expected);
		
		assertThat(actual).isEqualTo(expected);
	}
	
	@Test
	public void givenUpdatedProductReview_whenUpdatingEntity_thenCallRepositoryAndUpdate() {
		int expectedNumberOfReviews = 5;
		float expectedAverageReviewScore = 10.2f;
		ProductReview expected = TestUtilsFactory.getProductReview();
		ProductReview updated = ProductReview.builder()
				.productId(expected.getProductId())
				.averageReviewScore(expectedAverageReviewScore)
				.numberOfReviews(expectedNumberOfReviews)
				.build();
		given(repository.findById(anyString())).willReturn(Optional.of(expected));
		
		ProductReview actual = testClass.update(updated.getProductId(), updated);
		
		assertThat(actual.getAverageReviewScore()).isEqualTo(expectedAverageReviewScore);
		assertThat(actual.getNumberOfReviews()).isEqualTo(expectedNumberOfReviews);
	}
	
	@Test
	public void givenProductId_whenDeletingEntity_thenCallRepositoryMethodWithCorrectParameter() {
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		String expected = "CA1234";
		
		testClass.delete(expected);
		
		verify(repository).deleteById(captor.capture());
		assertThat(captor.getValue()).isEqualTo(expected);
	}
}
