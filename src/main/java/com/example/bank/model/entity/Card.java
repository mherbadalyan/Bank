package com.example.bank.model.entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "card")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardNumber;

    @Column
    private Enum type;

    @Column(name = "payment_type")
    private Enum paymentType;

    @Column
    private Long balance;

    @Column(name = "date")
    private Date expDate;

    @Column
    private Integer cvv;

    @Column
    private Enum status;

    @Column
    private String pin;

    @Column(name = "account_id")
    private Long accountId;
}
