package com.example.pregateway.controller.v2;

import com.example.pregateway.bean.EndUserBeanSession;
import com.example.pregateway.service.AccountService;
import org.killbill.billing.client.KillBillClientException;
import org.killbill.billing.client.model.gen.Account;
import org.killbill.billing.client.model.gen.Bundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/1.0/kb/accounts")
public class AccountController {
    @Autowired
    AccountService accountService;
    @Autowired
    EndUserBeanSession endUserBeanSession;

    @RequestMapping(method = RequestMethod.GET, value = "/11a")
    public ResponseEntity<List<UUID>> getSubscriptionsbyAccountId() throws Exception {
       List<UUID> subscriptions=accountService.getAccountSubscriptions(endUserBeanSession.getCustomerId());
        return new ResponseEntity<>(subscriptions, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/invoices")
    public ResponseEntity<Map<UUID, String>> getAccountInvoices( )throws KillBillClientException {
        Map<UUID, String> invoices = accountService.getAccountInvoices(endUserBeanSession.getCustomerId());
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/")
    public ResponseEntity<Account> getAccount()throws KillBillClientException {
        Account account = accountService.getAccount(endUserBeanSession.getCustomerId());
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/bundles")
    public ResponseEntity<Iterator<Bundle>> getAccountbundles()throws KillBillClientException {
        Iterator<Bundle>  bundles= accountService.getAccountBundles(endUserBeanSession.getCustomerId());
        return new ResponseEntity<>(bundles, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.PUT, value = "/")
    public ResponseEntity<Account> updateAccount(@RequestBody Account account)throws KillBillClientException {
        accountService.updateAccount(endUserBeanSession.getCustomerId(),account);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }


}
