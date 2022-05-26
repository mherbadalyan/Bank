package com.example.bank.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountDto {

    private Long accountNumber;

    private String IBAN;

    private Long balance;

    private BankDto bankDto;

    private CardHolderDto cardHolderDto;
}
