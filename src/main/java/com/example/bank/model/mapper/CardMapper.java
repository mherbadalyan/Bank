package com.example.bank.model.mapper;

import com.example.bank.model.dto.CardDto;
import com.example.bank.model.entity.Card;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class CardMapper implements BaseMapper<Card, CardDto>{

    private AccountMapper accountMapper;
    @Override
    public Card convertToEntity(CardDto dto) {
        Card card = new Card();
        card.setCardNumber(dto.getCardNumber());
        card.setAccount(accountMapper.convertToEntity(dto.getAccountDto()));
        return card;
    }

    @Override
    public CardDto convertToDto(Card entity) {
        return CardDto.builder().
                cardNumber(entity.getCardNumber()).
                type(entity.getType()).
                paymentType(entity.getPaymentType()).
                expDate(entity.getExpDate()).
                cvv(entity.getCvv()).
                pin(entity.getPin()).
                status(entity.getStatus()).
                accountDto(accountMapper.convertToDto(entity.getAccount())).build();
    }

}
