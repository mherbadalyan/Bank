package com.example.bank.model.mapper;

import com.example.bank.model.dto.BankDto;
import com.example.bank.model.entity.Bank;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
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

}
