package com.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.model.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer>  {

}
