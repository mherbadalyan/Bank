package com.example.bank.service;

import com.example.bank.model.dto.AddressDto;
import com.example.bank.model.dto.CardHolderDto;
import com.example.bank.model.entity.Address;
import com.example.bank.model.entity.CardHolder;
import com.example.bank.model.enums.Country;
import com.example.bank.model.mapper.CardHolderMapper;
import com.example.bank.repository.AddressRepository;
import com.example.bank.repository.CardHolderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CardHolderService {

    private final CardHolderMapper cardHolderMapper;

    private final CardHolderRepository cardHolderRepository;

    private final AddressRepository addressRepository;

    public Optional<CardHolderDto> createCardHolder(CardHolderDto cardHolderDto) {
        if (cardHolderRepository.existsCardHolderByPhone(cardHolderDto.getPhone())) {
            return Optional.empty();
        }
        CardHolder cardHolderToSave = cardHolderMapper.convertToEntity(cardHolderDto);

        if (addressRepository.existsAddressByCountryAndCity(
                cardHolderToSave.getAddress().getCountry(),
                cardHolderToSave.getAddress().getCity())) {
            Address existingAddress = addressRepository.getAddressByCountryAndCity(
                    cardHolderToSave.getAddress().getCountry(),
                    cardHolderToSave.getAddress().getCity());
            cardHolderToSave.setAddress(existingAddress);
        }

        CardHolder savedCardHolder = cardHolderRepository.save(cardHolderToSave);

        return Optional.of(cardHolderMapper.convertToDto(savedCardHolder));
    }

    public Optional<CardHolderDto> updateCardHolder(CardHolderDto cardHolderDto,String phone) {

        Optional<CardHolder> cardHolderFromData = cardHolderRepository.getByPhone(phone);
        if (cardHolderFromData.isEmpty()) {
            return Optional.empty();
        }
        CardHolder cardHolderToSave = cardHolderMapper.convertToEntity(cardHolderDto);

        if (addressRepository.existsAddressByCountryAndCity(
                cardHolderToSave.getAddress().getCountry(),
                cardHolderToSave.getAddress().getCity())) {
            Address existingAddress = addressRepository.getAddressByCountryAndCity(
                    cardHolderToSave.getAddress().getCountry(),
                    cardHolderToSave.getAddress().getCity());
            cardHolderFromData.get().setAddress(existingAddress);
        } else {
            cardHolderFromData.get().setAddress(cardHolderToSave.getAddress());
        }
        cardHolderFromData.get().setName(cardHolderToSave.getName());
        cardHolderFromData.get().setPhone(cardHolderToSave.getPhone());

        CardHolder savedCardHolder = cardHolderRepository.save(cardHolderFromData.get());
        return Optional.of(cardHolderMapper.convertToDto(savedCardHolder));
    }
@Transactional
    public void deleteCardHolder(String phone) {
        cardHolderRepository.removeByPhone(phone);
    }

    public Optional<CardHolderDto> getCardHolder(String phone) {
        Optional<CardHolder> cardHolderDto = cardHolderRepository.getByPhone(phone);

        if (cardHolderDto.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(cardHolderMapper.convertToDto(cardHolderDto.get()));
    }
}
