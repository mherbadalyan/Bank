package com.example.bank.model.entity;

import com.example.bank.model.enums.CardStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CardStatusRequest {
    Long cardNumber;
    CardStatus cardStatus;
}
