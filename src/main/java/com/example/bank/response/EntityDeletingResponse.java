package com.example.bank.response;

import org.springframework.http.ResponseEntity;

public class EntityDeletingResponse<T> {

    public ResponseEntity<?> onSuccess(T entityDto) {
        return ResponseEntity.ok().
                body("The following address was successfully deleted \n" + entityDto);
    }
}
