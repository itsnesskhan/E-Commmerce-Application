package com.ecom.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecom.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

	@Query(value = "SELECT DISTINCT o FROM Order o INNER JOIN FETCH o.orderItems Where oid =:oid")
  Optional<Order> getOrder(@Param(value = "oid") Integer oid);
}
