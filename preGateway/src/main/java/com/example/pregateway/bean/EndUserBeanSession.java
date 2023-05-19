package com.example.pregateway.bean;

import com.example.pregateway.models.CustomerInfo;
import com.example.pregateway.repository.CustomerInfoRepository;
import lombok.Data;
import org.keycloak.KeycloakPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Data
public class EndUserBeanSession {

    private String email;
    private HttpHeaders headers;
    private UUID customerId;
    public EndUserBeanSession(){

        initialize();
    }

    public void initialize() {
        setHeaders(getHeaders());
    }
    public HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("admin", "password");
        headers.set("X-Killbill-ApiKey", "TEST-APP");
        headers.set("X-Killbill-ApiSecret", "lazar");
        headers.set("X-Killbill-CreatedBy", "demo");
        headers.set("Content-Type", "application/x-www-form-urlencoded");
        return headers;
    }
}
