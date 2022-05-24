package com.example.bank.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Transfer {
    private Long from;
    private Long to;
    private Long amount;
}
