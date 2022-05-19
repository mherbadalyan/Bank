package com.example.bank.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountDto {

    private Long id;

    private String IBAN;

    private Long balance;

    private Long bankId;

    private Long ch_id;
}
