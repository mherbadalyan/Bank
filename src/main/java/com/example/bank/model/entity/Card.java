package com.example.bank.model.entity;



import com.example.bank.model.enums.CardPaymentType;
import com.example.bank.model.enums.CardStatus;
import com.example.bank.model.enums.CardType;
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
    private Long cardNumber;

    @Column
    private CardType type;

    @Column(name = "payment_type")
    private CardPaymentType paymentType;

    @Column
    private Long balance;

    @Column(name = "date")
    private Date expDate;

    @Column
    private Integer cvv;

    @Column
    private CardStatus status;

    @Column
    private String pin;

    @OneToOne
    @JoinColumn(name = "account_id",referencedColumnName = "id")
    private Account account;
}
