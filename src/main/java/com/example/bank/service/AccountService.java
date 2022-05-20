package com.example.bank.service;

import com.example.bank.model.mapper.AccountMapper;
import com.example.bank.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountService{

    private final AccountMapper accountMapper;

    private final AccountRepository accountRepository;



}
