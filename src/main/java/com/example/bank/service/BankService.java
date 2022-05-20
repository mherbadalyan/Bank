package com.example.bank.service;

import com.example.bank.model.mapper.BankMapper;
import com.example.bank.repository.BankRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BankService {

    private final BankMapper bankMapper;

    private final BankRepository bankRepository;
}
