package com.example.bank.model.entity;


import com.example.bank.model.enums.BankType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "bank")
public class Bank {
    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private BankType type;

    @OneToMany(mappedBy = "bank",cascade = CascadeType.REMOVE)
    Set<Account> accounts = new HashSet<>();
}
