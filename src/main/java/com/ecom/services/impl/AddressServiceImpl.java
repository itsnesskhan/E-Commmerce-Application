package com.ecom.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.ecom.dtos.AddressDto;
import com.ecom.model.Address;
import com.ecom.repository.AddressRepository;
import com.ecom.services.AddressService;

@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public AddressDto addAddress(AddressDto addressDto) {

		List<Address> address = addressRepository.findByUserUid(addressDto.getUser().getUid());
		Address userAddress = dtoToMode(addressDto);
		addressRepository.save(userAddress);
		userAddress = addressRepository.save(userAddress);

		return modelToDto(userAddress);
	}

	@Override
	public AddressDto getAddressById(Integer aid) {
		Address address = addressRepository.findById(aid)
				.orElseThrow(() -> new ResourceAccessException("resource not found exception"));
		return modelToDto(address);
	}

	public AddressDto modelToDto(Address address) {
		return modelMapper.map(address, AddressDto.class);
	}

	private Address dtoToMode(AddressDto addressDto) {
		return modelMapper.map(addressDto, Address.class);
	}

	@Override
	public AddressDto updateAddress(AddressDto addressDto) {
		Address address = addressRepository.findById(addressDto.getAid())
				.orElseThrow(() -> new ResourceAccessException("No addresss with this id"));
		address.setState(addressDto.getStreet());
		address.setCity(addressDto.getCity());
		address.setState(addressDto.getState());
		address = addressRepository.save(address);
		return modelToDto(address);
	}

	@Override
	public void deleteAddress(Integer aid) {
		Address address = addressRepository.findById(aid)
				.orElseThrow(() -> new ResourceAccessException("Address not found"));
		addressRepository.delete(address);

	}


	@Override
	public List<AddressDto> getAddressByUserId(Integer uid) {
		List<Address> findByUserUid = addressRepository.findByUserUid(uid);
		return findByUserUid.stream().map(item-> modelMapper.map(item, AddressDto.class)).collect(Collectors.toList());
	}

}
