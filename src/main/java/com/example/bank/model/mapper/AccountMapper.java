package com.example.bank.model.mapper;

import com.example.bank.model.dto.AccountDto;
import com.example.bank.model.entity.Account;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;



@Component
@AllArgsConstructor
public class AccountMapper implements BaseMapper <Account,AccountDto> {

    private BankMapper bankMapper;
    private CardHolderMapper cardHolderMapper;


    @Override
    public Account convertToEntity(AccountDto dto) {
        Account account=new Account();

        account.setAccountNumber(dto.getAccountNumber());
        account.setIBAN(dto.getIBAN());
        account.setBalance(dto.getBalance());
        account.setBank(bankMapper.convertToEntity(dto.getBankDto()));
        account.setCardHolder(cardHolderMapper.convertToEntity(dto.getCardHolderDto()));
        return account;
    }

    @Override
    public AccountDto convertToDto(Account entity) {
      return  AccountDto.builder().accountNumber(entity.getAccountNumber()).
              IBAN(entity.getIBAN()).
              balance(entity.getBalance()).
              bankDto(bankMapper.convertToDto(entity.getBank())).
              cardHolderDto(cardHolderMapper.convertToDto(entity.getCardHolder()))
              .build();

    }

}



