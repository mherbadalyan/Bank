package com.example.bank.model.entity;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "account")
public class Account {

    @Id
    private Long accountNumber;

    @Column(name = "IBAN", unique = true)
    private String IBAN;

    @Column(name = "balance")
    private Long balance = 0L;
    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;
    @ManyToOne
    @JoinColumn(name = "CH_id")
    CardHolder cardHolder;

    @OneToOne(mappedBy = "account")
    Card card;
}
