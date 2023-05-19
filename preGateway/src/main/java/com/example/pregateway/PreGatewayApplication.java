package com.example.pregateway;

import com.example.pregateway.configuration.TraceResponseFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.WebFilter;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableMongoRepositories
public class PreGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(PreGatewayApplication.class, args);
    }
    @Bean
    WebFilter traceResponseFilter() {
        return new TraceResponseFilter();
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
