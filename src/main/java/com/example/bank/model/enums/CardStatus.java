package com.example.bank.model.enums;

public enum CardStatus {
    CREATED("Created"),
    ACTIVE("Active"),
    BLOCKED("Blocked");

    private final String status;

    CardStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
