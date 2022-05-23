package com.example.bank.service;

import com.example.bank.model.dto.BankDto;
import com.example.bank.model.entity.Bank;
import com.example.bank.model.mapper.BankMapper;
import com.example.bank.repository.BankRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BankService {

    private final BankMapper bankMapper;

    private final BankRepository bankRepository;

    public Optional<BankDto> createBank(BankDto  bankDto) {
        if (bankRepository.existsById(bankDto.getId())) {
            return Optional.empty();
        }
        Bank bankToSave = this.bankMapper.convertToEntity(bankDto);

        Bank savedBank = this.bankRepository.save(bankToSave);

        return Optional.of(
                this.bankMapper.convertToDto(savedBank)
        );
    }

    public Optional<BankDto> getBank(Long id) {

        Optional<Bank> bank = this.bankRepository.findById(id);
        if (bank.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(
                this.bankMapper.convertToDto(bank.get())
        );
    }


    public List<BankDto> getBanks() {
        return bankMapper.mapAllToDepartmentDto(bankRepository.findAll());
    }


    public void deleteBank(Long id) {
        bankRepository.deleteById(id);
    }



    public Optional<BankDto> updateBank(BankDto bankDto,Long id) {
        if (!bankRepository.existsBankById(id)) {
            return Optional.empty();
        }

        Optional<Bank> bankFromData = bankRepository.findById(id);
        if (bankFromData.isEmpty()){
            return Optional.empty();
        }
        Bank bankToSave = bankMapper.convertToEntity(bankDto);


        bankFromData.get().setId(bankToSave.getId());
        bankFromData.get().setName(bankToSave.getName());
        bankFromData.get().setType(bankToSave.getType());

        Bank savedBank = bankRepository.save(bankFromData.get());
        return Optional.of(bankMapper.convertToDto(savedBank));
    }

}
