package com.example.bank.model.dto;

import com.example.bank.model.enums.BankType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BankDto {

    private Long id;

    private String name;

    private BankType type;

}
