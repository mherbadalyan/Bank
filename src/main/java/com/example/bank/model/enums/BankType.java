package com.example.bank.model.enums;

public enum BankType {
    CENTRAL("Central"), COMMERCIAL("Commercial"), SPECIALISED("Specialised"), COPERATIVE("Coperative");

    private final String type;

    BankType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}