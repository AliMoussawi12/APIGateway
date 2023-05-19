package com.example.analyticservice.dto;

import lombok.Data;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Data
public class MeteringDataAnalyticsDto {
    String smartMeterId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    DateTime startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    DateTime endDate;

}
