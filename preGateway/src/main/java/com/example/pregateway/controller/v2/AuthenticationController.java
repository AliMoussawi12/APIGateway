package com.example.pregateway.controller.v2;

import com.example.pregateway.dto.LoginAttributesDto;
import org.keycloak.representations.AccessTokenResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {
    Logger log = LoggerFactory.getLogger(AuthenticationController.class);


    private final RestTemplate restTemplate;

    public AuthenticationController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginAttributesDto loginAttributesDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", loginAttributesDto.getGrantType());
        requestBody.add("client_id", "smartMeterClient");
        requestBody.add("username", loginAttributesDto.getUsername());
        requestBody.add("password", loginAttributesDto.getPassword());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(requestBody, headers);

        AccessTokenResponse result = restTemplate.exchange(
                "http://localhost:8080/auth/realms/Smart-Meter/protocol/openid-connect/token",
                HttpMethod.POST,
                request,
                AccessTokenResponse.class
        ).getBody();

        return ResponseEntity.ok(result);
    }

}
