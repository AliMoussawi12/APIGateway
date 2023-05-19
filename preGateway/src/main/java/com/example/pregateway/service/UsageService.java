package com.example.pregateway.service;

import com.example.pregateway.configuration.property.KillBillApiProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.LocalDate;
import org.killbill.billing.client.KillBillClientException;
import org.killbill.billing.client.KillBillHttpClient;
import org.killbill.billing.client.api.gen.AccountApi;
import org.killbill.billing.client.api.gen.UsageApi;
import org.killbill.billing.client.model.gen.RolledUpUsage;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.Executor;

@Service
@EnableAsync(proxyTargetClass = true)
public class UsageService {

    static String UNIT_TYPE = "kWh";

    private static final Logger log =  LogManager.getLogger(UsageService.class);
    private final KillBillHttpClient killBillClient;
    private final KillBillApiProperties apiProperties;

    AccountApi accountApi;
    UsageApi usageApi;
    int FIRST_ITEM=0;
    public UsageService(KillBillHttpClient killBillClient, KillBillApiProperties apiProperties, Executor executor) {
        this.killBillClient = killBillClient;
        this.apiProperties = apiProperties;
        usageApi= new UsageApi(killBillClient);
        accountApi=new AccountApi(killBillClient);
    }

    public RolledUpUsage getUsageBySubscriptionId( UUID subscriptionId, LocalDate startDate, LocalDate endDate) throws KillBillClientException {
        return usageApi.getUsage(subscriptionId, UNIT_TYPE, startDate, endDate, apiProperties.getRequestOptions());
    }



}
