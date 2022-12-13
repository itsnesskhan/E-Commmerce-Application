package com.ecom.dtos;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDto {

	private Integer oid;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonProperty(access = Access.READ_ONLY)
	private LocalDateTime createdAt;
	
	private String status;
	
	private List<OrderitemDto> orderItems;
	
	private UserDto user;
}
