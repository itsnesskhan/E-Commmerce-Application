package com.ecom.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressDto {
	
	private Integer aid;
	
	private String street;
	
	private String city;
	
	private String state;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private UserDto user;
	
	
	

}
