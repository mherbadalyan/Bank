package com.example.bank.service;

import com.example.bank.model.dto.AccountDto;
import com.example.bank.model.dto.CardHolderDto;
import com.example.bank.model.entity.Account;
import com.example.bank.model.entity.Address;
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
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountService{

    private final AccountMapper accountMapper;

    private final AccountRepository accountRepository;

    private final BankRepository bankRepository;

    private final CardHolderRepository cardHolderRepository;

    private final CardHolderMapper cardHolderMapper;

    private final BankMapper bankMapper;



    private long cardNumberGen16Digit() {
        Random rng =new Random();
        long first16 = (rng.nextLong() % 100000000000000L) + 5200000000000000L;
        return first16;
    }


    private String ibanGenerate() {
        Random rng =new Random();
        long first16 = (rng.nextLong() % 100000000000000L) + 5200000000000000L;
        return "AM"+first16;
    }





    public Optional<AccountDto> createAccount(AccountDto accountDto) {
        if (!cardHolderRepository.existsCardHolderByPhone(
                accountDto.getCardHolderDto().getPhone())) {
            return Optional.empty();
        }
        if (!bankRepository.existsBankById(accountDto.getBankDto().getId())) {
            return Optional.empty();
        }

//        if (cardHolderRepository.existsCardHolderByPhone(
//                accountDto.getCardHolderDto().getPhone())) {
//            Optional<CardHolder> existingCardHolder =
//                    cardHolderRepository.getByPhone(
//                            accountDto.getCardHolderDto().getPhone());
//            accountDto.setCardHolderDto(cardHolderMapper.
//                    convertToDto(existingCardHolder.get()));
//        }

        Optional<CardHolder> existingCardHolder =
                cardHolderRepository.getByPhone(
                        accountDto.getCardHolderDto().getPhone());
        if (existingCardHolder.isPresent()) {
            accountDto.setCardHolderDto(cardHolderMapper.
                    convertToDto(existingCardHolder.get()));
        }

        if (bankRepository.existsBankById(
                accountDto.getBankDto().getId())) {
            Bank existingBank = bankRepository.getById(
                    accountDto.getBankDto().getId());
            accountDto.setBankDto(bankMapper.convertToDto(existingBank));
        }

        //need Update
        Long accountNumber1 = cardNumberGen16Digit();
        String iban = ibanGenerate()+accountDto.getBankDto().getId();

        Account accountToSave = accountMapper.convertToEntity(accountDto);


        accountToSave.setAccountNumber(accountNumber1);
        accountToSave.setIBAN(iban);
        accountToSave.setBalance(0L);



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
