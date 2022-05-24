package com.example.bank.model.mapper;

import com.example.bank.model.dto.CardHolderDto;
import com.example.bank.model.entity.CardHolder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
@Component
@AllArgsConstructor
public class CardHolderMapper implements BaseMapper<CardHolder, CardHolderDto>{

    private AddressMapper addressMapper;

    @Override
    public CardHolder convertToEntity(CardHolderDto dto) {
        CardHolder cardHolder = new CardHolder();

        cardHolder.setId(dto.getId());
        cardHolder.setName(dto.getName());
        cardHolder.setPhone(dto.getPhone());
        cardHolder.setAddress(addressMapper.convertToEntity(dto.getAddressDto()));

        return cardHolder;
    }

    @Override
    public CardHolderDto convertToDto(CardHolder entity) {
        return CardHolderDto.builder().
                id(entity.getId()).
                name(entity.getName()).
                phone(entity.getPhone()).
        addressDto((addressMapper.convertToDto(entity.getAddress()))).build();
    }

}
