package com.example.bank.service;

import com.example.bank.controller.CardController;
import com.example.bank.model.entity.Account;
import com.example.bank.model.entity.Transfer;
import com.example.bank.repository.AccountRepository;
import com.example.bank.repository.CardRepository;
import com.example.bank.response.TransferResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransferService<T> {
    private static final Logger logger = LoggerFactory.getLogger(CardController.class);

    private final CardRepository cardRepository;

    private final AccountRepository accountRepository;
    public ResponseEntity<?> fromAccountToAccount(Transfer transfer) {
        Optional<Account> accFrom = accountRepository.findById(transfer.getFrom());
        Optional<Account> accTo = accountRepository.findById(transfer.getTo());

        if (accFrom.isEmpty() || accTo.isEmpty()) {
            logger.info("Incorrect account number");
            return new TransferResponse<Transfer>().incorrectAccount();
        }
        if (accFrom.get().getBalance() < transfer.getAmount()) {
            logger.info("Insufficient amount");
            return new TransferResponse<Transfer>().insufficientAmount();
        }

        accFrom.get().setBalance(accFrom.get().getBalance() - transfer.getAmount());
        accTo.get().setBalance(accTo.get().getBalance() + transfer.getAmount());

        accountRepository.save(accFrom.get());
        accountRepository.save(accTo.get());
        logger.info("Successfully money transfer");
        return new TransferResponse<Transfer>().onSuccess();
    }
}
