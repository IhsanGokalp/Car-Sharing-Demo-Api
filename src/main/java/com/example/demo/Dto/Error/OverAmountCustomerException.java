package com.example.demo.Dto.Error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OverAmountCustomerException extends RuntimeException{
    private String message;
    public OverAmountCustomerException(String message) {
        message = message;
    }
}
