package com.example.bank.repository;

import com.example.bank.model.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, Long> {

    boolean existsBankById(Long id);

//    Bank getGetBankById(Long id);
}
