package com.ecom.services;

import java.util.List;

import com.ecom.dtos.AddressDto;

public interface AddressService {

	AddressDto addAddress(AddressDto addressDto);

	AddressDto getAddressById(Integer aid);
	
	List<AddressDto> getAddressByUserId(Integer uid);
	
	AddressDto updateAddress(AddressDto addressDto);
	
	void deleteAddress(Integer aid);
}
