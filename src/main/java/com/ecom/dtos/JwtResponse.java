
package com.ecom.dtos;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class JwtResponse {

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate createdDate;
	
	private String createdBy;
	
	private String email;
	
	private String token;
	
	private String refreshToken;
	
	private String authority;
	
	private String username;
	
	private long tokenExpriyTime;
}
