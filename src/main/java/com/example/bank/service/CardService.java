package com.example.bank.service;

import com.example.bank.controller.CardController;
import com.example.bank.model.dto.CardDto;
import com.example.bank.model.entity.Account;
import com.example.bank.model.entity.Card;
import com.example.bank.model.enums.CardStatus;
import com.example.bank.model.enums.CardType;
import com.example.bank.model.mapper.CardMapper;
import com.example.bank.model.mapper.EncryptDecryptPassword;
import com.example.bank.repository.AccountRepository;
import com.example.bank.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardService {
    private final GeneratorService genServ;
    private static final Logger logger = LoggerFactory.getLogger(CardController.class);
    private final CardMapper cardMapper;
    private final CardRepository cardRepository;
    private final AccountRepository accountRepository;
    private final EncryptDecryptPassword encDec;

    private final AccountService accountService;

    public Optional<CardDto> createCard(CardDto cardDto) {
        if (accountRepository.existsById(cardDto.getAccountDto().getAccountNumber())) {

            Optional<Account> opAccountFromData = accountRepository.findById(cardDto.getAccountDto().getAccountNumber());
            if (opAccountFromData.isEmpty()) {
                return Optional.empty();
            }

            if (!opAccountFromData.get().getCard().isEmpty() &&
                    opAccountFromData.get().getCard().get(0).getType() != cardDto.getType()) {
                logger.warn("This account can't contain card with this card type");
                return Optional.empty();
            }

            Card cardToSave =  Card.builder().
                    paymentType(cardDto.getPaymentType()).
                    type(cardDto.getType()).
                    account(opAccountFromData.get()).build();


            if (isCredit(cardToSave.getType())) {
                cardToSave.getAccount().setBalance(50000L);
            }

            Long cardNumber;
            do {
                cardNumber= genServ.cardNumberGen16Digit(cardToSave.getPaymentType());
            }while (cardRepository.existsById(cardNumber));

            cardToSave.setCardNumber(cardNumber);

            cardToSave.setStatus(CardStatus.CREATED);

            String decryptCvv = genServ.generateCvv();
            String encryptCvv = encDec.encrypt(decryptCvv);
            cardToSave.setCvv(encryptCvv);

            String decryptPin = genServ.generatePin();
            String encryptPin = encDec.encrypt(decryptPin);
            cardToSave.setPin(encryptPin);

            Date date = Date.valueOf(LocalDate.now().plusYears(3));
            cardToSave.setExpDate(date);

            Card savedCard = cardRepository.save(cardToSave);

            decryptCvv = encDec.decrypt(savedCard.getCvv());
            decryptPin = encDec.decrypt(savedCard.getPin());
            savedCard.setPin(decryptPin);
            savedCard.setCvv(decryptCvv);

            return Optional.of(cardMapper.convertToDto(savedCard));
        }
        return Optional.empty();
    }

    private boolean isCredit(CardType type) {
        return type == CardType.CREDIT;
    }

    public Optional<CardDto> getCard(Long cardNumber) {
        Optional<Card> card = cardRepository.findById(cardNumber);

        if (card.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(cardMapper.convertToDto(card.get()));
    }

    public Optional<?> updateCard(Long cardNumber,CardStatus status) {

        Optional<Card> cardFromData = cardRepository.findById(cardNumber);
        if (cardFromData.isEmpty()) {
            logger.warn("There is not card with given parameter");
            return Optional.empty();
        }
        cardFromData.get().setStatus(status);
        Card changedCardFromData = cardRepository.save(cardFromData.get());
        if (changedCardFromData.getStatus() != status) {
            logger.warn("There is problem with required status. Card status not changed.");
            return Optional.empty();
        }
        return Optional.of(changedCardFromData.getStatus());
    }
}
