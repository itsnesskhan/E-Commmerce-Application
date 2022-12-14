package com.ecom.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.dtos.AddressDto;
import com.ecom.services.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {

	@Autowired
	private AddressService addressService;

	@PostMapping("")
	public ResponseEntity<AddressDto> addUserAddress(@RequestBody AddressDto addressDto) {
		AddressDto addAddress = addressService.addAddress(addressDto);
		return new ResponseEntity<>(addAddress, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<AddressDto> getAddressById(@PathVariable("id") Integer id) {
		AddressDto address = addressService.getAddressById(id);
		return ResponseEntity.ok(address);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<List<AddressDto>> getUserAddress(@PathVariable("id") Integer id) {
		List<AddressDto> address = addressService.getAddressByUserId(id);
		return ResponseEntity.ok(address);
	}

	@DeleteMapping("/{aid}")
	public ResponseEntity<Object> deleteUserAddress(@PathVariable("aid") Integer aid) {
		addressService.deleteAddress(aid);
		return ResponseEntity.ok(Map.entry("Address deleted Successfully", true));
	}

	@PutMapping("/user")
	public ResponseEntity<AddressDto> updateUserAddress(@RequestBody AddressDto addressDto) {
		AddressDto address = addressService.updateAddress(addressDto);
		return ResponseEntity.ok(address);
	}
}
