package com.example.bank.model.dto;

import com.example.bank.model.enums.CardPaymentType;
import com.example.bank.model.enums.CardStatus;
import com.example.bank.model.enums.CardType;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class CardDto {

    private Long cardNumber;

    private CardType type;

    private CardPaymentType paymentType;

    private Long balance;

    private Date expDate;

    private Integer cvv;

    private CardStatus status;

    private String pin;

    private AccountDto accountDto;

}
