package com.bi.auth.authservice.payloads;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ApiErrorsResponse {
    List<ErrorResponse> errors;
}