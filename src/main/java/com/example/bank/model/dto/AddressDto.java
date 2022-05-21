package com.example.bank.model.dto;

import com.example.bank.model.enums.Country;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDto {

    private Long id;

    private Country country;

    private String city;

    @Override
    public String toString() {
        return "country = " + country +
                ", city = " + city ;
    }
}
