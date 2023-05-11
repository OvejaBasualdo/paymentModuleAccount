package com.microservices.paymentModule.controller;

import com.microservices.paymentModule.dtos.TransactionDTO;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.ObjectMessage;
import jakarta.jms.Session;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/messaging")
public class Sender {
    @Autowired
    JmsTemplate jmsTemplate;

    public Sender() {
    }

    public String convertAndSend(TransactionDTO transactionDTO) {
        jmsTemplate.send("demo", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("amount", transactionDTO.getAmount());
                jsonObject.put("fromAccount", transactionDTO.getFromAccount());
                jsonObject.put("toAccount", transactionDTO.getToAccount());
                System.out.println(transactionDTO.getScheduledDate());
                jsonObject.put("scheduledDate", transactionDTO.getScheduledDate().toString());
                ObjectMessage object = session.createObjectMessage(jsonObject.toString());
                System.out.println(object);
                return object;
            }
        });

        return "TRANSACTIONDTO to string ";
    }
}
