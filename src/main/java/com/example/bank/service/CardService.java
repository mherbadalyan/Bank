package com.example.bank.service;

import com.example.bank.model.mapper.CardMapper;
import com.example.bank.repository.CardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CardService {

    private final CardMapper cardMapper;

    private final CardRepository cardRepository;
}
