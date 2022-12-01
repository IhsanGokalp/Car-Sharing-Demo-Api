package com.example.demo.Dto.Error;

import lombok.Getter;
import lombok.Setter;
import com.vividsolutions.jts.io.ParseException;

@Getter
@Setter
public class PointParseException extends RuntimeException {
    private String string;
    public PointParseException(ParseException e) {
        string= e.getMessage();
    }
}
