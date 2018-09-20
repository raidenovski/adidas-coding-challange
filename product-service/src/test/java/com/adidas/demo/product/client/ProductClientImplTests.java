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

import com.adidas.demo.product.client.impl.ProductClientImpl;
import com.adidas.demo.product.dto.Product;
import com.adidas.demo.product.utils.TestUtilsFactory;
import com.fasterxml.jackson.core.JsonProcessingException;

@RunWith(SpringRunner.class)
@RestClientTest(ProductClientImpl.class)
@AutoConfigureWebClient(registerRestTemplate = true)
public class ProductClientImplTests {

	private static final String URL_TEMPLATE = "/product/{id}";
	private static final String ID = "C77154";
	@Autowired
	private ProductClientImpl testClass;
    @Autowired
    private MockRestServiceServer mockServer;
    @Rule
    public OutputCapture output = new OutputCapture();
    
    @Test
    public void givenClientCall_whenCallingAPI_thenReturnResponse() throws JsonProcessingException {
    	String json = TestUtilsFactory.getProductJson();
    	mockServer.expect(requestTo("/product/" + ID))
		.andRespond(withSuccess(json, MediaType.APPLICATION_JSON_UTF8));
    	
    	Product actual = testClass.getResourceFromUrl(URL_TEMPLATE, ID);
    	
    	assertThat(json).contains(actual.getId());
    }
    
    @Test
    public void givenClientCallForNonExistentEntry_whenCallingAPI_Return404() {
    	mockServer.expect(requestTo("/product/" + ID))
		.andRespond(withStatus(HttpStatus.NOT_FOUND));
    	
    	Product actual = testClass.getResourceFromUrl(URL_TEMPLATE, ID);
    	
    	assertThat(actual).isNull();
    	assertThat(output.toString()).contains("Response 404");
    }
    
}
