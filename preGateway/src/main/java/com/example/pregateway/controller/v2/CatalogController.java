package com.example.pregateway.controller.v2;

import com.example.pregateway.bean.EndUserBeanSession;
import com.example.pregateway.dto.dto.killbill.UsageRecordRequestDto;
import com.example.pregateway.service.CatalogService;
import org.killbill.billing.client.KillBillClientException;
import org.killbill.billing.client.model.PlanDetails;
import org.killbill.billing.client.model.gen.RolledUpUsage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/1.0/kb/catalog")
@CrossOrigin
public class CatalogController {
    @Autowired
    CatalogService catalogService;
    @Autowired
    EndUserBeanSession endUserBeanSession;

    @RequestMapping(method = RequestMethod.GET, value = "/availableBasePlans")
    public ResponseEntity<PlanDetails> getAvailableBasePlan() throws KillBillClientException {
        PlanDetails planDetails = catalogService.getAvailablePlans(endUserBeanSession.getCustomerId());
        return new ResponseEntity<>(planDetails, HttpStatus.OK);}



}
