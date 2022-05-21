package com.example.bank.repository;

import com.example.bank.model.entity.Address;
import com.example.bank.model.enums.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
    boolean existsAddressByCityAndCountry(String city,Country country);
}
