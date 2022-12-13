package com.ecom.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Data
@Table(name = "orders")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer oid;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime createdAt;
	
	private String status;
	
	@JsonManagedReference
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "pk.order")
	private List<OrderItem> orderItems = new ArrayList<>();
	
	@ManyToOne(optional = false,fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;
	
}
