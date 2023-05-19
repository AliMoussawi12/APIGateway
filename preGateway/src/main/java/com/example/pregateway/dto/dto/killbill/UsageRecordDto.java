package com.example.pregateway.dto.dto.killbill;

import lombok.Data;
import org.joda.time.LocalDate;

@Data
public class UsageRecordDto {

    Long amount;
    LocalDate startDate;
    LocalDate endDate;

    public UsageRecordDto( Long amount, LocalDate startDate, LocalDate endDate)

    {this.amount=amount;
    this.startDate=startDate;
    this.endDate=endDate;}
}
