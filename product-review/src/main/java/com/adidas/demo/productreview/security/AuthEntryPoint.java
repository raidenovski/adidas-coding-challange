package com.adidas.demo.productreview.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class AuthEntryPoint extends BasicAuthenticationEntryPoint {
 
    @Override
    public void commence
      (HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx) 
      throws IOException, ServletException { 
        response.addHeader("WWW-Authenticate", "Cert realm = " + getRealmName());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().println("HTTP Status 401 - " + authEx.getMessage());
    }
    
    @Override
    public void afterPropertiesSet() throws Exception {
        setRealmName("Adidas cert authority");
        super.afterPropertiesSet();
    }
}
