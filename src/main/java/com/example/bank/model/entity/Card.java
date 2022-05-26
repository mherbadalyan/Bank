package com.example.bank.model.entity;

import com.example.bank.model.enums.CardPaymentType;
import com.example.bank.model.enums.CardStatus;
import com.example.bank.model.enums.CardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.sql.Date;

@Builder
@Entity
@Table(name = "card")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {

    @Id
    private Long cardNumber;

    @Column
    private CardType type;

    @Column(name = "payment_type")
    private CardPaymentType paymentType;


    @Column(name = "date")
    private Date expDate;

    @Column
    private String cvv;

    @Column
    private CardStatus status;

    @Column
    private String pin;


    @ManyToOne
    @JoinColumn(name = "account_number",referencedColumnName = "accountNumber")
    Account account;
}
