package com.example.bank.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardHolderDto {

    private Long id;

    private String name;

    private String phone;

    private Long address_id;
}
