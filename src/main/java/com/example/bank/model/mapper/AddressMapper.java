package com.example.bank.model.mapper;

import com.example.bank.model.dto.AddressDto;
import com.example.bank.model.entity.Address;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AddressMapper implements BaseMapper<Address, AddressDto>{

    @Override
    public Address convertToEntity(AddressDto dto) {
        Address address = new Address();
        address.setCountry(dto.getCountry());
        address.setCity(dto.getCity());
        return address;
    }

    @Override
    public AddressDto convertToDto(Address entity) {
        return AddressDto.builder().
                id(entity.getId()).
                country(entity.getCountry()).
                city(entity.getCity()).build();
    }
}
