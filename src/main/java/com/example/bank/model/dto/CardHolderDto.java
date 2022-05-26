package com.example.bank.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardHolderDto {

    private Long id;

    private String name;

    private String phone;
    @JsonProperty(value = "address")
    private AddressDto addressDto;

    @Override
    public String toString() {
        return  " name = " + name  +
                ", phone = " + phone +
                ", address ( " + addressDto + " )";
    }
}
