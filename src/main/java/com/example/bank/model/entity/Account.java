package com.example.bank.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CH_id")
    private Long CH_id;

    @Column(name = "IBAN")
    private String IBAN;

    @Column(name = "CH_id")
    private Long bank_id;

    @Column(name = "balance")
    private Long balance;


}
