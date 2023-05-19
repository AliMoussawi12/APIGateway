package com.example.pregateway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginAttributesDto {
   String username;
   String password;
   String grantType;

}
