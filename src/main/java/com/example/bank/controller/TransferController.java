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

    /**
     * Transferring money
     * @pathVariable "fromAccountToAccount" or
     *                "fromAccountToCard"   or
     *                "fromCardToAccount"   or
     *                "fromCardToCard"
     * requestBody {
     *     "from":5147552406426111,
     *     "to":5233210117020158,
     *     "amount":7000
     *             }
     */
    @PostMapping("/{fromTo}")
    public ResponseEntity<?> transfer(@RequestBody Transfer transfer,
                                      @PathVariable("fromTo")String fromTo) {
        logger.info("Received a request to do transfer.");
        switch (fromTo) {
            case "fromAccountToAccount":
                return transferService.fromAccountToAccount(transfer);
            case "fromAccountToCard":
                return transferService.fromAccountToCard(transfer);
            case "fromCardToAccount":
                return  transferService.fromCardToAccount(transfer);
            case "fromCardToCard":
                return transferService.fromCardToCard(transfer);
        }
        logger.warn("Transfer failed.Wrong transfer type.");
        return new TransferResponse<>().onFailure();
    }
}
