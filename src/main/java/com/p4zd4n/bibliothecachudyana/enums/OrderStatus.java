package com.p4zd4n.bibliothecachudyana.enums;

public enum OrderStatus {

    IN_PROGRESS("W realizacji"),
    SENT("Wysłane"),
    RECEIVED("Odebrane");

    private final String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String getDescriptionInPolish() {
        return status;
    }
}
