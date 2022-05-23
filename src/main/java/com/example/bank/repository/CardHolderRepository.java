package com.example.bank.repository;

import com.example.bank.model.entity.Address;
import com.example.bank.model.entity.CardHolder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardHolderRepository extends JpaRepository<CardHolder, Long> {

    boolean existsCardHolderByPhone(String phone);

    Optional<CardHolder> getByPhone(String phone);

    boolean deleteByPhone(String phone);
}
