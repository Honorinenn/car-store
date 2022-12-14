package com.company.carstore.controller;

import com.company.carstore.exception.InvalidRequestException;
import com.company.carstore.exception.NoRecordFoundException;
import com.company.carstore.model.CustomErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = InvalidRequestException.class)
    public ResponseEntity<CustomErrorResponse> handleInvalidRequest(InvalidRequestException e) {
        CustomErrorResponse error = new CustomErrorResponse(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        ResponseEntity<CustomErrorResponse> responseEntity = new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
        return responseEntity;
    }

    @ExceptionHandler(value= NoRecordFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleNoRecordFound(NoRecordFoundException e) {
        CustomErrorResponse error = new CustomErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND);
        ResponseEntity<CustomErrorResponse> responseEntity = new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        return responseEntity;
    }

}
