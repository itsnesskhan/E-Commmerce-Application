package com.ecom.services.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.ecom.dtos.AddressDto;
import com.ecom.dtos.UserDto;
import com.ecom.model.Address;
import com.ecom.model.User;
import com.ecom.model.UserRole;
import com.ecom.repository.AddressRepository;
import com.ecom.repository.RoleRepository;
import com.ecom.repository.UserRepository;
import com.ecom.services.UserServices;
import com.ecom.utills.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserServiesImpl implements UserServices {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private AddressRepository addressRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiesImpl.class);

	@Override
	public UserDto addUser(UserDto userDto, Set<UserRole> roles) {

		LOGGER.info("UserservicesImpl::addUser == START");

		User user = userRepository.findByEmail(userDto.getEmail());

		LOGGER.info("UserservicesImpl::addUser::user by ID = {}", user);
		if (user != null) {

			LOGGER.info("UserservicesImpl::addUser::if user not null = {}", user);
			throw new RuntimeException("user already exist with email " + userDto.getEmail());
		}

		user = dtoToModel(userDto);
		for (UserRole userRole : roles) {
			UserRole role = roleRepository.save(userRole);
			user.getRoles().add(role);
		}

		// user save
		LOGGER.info("UserservicesImpl::addUser::UserDto to User Conversion = {}", user);
		user = userRepository.save(user);

		LOGGER.info("UserservicesImpl::addUser::converting addressDto to address");
		List<Address> addresses = userDto.getAddress().stream().map(adDto -> modelMapper.map(adDto, Address.class))
				.collect(Collectors.toList());

		// adding user id to address
		LOGGER.info("UserservicesImpl::addUser::setting userId to address");
		for (Address address : addresses) {
			address.setUser(user);
			address = addressRepository.save(address);
			user.getAddress().add(address);
		}

		userRepository.save(user);
		LOGGER.info("UserservicesImpl::addUser::User after getting saved = {}", user);

		LOGGER.info("UserservicesImpl::addUser == END");
		return modelToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> findAll = userRepository.findAll();
		return findAll.stream().map(user -> modelToDto(user)).collect(Collectors.toList());
	}

	public UserDto modelToDto(User user) {
		return modelMapper.map(user, UserDto.class);
	}

	public User dtoToModel(UserDto userDto) {
//		BeanUtils.copyProperties(userDto, User.class);
		return modelMapper.map(userDto, User.class);
	}

	@Override
	public UserDto updateUser(UserDto userDto) {
		LOGGER.info("UserservicesImpl::updateUser == START");

		LOGGER.info("UserservicesImpl::updateUser::fetching User with Id = {}", userDto.getUid());
		User user = userRepository.findById(userDto.getUid())
				.orElseThrow(() -> new ResourceAccessException("user not exist"));

		LOGGER.info("UserservicesImpl::updateUser::fetched User = {}", user);

		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setMobileNumber(userDto.getMobileNumber());
		
		if (userDto.getProfileUrl()!=null) {
			user.setProfileUrl(userDto.getProfileUrl());	
		}
		user = userRepository.save(user);

		if (!userDto.getAddress().isEmpty() ) {
			for (AddressDto addressDto : userDto.getAddress()) {

				LOGGER.info("UserservicesImpl::updateUser::fetching address by user id = {}", userDto.getUid());
				Address dbAddress = addressRepository.findByUserUid(userDto.getUid());

				LOGGER.info("UserservicesImpl::updateUser::address by id = {}", dbAddress);

				if (dbAddress != null) {
					dbAddress.setStreet(addressDto.getStreet());
					dbAddress.setCity(addressDto.getCity());
					dbAddress.setState(addressDto.getState());

					LOGGER.info("UserservicesImpl::updateUser::saving address objects");

					Address address = addressRepository.save(dbAddress);
					user.getAddress().add(address);
					
				} else {
					LOGGER.info("UserservicesImpl::updateUser::saving new address objects");

					Address address = modelMapper.map(addressDto, Address.class);
					address.setUser(user);
					address = addressRepository.save(address);
					user.getAddress().add(address);
				}

			}
		}

		LOGGER.info("UserservicesImpl::updateUser::updating user");
		user = userRepository.save(user);

		LOGGER.info("UserservicesImpl::updateUser::user after updation == {}", user);

		LOGGER.info("UserservicesImpl::updateUser == END");

		return modelToDto(user);
	}

	@Override
	public void deleteUser(Integer uid) {
		LOGGER.info("UserservicesImpl::deleteUser == START");
		
		LOGGER.info("UserservicesImpl::fetching user with id = {}",uid);
		User user = userRepository.findById(uid).orElseThrow(() -> new RuntimeException("no user found"));
		
		LOGGER.info("UserservicesImpl::deleteUser:: geting user address from user");
		List<Address> addresses = user.getAddress();
		
		if (!addresses.isEmpty()) {
			LOGGER.info("UserservicesImpl::deleteUser:: deleting user address");
			addressRepository.deleteAll(addresses);
		}
		
		LOGGER.info("UserservicesImpl::deleteUser:: deleting user");
		
		userRepository.delete(user);
		LOGGER.info("UserservicesImpl::deleteUser == END");
	}

	@Override
	public AddressDto addAddress(AddressDto addressDto) {
		User user = userRepository.findById(addressDto.getUser().getUid())
				.orElseThrow(() -> new RuntimeException("no user found"));

//		Address address = addressRepository.findByUserUid(addressDto.getUser().getUid());
//		if (address!=null) {
//			throw new RuntimeException("User already have a address");
//		}

		Address address = dtoToMode(addressDto);
		address.setUser(user);
		userRepository.save(user);
		address = addressRepository.save(address);

		return modelToDto(address);
	}

	@Override
	public AddressDto getAddress(Integer aid) {
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
	public UserDto getUserById(Integer uid) {
		User user = userRepository.findById(uid).orElseThrow(()-> new RuntimeException("No user found with id "+uid));
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto getJson(String userString) {
		
		UserDto userDto = new UserDto();
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			
			userDto = mapper.readValue(userString, userDto.getClass());
			
		} catch (JsonProcessingException e) {
				e.printStackTrace();
		}
		return userDto;
	}

}
