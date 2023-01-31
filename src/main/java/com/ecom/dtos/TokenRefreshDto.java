package com.ecom.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TokenRefreshDto {

	private String refreshToken;
}
