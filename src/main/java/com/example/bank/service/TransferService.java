package com.example.bank.service;

import com.example.bank.controller.CardController;
import com.example.bank.model.entity.Account;
import com.example.bank.model.entity.Card;
import com.example.bank.model.entity.Transfer;
import com.example.bank.model.enums.CardStatus;
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
    private final double RATE=0.015;

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

    public ResponseEntity<?> fromCardToCard(Transfer transfer) {
        Optional<Card> cardFrom = cardRepository.findById(transfer.getFrom());
        Optional<Card> cardTo = cardRepository.findById(transfer.getTo());

        if (cardFrom.isEmpty() || cardTo.isEmpty()) {
            logger.info("Incorrect card number");
            return new TransferResponse<Transfer>().incorrectCardNumber();
        }
        if (cardTo.get().getStatus() != CardStatus.ACTIVE ||
                cardFrom.get().getStatus() != CardStatus.ACTIVE) {
            logger.info("Selected card is not active");
            return new TransferResponse<Transfer>().blockedCard();
        }
        if (cardFrom.get().getAccount().getBalance() < transfer.getAmount()) {
            logger.info("Insufficient amount");
            return new TransferResponse<Transfer>().insufficientAmount();
        }

        Account accFrom = cardFrom.get().getAccount();
        Account accTo = cardTo.get().getAccount();

        accFrom.setBalance(accFrom.getBalance() - transfer.getAmount());
        accTo.setBalance(accTo.getBalance() + transfer.getAmount());

        accountRepository.save(accFrom);
        accountRepository.save(accTo);
        logger.info("Successfully money transfer");
        return new TransferResponse<Transfer>().onSuccess();
    }

    public ResponseEntity<?> fromAccountToCard(Transfer transfer) {
        Optional<Account> accFrom = accountRepository.findById(transfer.getFrom());
        Optional<Card> cardTo = cardRepository.findById(transfer.getTo());

        if (accFrom.isEmpty() || cardTo.isEmpty()) {
            logger.info("Incorrect account number");
            return new TransferResponse<Transfer>().incorrectAccount();
        }
        if (cardTo.get().getStatus() != CardStatus.ACTIVE) {
            logger.info("Selected card is not active");
            return new TransferResponse<Transfer>().blockedCard();
        }

        if (accFrom.get().getBalance() < transfer.getAmount()) {
            logger.info("Insufficient amount");
            return new TransferResponse<Transfer>().insufficientAmount();
        }
        Account accTo = cardTo.get().getAccount();

        accFrom.get().setBalance((accFrom.get().getBalance() - transfer.getAmount()));
        accTo.setBalance(accTo.getBalance() + transfer.getAmount());
        accountRepository.save(accFrom.get());
        accountRepository.save(accTo);
        logger.info("Successfully money transfer");
        return new TransferResponse<Transfer>().onSuccess();
    }

    public ResponseEntity<?> fromCardToAccount(Transfer transfer) {
        Optional<Card> cardFrom = cardRepository.findById(transfer.getFrom());
        Optional<Account> accTo = accountRepository.findById(transfer.getTo());

        if ( cardFrom.isEmpty() || accTo.isEmpty()) {
            logger.info("Incorrect account number");
            return new TransferResponse<Transfer>().incorrectAccount();
        }
        if (cardFrom.get().getStatus() != CardStatus.ACTIVE) {
            logger.info("Selected card is not active");
            return new TransferResponse<Transfer>().blockedCard();
        }
        if (cardFrom.get().getAccount().getBalance() < transfer.getAmount()) {
            logger.info("Insufficient amount");
            return new TransferResponse<Transfer>().insufficientAmount();
        }

        Account accFrom = cardFrom.get().getAccount();
        accFrom.setBalance(accFrom.getBalance() - transfer.getAmount());

        accTo.get().setBalance(accTo.get().getBalance()+ transfer.getAmount());

        accountRepository.save(accFrom);
        accountRepository.save(accTo.get());
        logger.info("Successfully money transfer");
        return new TransferResponse<Transfer>().onSuccess();
    }


}
