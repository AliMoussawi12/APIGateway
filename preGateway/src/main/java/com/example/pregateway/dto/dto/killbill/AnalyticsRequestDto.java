package com.example.pregateway.dto.dto.killbill;

import lombok.Data;
import org.joda.time.DateTime;

@Data
public class AnalyticsRequestDto {
    String groupedBy;
   DateTime startDate;
    DateTime endDate;
}
