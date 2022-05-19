package com.example.bank.model.enums;

public enum CardType {
    DEBIT("Debit"),CREDIT("Credit");

    private final String type;

    CardType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
