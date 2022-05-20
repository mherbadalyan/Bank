package com.example.bank.service;

import com.example.bank.model.mapper.AddressMapper;
import com.example.bank.repository.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddressService {
    private final AddressMapper addressMapper;

    private final AddressRepository addressRepository;
}
