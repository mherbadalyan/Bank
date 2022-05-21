package com.example.bank.service;

import com.example.bank.model.dto.AddressDto;
import com.example.bank.model.entity.Address;
import com.example.bank.model.mapper.AddressMapper;
import com.example.bank.repository.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AddressService {
    private final AddressMapper addressMapper;

    private final AddressRepository addressRepository;

    public Optional<AddressDto> createAddress(AddressDto addressDto) {
        if (addressRepository.existsAddressByCountryAndCity(addressDto.getCountry(),addressDto.getCity())) {
            return Optional.empty();
        }

        Address addressToSave = addressMapper.convertToEntity(addressDto);

        Address savedAddress = addressRepository.save(addressToSave);

        return Optional.of(addressMapper.convertToDto(savedAddress));
    }

    public Optional<AddressDto> getAddress(Long id) {
        Optional<Address> address = addressRepository.findById(id);

        if (address.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(addressMapper.convertToDto(address.get()));
    }

    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }
}
