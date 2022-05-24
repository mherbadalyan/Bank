package com.example.bank;

import com.example.bank.repository.CardRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BankApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(CardRepository cardRepository) {
        return args -> {
            cardRepository.findById(5126271022659425L);
        };
    }

}
