package com.example.bank.model.mapper;

import com.example.bank.model.dto.BankDto;
import com.example.bank.model.entity.Bank;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class BankMapper implements BaseMapper<Bank, BankDto>{
    @Override
    public Bank convertToEntity(BankDto dto) {
        Bank bank = new Bank();
        bank.setId(dto.getId());
        bank.setName(dto.getName());
        bank.setType(dto.getType());
        return bank;
    }

    @Override
    public BankDto convertToDto(Bank entity) {
        return BankDto.builder().
                id(entity.getId()).
                name(entity.getName()).
                type(entity.getType()).build();
    }

    public List<BankDto> mapAllToDepartmentDto(List<Bank> banks) {
        List<BankDto> list = new ArrayList<>();
        for (Bank bank : banks) {
            BankDto bankDto = convertToDto(bank);
            list.add(bankDto);
        }
        return list;
    }


}
