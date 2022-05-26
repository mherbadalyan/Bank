package com.example.bank.controller;

import com.example.bank.model.dto.AddressDto;
import com.example.bank.response.EntityCreatingResponse;
import com.example.bank.response.EntityDeletingResponse;
import com.example.bank.response.EntityLookupResponse;
import com.example.bank.service.AddressService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/bank/address")
@AllArgsConstructor
public class AddressController {

    private final AddressService addressService;
    private static final Logger logger = LoggerFactory.getLogger(AddressController.class);

    /**
     * Creating address
     * request body example
     *          {
     *         "country":"ARMENIA",
     *         "city":"Aparan"
     *          }
     * @return ResponseEntity
     */
    @PostMapping
    public ResponseEntity<?> createAddress(@RequestBody AddressDto addressDto) {
        logger.info("Received a request to create an address.");
        Optional<AddressDto> adr = addressService.createAddress(addressDto);

        if (adr.isEmpty()) {
            logger.warn("There is an address with this Country and City.");
            return new EntityCreatingResponse<AddressDto>().onFailure("Address");
        }
        logger.info("Address successfully created.");
        return new EntityCreatingResponse<AddressDto>().onSuccess(adr.get());
    }

    /**
     * Searching address by id
     * @pathParam address id
     * @return ResponseEntity
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getAddress(@PathVariable("id") Long id) {
        logger.info("Received a request to get an address.");
        Optional<AddressDto> adr = addressService.getAddress(id);

        if (adr.isPresent()) {
            logger.info("Address with given id found");
            return new EntityLookupResponse<AddressDto>().onSuccess(adr.get());
        }
        logger.warn("There is not address with given id");
        return new EntityLookupResponse<AddressDto>().onFailure("Address");
    }

    /**
     * deleting address by id
     * @pathVariable address id
     * @return ResponseEntity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable("id") Long id) {
        logger.info("Received a request to delete an address.");
        Optional<AddressDto> adr = addressService.getAddress(id);

        if (adr.isPresent()) {
            addressService.deleteAddress(id);
            return new EntityDeletingResponse<AddressDto>().onSuccess(adr.get(),"Address");
        }
        logger.warn("There is not address with given id");
        return new EntityLookupResponse<AddressDto>().onFailure("Address");
    }

}
