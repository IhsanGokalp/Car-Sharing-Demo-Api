package com.example.demo.Dto.Error;

public class OccupancyExceedException extends RuntimeException {

    private String message;
    public OccupancyExceedException(String message) {
        message = message;
    }
}
