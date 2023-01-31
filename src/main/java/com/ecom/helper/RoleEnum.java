package com.ecom.helper;
public enum RoleEnum {

	CUSTOMER("CUSTOMER"), ADMIN("ADMIN");

	// declaring private variable for getting values
	private String role;

	// getter method
	public String getRole() {
		return this.role;
	}

	// enum constructor - cannot be public or protected
	private RoleEnum(String role) {
		this.role = role;
	}
}