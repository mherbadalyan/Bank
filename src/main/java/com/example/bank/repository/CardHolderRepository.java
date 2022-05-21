package com.example.bank.repository;

import com.example.bank.model.entity.Address;
import com.example.bank.model.entity.CardHolder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardHolderRepository extends JpaRepository<CardHolder, Long> {

    boolean existsCardHolderByPhone(String phone);

    CardHolder getByPhone(String phone);
}
