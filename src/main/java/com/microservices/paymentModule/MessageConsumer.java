package com.microservices.paymentModule;

import com.google.gson.Gson;
import com.microservices.paymentModule.dtos.TransactionDTO;
import com.microservices.paymentModule.service.IAccountService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    private TransactionDTO transactionDTO;
    @Autowired
    private IAccountService accountService;

    @JmsListener(destination = "demo")
    @Scheduled(cron = "*/2 * * * * MON-FRI")
    public void receiveMessage(String message) {
        Gson gson = new Gson();
        transactionDTO = gson.fromJson(message, TransactionDTO.class);
        System.out.println(transactionDTO.getToAccount());
        System.out.println(transactionDTO.getAmount());
        System.out.println(transactionDTO.getFromAccount());
        System.out.println(message);
        accountService.updateBalanceAccountReceiver(transactionDTO);
    }
}

