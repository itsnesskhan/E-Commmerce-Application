package com.ecom.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TokenRefreshResponseDto {
	private String refreshToken;
	private String token;
	private String tokenType;
}