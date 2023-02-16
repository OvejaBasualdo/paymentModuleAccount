package com.microservices.paymentModule;

import com.google.gson.*;
import com.microservices.paymentModule.controller.Sender;
import com.microservices.paymentModule.dtos.TransactionDTO;
import com.microservices.paymentModule.entity.Account;
import com.microservices.paymentModule.model.User;
import com.microservices.paymentModule.service.IAccountService;
import jakarta.jms.ObjectMessage;
import jakarta.jms.Session;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MessageConsumer {

    private Logger logger = LoggerFactory.getLogger(MessageConsumer.class);
    @Autowired
    private RestTemplate accountRest;

    private TransactionDTO transactionDTO;
    private List<TransactionDTO> transactionDTOSList = new ArrayList<>();


    @Autowired
    private IAccountService accountService;

    @JmsListener(destination = "transactionDelayed")
    public void receiveMessage(String message) throws InterruptedException {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return LocalDate.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }
        }).create();
        transactionDTO = gson.fromJson(message, TransactionDTO.class);
        System.out.println(message);
        logger.info("The transaction is executing");


        if (accountService.updateBalanceAccountSender(transactionDTO)) {
            accountService.updateBalanceAccountReceiver(transactionDTO);
            logger.info("The transaction was done");
        } else {
            logger.warn("The transaction was processed");
        }
    }
}
            /*Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonSerializer<LocalDate>() {

                public JsonElement serialize(LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
                    return new JsonPrimitive(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(src));
                }
            }).create();
            String message = gson.toJson(transactionDTO);
            Map<String, String> pathVariables = new HashMap<>();
            pathVariables.put("message", message);
            String queueTransaction = accountRest.getForObject("http://localhost:8003/api/messaging/send/{message}", String.class, pathVariables);
            Thread.sleep(1000L);
             */

