package com.example.pregateway.controller.v1;

import com.example.pregateway.bean.EndUserBeanSession;
import com.example.pregateway.repository.CustomerInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
@RestController
@RequestMapping("/proxy")
public class ProxyController {

    @Value("${gateway.url}")
    String gatewayUrl;
    @Autowired
    CustomerInfoRepository customerInfoRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    EndUserBeanSession endUserBeanSession;

    public ProxyController() {
        restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
    }

    @RequestMapping(value = "/user/killbill/**", method = RequestMethod.GET)
    public ResponseEntity<?> proxyGet(HttpServletRequest request, HttpServletResponse response) throws IOException, URISyntaxException {
        String url = "http://localhost:8090" + request.getRequestURI().replace("/proxy/user/killbill", "");
        System.out.println("URL: "+url);
        UriComponentsBuilder builder = UriComponentsBuilder.fromUri(new URI(url));
        for (String paramName : request.getParameterMap().keySet()) {
            for (String paramValue : request.getParameterMap().get(paramName)) {
                builder.queryParam(paramName, paramValue);
            }
        }

        HttpEntity<Void> requestEntity = new HttpEntity<>(endUserBeanSession.getHeaders());

        ResponseEntity<?> responseEntity = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                requestEntity,
                Object.class
        );

        return ResponseEntity.ok(responseEntity.getBody());
    }

    @RequestMapping(value = "/user/killbill/**", method = RequestMethod.POST)
    @PreAuthorize("hasRole('endUser')")
    public ResponseEntity<?> proxyPost(HttpServletRequest request) throws IOException, URISyntaxException {
        String url = "http://localhost:8090" + request.getRequestURI().replace("/proxy/user/killbill", "");
        System.out.println("URL: "+url);

        UriComponentsBuilder builder = UriComponentsBuilder.fromUri(new URI(url));
        for (String paramName : request.getParameterMap().keySet()) {
            for (String paramValue : request.getParameterMap().get(paramName)) {
                builder.queryParam(paramName, paramValue);
            }
        }

        String requestBody = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody,endUserBeanSession.getHeaders());

        ResponseEntity<?> responseEntity = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.POST,
                requestEntity,
                Object.class
        );

        return ResponseEntity.ok(responseEntity.getBody());
    }
    @RequestMapping(value = "/user/analytics", method = RequestMethod.POST)
    @PreAuthorize("hasRole('endUser')")
    public ResponseEntity<String> proxyPost1(HttpServletRequest request) throws IOException, URISyntaxException {
        System.out.println("request.getUserPrincipal():"+request.getUserPrincipal());
        System.out.println("request.getHeaderNames()"+request.getHeaderNames());
        System.out.println("headers:"+endUserBeanSession.getHeaders().toString());
        System.out.println("customerId :"+endUserBeanSession.getCustomerId());
        System.out.println(customerInfoRepository.findAll());
        return ResponseEntity.ok("asas232323");

    }



}
