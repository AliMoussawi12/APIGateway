package com.example.analyticservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
public class MeteringDataDto {
    @Id
    private UUID id;
    private String smartMeterId;
    private DateTime eventTime;
    private DateTime processTime;
    private double energyTotal;
    private double powerTotal;
    private double energyTotalNeg;
    private double energyTotalPos;


}
