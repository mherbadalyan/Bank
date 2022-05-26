package com.example.bank.repository;

import com.example.bank.model.entity.Account;
import com.example.bank.model.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {

}
