package com.example.bank.service;

import com.example.bank.model.enums.CardPaymentType;
import org.springframework.stereotype.Service;

import java.util.Random;
@Service
public class GeneratorService {
    public long accountNumberGen16Digit(Long bankId) {
        Random rng =new Random();
        return bankId * 10000000000000L + ((rng.nextLong() % 100000000000L) + 5200000000000L);
    }

    public String ibanGenerate(String country,Long bankId) {
        Random rng = new Random();
        long first16 = (rng.nextLong() % 100000000000000L) + 5200000000000000L;
        return country + bankId + first16 ;
    }

    public Long cardNumberGen16Digit(CardPaymentType cpt) {
        Random rng = new Random();
        long randNum  = (rng.nextLong() % 10000000000000L) + 520000000000000L;
        switch (cpt) {
            case VISA:
                return 4000000000000000L + randNum;
            case MASTER_CARD:
                return 5000000000000000L + randNum;
            case AMERICAN_EXPRESS:
                return 3000000000000000L + randNum;
        }
        return -1L;
    }

    public String generateCvv() {
        Random rnd = new Random();
        return String.valueOf(rnd.nextInt(900) + 100);
    }

    public String generatePin() {
        Random rnd = new Random();
        return String.valueOf(rnd.nextInt(9000) + 1000);
    }
}
