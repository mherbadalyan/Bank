package com.example.bank.repository;

import com.example.bank.model.entity.Address;
import com.example.bank.model.entity.CardHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CardHolderRepository extends JpaRepository<CardHolder, Long> {

    boolean existsCardHolderByPhone(String phone);

    Optional<CardHolder> getByPhone(String phone);

    @Modifying
    void removeByPhone(String phone);
}
