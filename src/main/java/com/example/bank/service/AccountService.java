package com.example.bank.service;

import com.example.bank.model.dto.AccountDto;
import com.example.bank.model.entity.Account;
import com.example.bank.model.entity.Bank;
import com.example.bank.model.entity.CardHolder;
import com.example.bank.model.mapper.AccountMapper;
import com.example.bank.model.mapper.BankMapper;
import com.example.bank.model.mapper.CardHolderMapper;
import com.example.bank.repository.AccountRepository;
import com.example.bank.repository.BankRepository;
import com.example.bank.repository.CardHolderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountService{
    private GeneratorService genServ;
    private final AccountMapper accountMapper;

    private final AccountRepository accountRepository;

    private final BankRepository bankRepository;

    private final CardHolderRepository cardHolderRepository;

    private final CardHolderMapper cardHolderMapper;

    private final BankMapper bankMapper;


    public Optional<AccountDto> createAccount(AccountDto accountDto) {
        Optional<CardHolder> existingCardHolder = cardHolderRepository.getByPhone(
                        accountDto.getCardHolderDto().getPhone());

        if (existingCardHolder.isEmpty()) {
            return Optional.empty();
        }
        accountDto.setCardHolderDto(cardHolderMapper.convertToDto(existingCardHolder.get()));

        Optional<Bank> existingBank = bankRepository.findById(accountDto.getBankDto().getId());

        if (existingBank.isEmpty()) {
            return Optional.empty();
        }

        accountDto.setBankDto(bankMapper.convertToDto(existingBank.get()));


        Long accountNumber ;
        do {
            accountNumber= genServ.accountNumberGen16Digit(existingBank.get().getId());
        }while (accountRepository.existsById(accountNumber));

        String iban = genServ.ibanGenerate(
                existingCardHolder.get().getAddress().getCountry().name().substring(0,3),
                accountDto.getBankDto().getId());

        accountDto.setAccountNumber(accountNumber);
        accountDto.setIBAN(iban);
        accountDto.setBalance(0L);

        Account accountToSave = accountMapper.convertToEntity(accountDto);

        Account savedAccount = accountRepository.save(accountToSave);

        return Optional.of(accountMapper.convertToDto(savedAccount));
    }

    public Optional<AccountDto> updateAccount(AccountDto accountDto,Long accountNumber) {
        if (!accountRepository.existsAccountByAccountNumber(accountNumber)) {
            return Optional.empty();
        }

        Optional<Account> accountFromData = accountRepository.findById(accountNumber);
        if (accountFromData.isEmpty()) {
            return Optional.empty();
        }
        Account accountToSave = accountMapper.convertToEntity(accountDto);

        Optional<CardHolder> existingCardHolder =
                cardHolderRepository.getByPhone(
                        accountDto.getCardHolderDto().getPhone());
        if (existingCardHolder.isPresent()) {
            accountDto.setCardHolderDto(cardHolderMapper.
                    convertToDto(existingCardHolder.get()));
            accountFromData.get().setCardHolder(existingCardHolder.get());
        }else{
            accountFromData.get().setCardHolder(accountToSave.getCardHolder());
        }
        if (bankRepository.existsBankById(
                accountDto.getBankDto().getId())) {
            Bank existingBank = bankRepository.getById(
                    accountDto.getBankDto().getId());
            accountDto.setBankDto(bankMapper.convertToDto(existingBank));
            accountFromData.get().setBank(existingBank);
        }else{
            accountFromData.get().setBank(accountToSave.getBank());
        }
        accountFromData.get().setBalance(accountToSave.getBalance());
        Account savedAccount = accountRepository.save(accountFromData.get());
        return Optional.of(accountMapper.convertToDto(savedAccount));
    }

    public void deleteAccount(Long accountNumber) {
        accountRepository.deleteById(accountNumber);
    }

    public Optional<AccountDto> getAccount(Long accountNumber) {
        Optional<Account> accountDto = accountRepository.findById(accountNumber);

        if (accountDto.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(accountMapper.convertToDto(accountDto.get()));
    }
}
