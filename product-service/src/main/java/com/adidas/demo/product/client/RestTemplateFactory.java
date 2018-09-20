package com.adidas.demo.product.client;

import org.apache.http.HttpHost;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.adidas.demo.product.config.HttpComponentsClientHttpRequestFactoryBasicAuth;

@Component
public class RestTemplateFactory implements FactoryBean<RestTemplate>, InitializingBean {
  
	@Value("${api.product-review.username}")
	private String username;
	@Value("${api.product-review.password}")
	private String password;
	
    private RestTemplate restTemplate;
 
    @Override
	public RestTemplate getObject() {
        return restTemplate;
    }
    @Override
	public Class<RestTemplate> getObjectType() {
        return RestTemplate.class;
    }
    @Override
	public boolean isSingleton() {
        return true;
    }
 
    @Override
	public void afterPropertiesSet() {
        HttpHost host = new HttpHost("localhost", 8081, "http");
        restTemplate = new RestTemplate(
          new HttpComponentsClientHttpRequestFactoryBasicAuth(host));
        restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(username, password));
    }
}