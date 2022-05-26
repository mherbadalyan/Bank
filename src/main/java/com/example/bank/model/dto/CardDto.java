package com.example.bank.model.dto;

import com.example.bank.model.enums.CardPaymentType;
import com.example.bank.model.enums.CardStatus;
import com.example.bank.model.enums.CardType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

@Data
@Builder
public class CardDto {

    private Long cardNumber;

    private CardType type;

    private CardPaymentType paymentType;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Date expDate;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String cvv;

    private CardStatus status;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String pin;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private AccountDto accountDto;


    private String FormatDate(LocalDate localDate)  {
        String date_s = String.valueOf(localDate);
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = null;
        try {
            date = dt.parse(date_s);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        SimpleDateFormat dt1 = new SimpleDateFormat("MM/yyyy");
        String dateToReturn = dt1.format(date);
        return  dateToReturn ;
    }
    @Override
    public String toString() {
        return  "cardNumber=" + cardNumber +
                ",\ntype=" + type +
                ",\npaymentType=" + paymentType +
                ",\nexpDate=" + FormatDate(expDate.toLocalDate()) +
                ",\nstatus=" + status ;
    }
}
