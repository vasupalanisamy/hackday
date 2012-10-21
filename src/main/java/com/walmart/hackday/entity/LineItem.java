package com.walmart.hackday.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;

@Entity
@Table(name="ORDER_LINE_ITEMS")
public class LineItem {
	
	public Sku getSku() {
		return sku;
	}

	public void setSku(Sku sku) {
		this.sku = sku;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Id
	@GeneratedValue
	@Column(name="ORDER_LINE_ID")
	private Integer id;
	
	@JsonBackReference("order-lineitem")
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ORDER_ID")
	private Order order;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="SKU_ID")
	private Sku sku;
}
