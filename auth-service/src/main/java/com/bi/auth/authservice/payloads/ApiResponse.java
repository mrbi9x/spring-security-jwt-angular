package com.bi.auth.authservice.payloads;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponse {
	private String message;
	private boolean success;
}
