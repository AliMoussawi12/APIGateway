package com.example.pregateway.controller.v2;

import com.example.pregateway.dto.dto.killbill.UsageRecordRequestDto;
import com.example.pregateway.service.UsageService;
import org.killbill.billing.client.KillBillClientException;
import org.killbill.billing.client.model.gen.RolledUpUsage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;
import java.util.UUID;


@Controller
@RequestMapping("/1.0/kb/usages")
@CrossOrigin
public class UsageController {
    @Autowired
    UsageService usageService;
    @RequestMapping(method = RequestMethod.GET, value = "/")
    public ResponseEntity<RolledUpUsage> getUsagebySubscriptionId(@RequestBody UsageRecordRequestDto usageRecordRequest) throws KillBillClientException {
        RolledUpUsage rolledUpUsage=usageService.getUsageBySubscriptionId(usageRecordRequest.getSubscriptionId(),usageRecordRequest.getStartDate(),usageRecordRequest.getEndDate());
    return new ResponseEntity<>(rolledUpUsage, HttpStatus.OK);}

}
