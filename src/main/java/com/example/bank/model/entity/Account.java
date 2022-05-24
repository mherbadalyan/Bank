package com.example.bank.model.entity;


import lombok.*;

import javax.persistence.*;

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

    @Column(name = "balance", columnDefinition = "long default 0l")
    private Long balance = 0L;
    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;
    @ManyToOne
    @JoinColumn(name = "CH_id")
    CardHolder cardHolder;


}
