package com.example.pregateway.service;

import com.example.pregateway.configuration.property.KillBillApiProperties;
import com.google.common.base.Preconditions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.asynchttpclient.Response;
import org.joda.time.LocalDate;
import org.killbill.billing.client.KillBillClientException;
import org.killbill.billing.client.KillBillHttpClient;
import org.killbill.billing.client.RequestOptions;
import org.killbill.billing.client.api.gen.AccountApi;
import org.killbill.billing.client.api.gen.ExportApi;
import org.killbill.billing.client.api.gen.UsageApi;
import org.killbill.billing.client.model.gen.RolledUpUsage;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.util.UUID;
import java.util.concurrent.Executor;

@Service
@EnableAsync(proxyTargetClass = true)
public class ExportService {

    private static final Logger log =  LogManager.getLogger(ExportService.class);
    private final KillBillApiProperties apiProperties;
    private final KillBillHttpClient killBillClient;

    ExportApi exportApi;
    int FIRST_ITEM=0;
    public ExportService(KillBillHttpClient killBillClient, KillBillApiProperties apiProperties, Executor executor) {
        this.killBillClient=killBillClient;
        this.apiProperties = apiProperties;
        exportApi=new ExportApi(killBillClient);
    }

    public Response getExportData(UUID accountId) throws KillBillClientException {
        return exportDataForAccount(accountId,null, apiProperties.getRequestOptions());
    }
    public Response exportDataForAccount(final UUID accountId, final OutputStream outputStream, final RequestOptions inputOptions) throws KillBillClientException {
        Preconditions.checkNotNull(accountId, "Missing the required parameter 'accountId' when calling exportDataForAccount");
        final String uri = "/1.0/kb/export/{accountId}"
                .replaceAll("\\{" + "accountId" + "\\}", accountId.toString());
        final RequestOptions.RequestOptionsBuilder inputOptionsBuilder = inputOptions.extend();
        inputOptionsBuilder.withHeader(KillBillHttpClient.HTTP_HEADER_ACCEPT, "application/octet-stream");
        final RequestOptions requestOptions = inputOptionsBuilder.build();
        final Response response = killBillClient.doGet(uri, outputStream, requestOptions);
        return response;
    }


}
