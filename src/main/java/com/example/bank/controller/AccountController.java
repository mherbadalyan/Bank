package com.example.bank.controller;

import com.example.bank.model.dto.AccountDto;
import com.example.bank.model.entity.Account;
import com.example.bank.response.EntityCreatingResponse;
import com.example.bank.response.EntityDeletingResponse;
import com.example.bank.response.EntityLookupResponse;
import com.example.bank.response.EntityUpdatingResponse;
import com.example.bank.service.AccountService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("bank/account")
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    /**
     * Creating account
     * request body {
     *           "bankDto":{
     *                "id":205
     *                     },
     *     "cardHolderDto":{
     *             "phone":"055557121"
     *                     }
     *               }
     */
    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody AccountDto accountDto) {
        logger.info("Received a request to create an Account.");
        Optional<AccountDto> opAccount = accountService.createAccount(accountDto);

        if (opAccount.isEmpty()) {
            logger.warn("There is not Card holder ot Bank with this params.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    "There is not Card holder ot Bank with this params."
            );
        }
        logger.info("Account successfully created.");
        return new EntityCreatingResponse<AccountDto>().onSuccess(opAccount.get());
    }

    /**
     * Getting account by account number
     * @pathParam account number
     */
    @GetMapping("/{accountNumber}")
    public ResponseEntity<?> getCardHolder(@PathVariable("accountNumber") Long accountNumber) {
        logger.info("Received a request to get a Account.");
        Optional<AccountDto> accountDto = accountService.getAccount(accountNumber);

        if (accountDto.isPresent()) {
            logger.info("Account with given account number found");
            return new EntityLookupResponse<AccountDto>().onSuccess(accountDto.get());
        }
        logger.warn("There is not Account with given account number");
        return new EntityLookupResponse<AccountDto>().onFailure("Account");
    }

    /**
     * Deleting account by account number
     * @pathParam account number
     */
    @DeleteMapping("/{accountNumber}")
    public ResponseEntity<?> deleteAccount(@PathVariable("accountNumber") Long accountNummber) {
        logger.info("Received a request to delete a Account.");
        Optional<AccountDto> opAccount = accountService.getAccount(accountNummber);

        if (opAccount.isPresent()) {
            accountService.deleteAccount(accountNummber);
            return new EntityDeletingResponse<AccountDto>().onSuccess(opAccount.get(),"Card Holder");
        }
        logger.warn("There is not Account with given account number");
        return new EntityLookupResponse<AccountDto>().onFailure("Account");
    }

    /**
     * Updating account
     * request body {
     *           "bankDto":{
     *         "id":205
     *                     },
     *     "cardHolderDto":{
     *         "phone":"055557121"
     *                     }
     *               }
     */
    @PutMapping("/{accountNumber}")
    public ResponseEntity<?> updateAccount(@RequestBody AccountDto accountDto ,
                                              @PathVariable("accountNumber") Long accountNumber) {
        logger.info("Received a request to update an Account.");
        Optional<AccountDto> opAccount = accountService.updateAccount(accountDto,accountNumber);

        if (opAccount.isEmpty()) {
            logger.warn("There is not a Account with this account number.");
            return new EntityUpdatingResponse<Account>().onFailure("Account");
        }
        logger.info("Account successfully updated.");
        return new EntityUpdatingResponse<AccountDto>().onSuccess(opAccount.get());
    }
}
