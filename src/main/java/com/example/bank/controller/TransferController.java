package com.example.bank.controller;

import com.example.bank.model.dto.CardDto;
import com.example.bank.model.entity.Card;
import com.example.bank.model.entity.Transfer;
import com.example.bank.response.TransferResponse;
import com.example.bank.service.TransferService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("bank/transfer")
@AllArgsConstructor
public class TransferController {

    private static final Logger logger = LoggerFactory.getLogger(CardController.class);

    private final TransferService<Transfer> transferService;

    @PostMapping("/{fromTo}")
    public ResponseEntity<?> transfer(@RequestBody Transfer transfer,
                                      @PathVariable("fromTo")String fromTo) {
        logger.info("Received a request to do transfer.");
        switch (fromTo) {
            case "fromAccountToAccount":
                return transferService.fromAccountToAccount(transfer);
            case "fromAccountToCard":
                break;
            case "fromCardToAccount":
                break;
            case "fromCardToCard":
                return transferService.fromCardToCard(transfer);
        }
        logger.warn("Transfer failed.Wrong transfer type.");
        return new TransferResponse<>().onFailure();
    }
}
