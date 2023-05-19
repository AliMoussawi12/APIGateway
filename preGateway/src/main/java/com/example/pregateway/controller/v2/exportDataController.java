package com.example.pregateway.controller.v2;

import com.example.pregateway.bean.EndUserBeanSession;
import com.example.pregateway.dto.dto.killbill.UsageRecordRequestDto;
import com.example.pregateway.service.ExportService;
import org.asynchttpclient.Response;
import org.killbill.billing.client.KillBillClientException;
import org.killbill.billing.client.api.gen.ExportApi;
import org.killbill.billing.client.model.gen.RolledUpUsage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/1.0/kb/export")
@CrossOrigin
public class exportDataController {
    @Autowired
    EndUserBeanSession endUserBeanSession;
    @Autowired
    ExportService exportService;


    @RequestMapping(method = RequestMethod.GET, value = "/")
    public ResponseEntity<Response> getExportData() throws KillBillClientException {
        Response response = exportService.getExportData(endUserBeanSession.getCustomerId());
        return new ResponseEntity<>(response, HttpStatus.OK);}

}
