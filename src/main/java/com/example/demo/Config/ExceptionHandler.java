package com.example.demo.Config;

import com.example.demo.Dto.Error.*;
import com.example.demo.Dto.Error.Response.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = OverAmountCustomerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected OverAmountCustomerResponse overAmountCustomerResponse(OverAmountCustomerException overAmountCustomerException) {
        OverAmountCustomerResponse overAmountCustomerResponse = new OverAmountCustomerResponse();
        overAmountCustomerResponse.setMessage(overAmountCustomerException.getMessage());
        overAmountCustomerResponse.setCode("400");
        return overAmountCustomerResponse;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = PointParseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected PointParseResponse pointParseResponse(PointParseException pointParseException) {
        PointParseResponse pointParseResponse = new PointParseResponse();
        pointParseResponse.setCode("400");
        pointParseResponse.setMessage(pointParseException.getMessage());
        return pointParseResponse;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = OccupancyExceedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected OccupancyExceedResponse occupancyExceedResponse(OccupancyExceedException e) {
        OccupancyExceedResponse pointParseResponse = new OccupancyExceedResponse();
        pointParseResponse.setCode("400");
        pointParseResponse.setMessage(e.getMessage());
        return pointParseResponse;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = InvalidCarException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected InvalidCarResponse invalidCarResponse(InvalidCarException e) {
        InvalidCarResponse invalidCarResponse = new InvalidCarResponse();
        invalidCarResponse.setCode(String.valueOf(HttpStatus.BAD_REQUEST));
        invalidCarResponse.setMessage(e.getMessage());
        return invalidCarResponse;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected EntityNotFoundResponse entityNotFoundResponse(EntityNotFoundException e) {
        EntityNotFoundResponse entityNotFoundResponse = new EntityNotFoundResponse();
        entityNotFoundResponse.setMessage(e.getMessage());
        entityNotFoundResponse.setCode(String.valueOf(HttpStatus.BAD_REQUEST));
        return entityNotFoundResponse;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(CoordinateParseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected CoordinateParseResponse coordinateParseResponse(CoordinateParseException e) {
        CoordinateParseResponse coordinateParseResponse = new CoordinateParseResponse();
        coordinateParseResponse.setCode(String.valueOf(HttpStatus.BAD_REQUEST));
        coordinateParseResponse.setMessage(e.getMessage());
        return coordinateParseResponse;
    }
}
