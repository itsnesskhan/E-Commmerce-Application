package com.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

	Address findByUserUid(Integer userId);
}
