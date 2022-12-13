package com.ecom.dtos;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.ecom.model.Name;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
	
	private Integer uid;
	
	@Valid
	private Name name;
	
	@Email(regexp = "^[A-Za-z0-9+_.-]+@(.+)$",message = "Enter a valid email address")
	@NotEmpty(message = "Email cannot be empty")
	private String email;

	@NotEmpty(message = "password cannot be empty")
	private String password;
	
	@NotNull(message = "mobile number cannot be empty")
	@Pattern(regexp = "(0|91)?[6-9][0-9]{9}",message = "enter a valid mobile number")
//	@Size(min = 10,max = 10, message = "Enter a valid mobile number")
	private String mobileNumber;
	
	@JsonProperty(access = Access.READ_ONLY)
	private String profileUrl;
	
	private List<AddressDto> address = new ArrayList<>();


}
