package com.example.bank.model.entity;

import lombok.*;
import javax.persistence.*;
@Entity
@Table(name = "card_holder")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class CardHolder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "address_id")
    private String addressId;
    @Column(name = "name")
    private  String name;
    @Column(name = "phone")
    private String phone;


}
