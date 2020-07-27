package com.bi.auth.authservice.payloads;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private HttpStatus satusCode;
    @JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss Z", locale = "vi_VN")
    private Date timestamp;
    private String message;
    private String details;
}