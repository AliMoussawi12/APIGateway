package com.example.pregateway.service;

import com.example.pregateway.configuration.property.KillBillApiProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.LocalDate;
import org.killbill.billing.client.KillBillClientException;
import org.killbill.billing.client.KillBillHttpClient;
import org.killbill.billing.client.api.gen.AccountApi;
import org.killbill.billing.client.api.gen.CatalogApi;
import org.killbill.billing.client.api.gen.UsageApi;
import org.killbill.billing.client.model.PlanDetails;
import org.killbill.billing.client.model.gen.RolledUpUsage;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.Executor;

@Service
@EnableAsync(proxyTargetClass = true)
public class CatalogService {
    private static final Logger log =  LogManager.getLogger(CatalogService.class);
    private final KillBillHttpClient killBillClient;
    private final KillBillApiProperties apiProperties;

    AccountApi accountApi;

    CatalogApi catalogApi;
    int FIRST_ITEM=0;
    public CatalogService(KillBillHttpClient killBillClient, KillBillApiProperties apiProperties, Executor executor) {
        this.killBillClient = killBillClient;
        this.apiProperties = apiProperties;
        catalogApi= new CatalogApi(killBillClient);
        accountApi=new AccountApi(killBillClient);
    }



    public PlanDetails getAvailablePlans(UUID accountId) throws KillBillClientException {

   return catalogApi.getAvailableBasePlans(accountId,apiProperties.getRequestOptions());}

}
