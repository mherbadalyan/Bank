package com.example.bank.controller;
import com.example.bank.model.dto.BankDto;
import com.example.bank.model.entity.Bank;
import com.example.bank.response.EntityCreatingResponse;
import com.example.bank.response.EntityDeletingResponse;
import com.example.bank.response.EntityLookupResponse;
import com.example.bank.response.EntityUpdatingResponse;
import com.example.bank.service.BankService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@RestController
@RequestMapping("/bank/")
public class BankController {

    private final BankService bankService;
    private static final Logger logger = LoggerFactory.getLogger(CardController.class);

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    /**
     * Creating bank
     * request body {
     *            "id":"3digit number"
     *          "name":"Ineco",
     *          "type":"CENTRAL"
     *               }
     */
    @PostMapping
    public ResponseEntity<?> createBank(@RequestBody BankDto bankDto) {
        logger.info("Received a request to create an Bank.");
        Optional<BankDto> opBank = bankService.createBank(bankDto);

        if (opBank.isEmpty()) {
            logger.warn("There is an Bank.");
            return new EntityCreatingResponse<Bank>().onFailure("Bank");
        }
        logger.info("Bank successfully created.");
        return new EntityCreatingResponse<BankDto>().onSuccess(opBank.get());
    }

    /**
     * Getting bank by id
     * @pathVariable bank id
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getBank(@PathVariable("id") Long id) {
        logger.info("Received a request to get a Bank.");
        Optional<BankDto> bankDto = bankService.getBank(id);

        if (bankDto.isPresent()) {
            logger.info("Bank  with given id found");
            return new EntityLookupResponse<BankDto>().onSuccess(bankDto.get());
        }
        logger.warn("There is not Bank with given id");
        return new EntityLookupResponse<BankDto>().onFailure("bank");
    }

    /**
     * Deleting bank by id
     * @pathVariable bank id
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBank(@PathVariable("id") Long id) {
        logger.info("Received a request to delete a Bank.");
        Optional<BankDto> optionalBankDto = bankService.getBank(id);

        if (optionalBankDto.isPresent()) {
            bankService.deleteBank(id);
            return new EntityDeletingResponse<BankDto>().onSuccess(optionalBankDto.get(),"bank");
        }
        logger.warn("There is not bank with given id");
        return new EntityLookupResponse<BankDto>().onFailure("bank");
    }

    /**
     * Updating exist bank
     * request body {
     *       "name":"Ineco",
     *       "type":"CENTRAL"
     *              }
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBank(@RequestBody BankDto bankDto ,
                                              @PathVariable("id") Long id) {
        logger.info("Received a request to update an Bank.");
        Optional<BankDto> bankDtoOptional = bankService.updateBank(bankDto,id);

        if (bankDtoOptional.isEmpty()) {
            logger.warn("There is not a Bank with this Id.");
            return new EntityUpdatingResponse<Bank>().onFailure("Bank");
        }
        logger.info("Bank successfully updated.");
        return new EntityUpdatingResponse<BankDto>().onSuccess(bankDtoOptional.get());
    }
}
