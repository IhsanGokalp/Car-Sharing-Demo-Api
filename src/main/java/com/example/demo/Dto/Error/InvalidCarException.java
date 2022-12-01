package com.example.demo.Dto.Error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidCarException extends RuntimeException {
    private String message;
    public InvalidCarException(String s) {
        message = s;
    }
}
