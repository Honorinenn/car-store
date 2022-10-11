package com.company.carstore.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class CustomerErrorResponse {

    String errorMsg;
    int status;
    String errorCode;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    LocalDateTime  timestamp;

    public CustomerErrorResponse(String errorMsg, HttpStatus httpStatus) {
        this.errorMsg = errorMsg;
        this.status = httpStatus.value();
        this.errorCode = httpStatus.toString();
        this.timestamp = LocalDateTime.now();
    }
}
