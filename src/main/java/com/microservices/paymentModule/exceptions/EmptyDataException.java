package com.microservices.paymentModule.exceptions;

import java.util.ArrayList;
import java.util.List;

public class EmptyDataException extends Exception {
    private List<String> messages;

    public EmptyDataException(List<String> messages) {
        super((messages != null && messages.size() > 0 ? messages.get(0) : null));
        if (this.messages==null){
            this.messages = new ArrayList<String>();
        }else {
            this.messages=messages;
        }
    }

    public List<String> getMessages() {
        return messages;
    }
}
