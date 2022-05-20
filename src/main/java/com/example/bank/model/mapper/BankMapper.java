package com.example.bank.model.mapper;

import com.example.bank.model.dto.BankDto;
import com.example.bank.model.entity.Bank;

import java.util.Collection;
import java.util.stream.Collectors;

public class BankMapper implements BaseMapper<Bank, BankDto>{
    @Override
    public Bank convertToEntity(BankDto dto) {
        Bank bank = new Bank();
        bank.setName(dto.getName());
        bank.setType(dto.getType());
        return bank;
    }

    @Override
    public BankDto convertToDto(Bank entity) {
        return BankDto.builder().
                name(entity.getName()).
                type(entity.getType()).build();
    }

    @Override
    public Collection<Bank> convertToEntityColl(Collection<BankDto> dtoCollection) {
        return dtoCollection.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<BankDto> convertToDtoColl(Collection<Bank> entityCollection) {
         return entityCollection.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
