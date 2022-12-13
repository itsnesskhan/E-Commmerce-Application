 package com.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.model.UserRole;

@Repository
public interface RoleRepository extends JpaRepository<UserRole, Integer> {

}
