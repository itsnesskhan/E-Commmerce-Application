package com.ecom.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SecondaryTable;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Its
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
@Getter
@Setter
public class OrderItemPK implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "order_id", referencedColumnName = "oid")
	private Order order;
	
	@ManyToOne(fetch = FetchType.LAZY, optional =  false)
	@JoinColumn(name = "product_id", referencedColumnName = "pid")
	private Product product;

	public OrderItemPK(Product product) {
		super();
		this.product = product;
	}
	
	
	
}
