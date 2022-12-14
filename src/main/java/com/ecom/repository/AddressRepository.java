package com.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

	List<Address> findByUserUid(Integer userId);
	
   Address findByUserUidAndAid(Integer uid, Integer aid);
}
