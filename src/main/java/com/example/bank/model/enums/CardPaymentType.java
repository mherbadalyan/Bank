package com.example.bank.model.enums;

public enum CardPaymentType {
    VISA("Visa"), MASTER_CARD("Master Card"), AMERICAN_EXPRESS("American Express");

    private final String name;

    CardPaymentType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
