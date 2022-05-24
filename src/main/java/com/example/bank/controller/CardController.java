package com.example.bank.controller;

import com.example.bank.model.dto.CardDto;
import com.example.bank.model.entity.CardStatusRequest;
import com.example.bank.model.enums.CardStatus;
import com.example.bank.response.EntityCreatingResponse;
import com.example.bank.response.EntityLookupResponse;
import com.example.bank.service.CardService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("bank/card")
@AllArgsConstructor
public class CardController {

    private static final Logger logger = LoggerFactory.getLogger(CardController.class);

    private final CardService cardService;

    @PostMapping
    public ResponseEntity<?> createCard(@RequestBody CardDto cardDto) {
        logger.info("Received a request to create a Card.");
        Optional<CardDto> optionalCardDto = cardService.createCard(cardDto);

        if (optionalCardDto.isEmpty()) {
            logger.warn("There is a problem with input parameters, please check it and try again.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body("There is a problem with input parameters, please check it and try again.");
        }
        logger.info("Card successfully created.");
        return new EntityCreatingResponse<CardDto>().onSuccess(optionalCardDto.get());
    }

    @GetMapping("/{cardNumber}")
    public ResponseEntity<?> getCard(@PathVariable("cardNumber") Long cardNumber) {
        logger.info("Received a request to get an Card.");
        Optional<CardDto> cardDto = cardService.getCard(cardNumber);

        if (cardDto.isPresent()) {
            logger.info("Card with given id found");
            return new EntityLookupResponse<CardDto>().onSuccess(cardDto.get());
        }
        logger.warn("There is not Card with given id");
        return new EntityLookupResponse<CardDto>().onFailure("Card");
    }

    @PutMapping("/")
    public ResponseEntity<?> updateCard(@RequestBody CardStatusRequest request) {

        logger.info("Received a request to change card status.");
        Optional<?> response = cardService.updateCard(request.getCardNumber(), request.getCardStatus());

        if (response.isPresent()) {
            logger.info("Card status changed to " + request.getCardStatus().name());
            return new EntityLookupResponse<CardStatus>().onSuccess((CardStatus)response.get());
        }
        logger.warn("Status changing failed.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body("There is a problem with changing card status.Please enter correct parameters.");

    }
}
