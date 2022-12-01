package com.example.demo.Dto.Error;

import lombok.Getter;
import lombok.Setter;


@Getter
public class CoordinateParseException extends RuntimeException {
    private String message;
    public CoordinateParseException(String s) {
        message = s;
        getMessage();
    }

}
