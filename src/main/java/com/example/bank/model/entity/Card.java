package com.example.bank.model.entity;



import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.sql.Date;

@Entity
@Table(name = "card")
public class Card {
    @Id
    private Long cardNumber;
    private String type;
    private String paymentType;
    private Long balance;
    private Date expDate;
    private Integer cvv;
    private String status;
    private String pin;
    private Long accountId;
}
