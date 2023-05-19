package com.example.pregateway.dto.dto.mongodb;

import lombok.Data;

import java.util.UUID;

@Data
public class CustomerInfoDto {
    String userEmail;
    UUID customerAccountId;
}