package com.ecom.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Name {
	
	@NotEmpty(message = "first name cannot be empty")
	@Column(name = "first_name")
	private String fname;
	
	@NotEmpty(message = "last name cannot be empty")
	@Column(name = "last_name")
	private String lname;

}
