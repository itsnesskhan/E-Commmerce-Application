package com.ecom;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ecom.model.User;
import com.ecom.repository.ProductRepository;
import com.ecom.repository.UserRepository;

@SpringBootApplication
public class EComAppApplication implements CommandLineRunner{
	
	
	
	@Autowired
	private UserRepository userRepository;
	
	

	public static void main(String[] args)  {
		SpringApplication.run(EComAppApplication.class, args);
	}
	
	
	@Autowired
	private ProductRepository productRepository;
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	
	

	@Override
	public void run(String... args) throws Exception {
//		User user = userRepository.findByEmailAndMobileNumber("itsnesskhan@gmail.com", "9713216901");
//		System.out.println(user);
		
		
//		User user = userRepository.findByEmailAndMobileNumber("itsnesskhan@gmail.com", "9713216901");
//		System.out.println(user.getRoles());
		
//		UserRole userRole = new UserRole(101, "CUSTOMER");
//		UserRole userRole1 = new UserRole(102, "ADMIN");
//		
//		roleRepository.save(userRole);
//		roleRepository.save(userRole1);

		
//		UserDto user = new UserDto();
//	
//		Name name = new Name("Nasser","Khan");
//		user.setName(name);
//		user.setEmail("itsnesskhan@gmail.com");
//		user.setPassword("iness");
//		
//		
//		HashSet<UserRole> roles = new HashSet<>();
//		UserRole role = new UserRole(101,"NORMAL_USER");
//		roles.add(role);
//		userServices.addUser(user, roles);
		
//		String endTimestamp = DateUtils.getEndTimestamp(10, 1998);
//		System.out.println(endTimestamp);
//		
//		System.out.println(DateUtils.stringToDateFormat("29/03/2019","d/MM/yyyy" ));
		
//		System.out.println(DateUtils.dateToStringFormat(new Date(1998, 10, 15), ""));
		
//		for (int i = 0; i < 10; i++) {
//			Product product = new Product();
//			product.setName("product"+i);
//			product.setPrice(i+00);
//			
//			productRepository.save(product);
//		}
	}

}
