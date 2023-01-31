package com.ecom.services;

import java.util.List;
import java.util.Set;

import com.ecom.dtos.AddressDto;
import com.ecom.dtos.JwtRequest;
import com.ecom.dtos.UserDto;
import com.ecom.model.UserRole;

public interface UserServices<T> {

	UserDto addUser(UserDto userDto);

	List<UserDto> getAllUsers();
	
	UserDto getJson(String userString);
	
	UserDto getUserById(Integer uid);

	T updateUser(UserDto userDto);

	void deleteUser(Integer uid);
	
	T loginUser(JwtRequest jwtRequest);


}
