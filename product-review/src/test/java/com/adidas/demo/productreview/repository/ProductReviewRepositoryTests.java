package com.adidas.demo.productreview.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.adidas.demo.productreview.entity.ProductReview;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductReviewRepositoryTests {

	@Autowired
	private ProductReviewRepository repository;
	
	@Test
	public void whenFindingByValidId_thenReturnEntity() {
		String id = "C77154";
		
		ProductReview actual = repository.findById(id).get();
		
		assertThat(actual).isNotNull();
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void whenFindingByInvalidId_thenThrowException() {
		String id = "dog";
		
		repository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
		
	}
	
	@Test
	public void whenGettingAll_thenReturnAllFromDatabase() {
		int expected = 5;
		
		List<ProductReview> list = new ArrayList<>();
		repository.findAll().forEach(list::add);
		
		assertThat(list).isNotEmpty();
		assertThat(list).hasSize(expected);
	}
}
