package com.bilgeadam.onlinefoodapp.domain;

public enum OrderStatus {
    PREPARING("Preparing"),
    PREPARED("Prepared"),
    CANCELED("Canceled");

    private String value;

    OrderStatus(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
