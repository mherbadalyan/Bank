package com.example.bank.controller;


import com.example.bank.model.dto.CardHolderDto;
import com.example.bank.model.entity.CardHolder;
import com.example.bank.response.EntityCreatingResponse;
import com.example.bank.response.EntityDeletingResponse;
import com.example.bank.response.EntityLookupResponse;
import com.example.bank.response.EntityUpdatingResponse;
import com.example.bank.service.CardHolderService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("bank/cardHolder")
@AllArgsConstructor
public class CardHolderController {
    private final CardHolderService cardHolderService;

    private static final Logger logger = LoggerFactory.getLogger(CardController.class);

    /**
     * Creating cardHolder
     * requestBody {
     *          "name":"Suren Badalyan",
     *         "phone":"055557151",
     *    "addressDto":{
     *       "country":"ARMENIA",
     *          "city":"Aparan"
     *                }
     * }
     */
    @PostMapping
    public ResponseEntity<?> createCardHolder(@RequestBody CardHolderDto cardHolderDto) {
        logger.info("Received a request to create an CardHolder.");
        Optional<CardHolderDto> opCardHold = cardHolderService.createCardHolder(cardHolderDto);

        if (opCardHold.isEmpty()) {
            logger.warn("There is an Card Holder with this phone number.");
            return new EntityCreatingResponse<CardHolder>().onFailure("CardHolder");
        }
        logger.info("CardHolder successfully created.");
        return new EntityCreatingResponse<CardHolderDto>().onSuccess(opCardHold.get());
    }

    /**
     * Updating card holder
     * request body {
     *     "name":"Suren Badalyan",
     *     "phone":"055557151",
     *     "addressDto":{
     *         "country":"ARMENIA",
     *         "city":"Aparan"
     *     }
     * }
     * @pathVariable card holder phone
     */
    @PutMapping("/{phone}")
    public ResponseEntity<?> updateCardHolder(@RequestBody CardHolderDto cardHolderDto ,
                                              @PathVariable("phone") String phone) {
        logger.info("Received a request to update an CardHolder.");
        Optional<CardHolderDto> opCardHold = cardHolderService.updateCardHolder(cardHolderDto,phone);

        if (opCardHold.isEmpty()) {
            logger.warn("There is not a Card Holder with this phone number.");
            return new EntityUpdatingResponse<CardHolder>().onFailure("CardHolder");
        }
        logger.info("CardHolder successfully updated.");
        return new EntityUpdatingResponse<CardHolderDto>().onSuccess(opCardHold.get());
    }

    /**
     * Deleting cardHolder by phone
     * @pathVariable cardHolder phone
     */
    @DeleteMapping("/{phone}")
    public ResponseEntity<?> deleteCardHolder(@PathVariable("phone") String phone) {
        logger.info("Received a request to delete a Card Holder.");
        Optional<CardHolderDto> opCardHold = cardHolderService.getCardHolder(phone);

        if (opCardHold.isPresent()) {
            cardHolderService.deleteCardHolder(phone);
            return new EntityDeletingResponse<CardHolderDto>().onSuccess(opCardHold.get(),"Card Holder");
        }
        logger.warn("There is not address with given id");
        return new EntityLookupResponse<CardHolderDto>().onFailure("Card Holder");
    }

    /**
     * Getting cardHolder by phone
     * @pathVariable cardHolder phone
     */
    @GetMapping("/{phone}")
    public ResponseEntity<?> getCardHolder(@PathVariable("phone") String phone) {
        logger.info("Received a request to get a Card Holder.");
        Optional<CardHolderDto> cardHolderDto = cardHolderService.getCardHolder(phone);

        if (cardHolderDto.isPresent()) {
            logger.info("Card Holder with given phone found");
            return new EntityLookupResponse<CardHolderDto>().onSuccess(cardHolderDto.get());
        }
        logger.warn("There is not Card Holder with given id");
        return new EntityLookupResponse<CardHolderDto>().onFailure("Card Holder");
    }
}
