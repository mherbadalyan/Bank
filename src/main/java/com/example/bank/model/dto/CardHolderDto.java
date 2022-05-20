package com.example.bank.model.dto;

import com.example.bank.model.entity.Address;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardHolderDto {

    private Long id;

    private String name;

    private String phone;

    private AddressDto addressDto;
}
