package com.bca.usermanagementapi.model.dto;

import java.util.ArrayList;
import java.util.List;

public class Response<T> {
    
    private StatusCode status;
    private List<String> messages = new ArrayList<>();
    private T payload;

    public StatusCode getStatus() {
        return status;
    }
    public void setStatus(StatusCode status) {
        this.status = status;
    }
    public List<String> getMessages() {
        return messages;
    }
    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
    public T getPayload() {
        return payload;
    }
    public void setPayload(T payload) {
        this.payload = payload;
    }
}
