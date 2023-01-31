package com.ecom.services.impl;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.ecom.config.JwtTokenUtil;
import com.ecom.config.MyUserDetailsService;
import com.ecom.dtos.AddressDto;
import com.ecom.dtos.JwtRequest;
import com.ecom.dtos.JwtResponse;
import com.ecom.dtos.UserDto;
import com.ecom.model.Address;
import com.ecom.model.User;
import com.ecom.model.UserRole;
import com.ecom.repository.AddressRepository;
import com.ecom.repository.RoleRepository;
import com.ecom.repository.UserRepository;
import com.ecom.response.ResponseHandler;
import com.ecom.services.UserServices;
import com.ecom.utills.Constants;
import com.ecom.utills.Messages;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserServiesImpl<T> implements UserServices<T> {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private AddressRepository addressRepository;
	
	@Value("${spring.jwtConfig.token-validity}")
	private long tokenTimeMiliSec;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiesImpl.class);

	@Override
	public T loginUser(JwtRequest jwtRequest) {
		Optional<User> user = userRepository.findByEmail(jwtRequest.getUsername());
		
		
		if (user.isEmpty()) {
			return (T) ResponseHandler.errorResponseBuilder(Constants.USER_DOES_NOT_EXIST, HttpStatus.NOT_FOUND);
		}
		if (!passwordEncoder.matches(jwtRequest.getPassword(), user.get().getPassword())) {
			return (T) ResponseHandler.errorResponseBuilder(Constants.WRONG_PASSWORD, HttpStatus.FORBIDDEN);
		}
		
		try {
			UserDetails userDetails = myUserDetailsService.loadUserByUsername(user.get().getUsername());
			String token = jwtTokenUtil.generateToken(userDetails);
			String refreshToken = jwtTokenUtil.generateRefreshToken(userDetails);
			
			JwtResponse jwtResponse = JwtResponse.builder()
						.createdDate(LocalDate.now())
						.authority(user.get().getRole().getName())
						.email(user.get().getEmail())
						.token(token)
						.createdBy(user.get().getName().getFname())
						.refreshToken(refreshToken)
						.username(user.get().getUsername())
						.tokenExpriyTime((tokenTimeMiliSec /1000)/60)
						.build();
			
			return (T) ResponseHandler.responseBuilder("LOGIN SUCCESS", HttpStatus.OK, jwtResponse);
			
		} catch (Exception e) {
			return (T) ResponseHandler.errorResponseBuilder(Messages.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST);
		}
	}
	
	@Override
	public UserDto addUser(UserDto userDto) {

		LOGGER.info("UserservicesImpl::addUser == START");
		
		User dbUser = null;

		Optional<User> user = userRepository.findByEmail(userDto.getEmail());
		
		
		LOGGER.info("UserservicesImpl::addUser::user by ID = {}", user);
		if (user.isPresent()) {

			LOGGER.info("UserservicesImpl::addUser::if user not null = {}", user);
			throw new RuntimeException("user already exist with email " + userDto.getEmail());
		}

		UserRole ROLE = new UserRole(101, "CUSTOMER");
		
		dbUser = dtoToModel(userDto);
			UserRole role = roleRepository.save(ROLE);
			dbUser.setRole(role);


		// user save
		LOGGER.info("UserservicesImpl::addUser::UserDto to User Conversion = {}", user);
		dbUser = userRepository.save(dbUser);

		LOGGER.info("UserservicesImpl::addUser::converting addressDto to address");
		List<Address> addresses = userDto.getAddress().stream().map(adDto -> modelMapper.map(adDto, Address.class))
				.collect(Collectors.toList());

		// adding user id to address
		LOGGER.info("UserservicesImpl::addUser::setting userId to address");
		for (Address address : addresses) {
			address.setUser(dbUser);
			address = addressRepository.save(address);
			dbUser.getAddress().add(address);
		}

		userRepository.save(dbUser);
		LOGGER.info("UserservicesImpl::addUser::User after getting saved = {}", user);

		LOGGER.info("UserservicesImpl::addUser == END");
		return modelToDto(dbUser);
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
		return modelMapper.map(userDto, User.class);
	}

	@Override
	public T updateUser(UserDto userDto) {
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
				if (addressDto.getAid()!=null) {
					Address dbAddress = addressRepository.findByUserUidAndAid(userDto.getUid(), addressDto.getAid());
					
					System.out.println(dbAddress+" my response");

					LOGGER.info("UserservicesImpl::updateUser::address by id = {}", dbAddress);

					if (dbAddress!=null) {
					
						dbAddress.setStreet(addressDto.getStreet());
						dbAddress.setCity(addressDto.getCity());
						dbAddress.setState(addressDto.getState());

						LOGGER.info("UserservicesImpl::updateUser::saving address objects");

						Address address = addressRepository.save(dbAddress);
						user.getAddress().add(address);	
					} 
				}
				else {
					LOGGER.info("UserservicesImpl::updateUser::saving new address objects");

					Address address = modelMapper.map(addressDto, Address.class);
					address.setUser(user);
					address = addressRepository.save(address);
					user.getAddress().add(address);
		
				}
			}
			
		}else {
			return (T) ResponseHandler.errorResponseBuilder(Messages.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST);
		}

		LOGGER.info("UserservicesImpl::updateUser::updating user");
		user = userRepository.save(user);

		LOGGER.info("UserservicesImpl::updateUser::user after updation == {}", user);

		LOGGER.info("UserservicesImpl::updateUser == END");

		return (T) modelToDto(user);
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
