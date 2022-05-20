package com.example.bank.service;

import com.example.bank.model.mapper.CardHolderMapper;
import com.example.bank.repository.CardHolderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CardHolderService {

    private final CardHolderMapper cardHolderMapper;

    private final CardHolderRepository cardHolderRepository;

}
