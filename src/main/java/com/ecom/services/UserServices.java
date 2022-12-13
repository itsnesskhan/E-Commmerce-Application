package com.ecom.services;

import java.util.List;
import java.util.Set;

import com.ecom.dtos.AddressDto;
import com.ecom.dtos.UserDto;
import com.ecom.model.UserRole;

public interface UserServices {

	UserDto addUser(UserDto userDto, Set<UserRole> roles);

	List<UserDto> getAllUsers();
	
	UserDto getJson(String userString);
	
	UserDto getUserById(Integer uid);

	UserDto updateUser(UserDto userDto);

	void deleteUser(Integer uid);

	AddressDto addAddress(AddressDto addressDto);

	AddressDto getAddress(Integer aid);
	
	AddressDto updateAddress(AddressDto addressDto);
	
	void deleteAddress(Integer aid);

}
