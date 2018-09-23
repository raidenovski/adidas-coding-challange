package com.adidas.demo.product.client;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import com.adidas.demo.product.client.impl.ProductReviewClientImpl;
import com.adidas.demo.product.dto.ProductReview;
import com.adidas.demo.product.exception.AggregateComponentNotFoundException;
import com.adidas.demo.product.utils.TestUtilsFactory;
import com.fasterxml.jackson.core.JsonProcessingException;

@RunWith(SpringRunner.class)
@RestClientTest(ProductReviewClientImpl.class)
@AutoConfigureWebClient(registerRestTemplate = true)
public class ProductReviewClientImplTests {

	private static final String URL_TEMPLATE = "/review/{id}";
	private static final String ID = "C77154";
	@Autowired
	private ProductReviewClientImpl testClass;
    @Autowired
    private MockRestServiceServer mockServer;
    @Rule
    public OutputCapture output = new OutputCapture();
    
    @Test
    public void givenClientCall_whenCallingProductReviewAPI_thenReturnResponseOk() throws JsonProcessingException {
    	String json = TestUtilsFactory.getProductReviewJson();
    	mockServer.expect(requestTo("/review/" + ID))
		.andRespond(withSuccess(json, MediaType.APPLICATION_JSON_UTF8));
    	
    	ProductReview actual = testClass.getResourceFromUrl(URL_TEMPLATE, ID);
    	
    	assertThat(json).contains(String.valueOf(actual.getNumberOfReviews()));
    }
    
    @Test
    public void givenClientCallForNonExistentEntry_whenCallingAPI_thenReturn404AndThrowException() {
    	mockServer.expect(requestTo("/review/" + ID))
		.andRespond(withStatus(HttpStatus.NOT_FOUND));
    	
    	try {
    		testClass.getResourceFromUrl(URL_TEMPLATE, ID);
    	} catch (AggregateComponentNotFoundException pae) {
    		assertThat(output.toString()).contains("Response 404");    		
    	}
    }
    
}
