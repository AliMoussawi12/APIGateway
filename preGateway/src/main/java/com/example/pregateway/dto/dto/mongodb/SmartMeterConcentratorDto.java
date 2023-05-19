package com.example.pregateway.dto.dto.mongodb;

import lombok.Data;

import java.util.UUID;
@Data
public class SmartMeterConcentratorDto {
    UUID concentratorId;
    UUID smartMeterId;
}