package com.ecom.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecom.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String email);
	
//	@Query(value = "SELECT DISTINCT u FROM User u INNER JOIN FETCH u.roles r Where email =:email And mobile_number =:mobile")
//   User findByEmailAndMobileNumber(@Param(value = "email") String email, @Param("mobile") String mobile);
	
//	User findByEmailAndMobileNumber(String email, String mobilenumber);
}
