package com.walmart.hackday.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="SKU")
public class Sku {
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Set<Sku> getCrossSellSkus() {
		return crossSellSkus;
	}

	public void setCrossSellSkus(Set<Sku> crossSellSkus) {
		this.crossSellSkus = crossSellSkus;
	}

	public Integer getSku() {
		return sku;
	}

	public void setSku(Integer sku) {
		this.sku = sku;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	@Id
	@Column(name="SKU_ID")
	private Integer sku;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="IMAGE_URL")
	private String imageURL;
	
	@Column(name="PRICE")
	private Double price;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
	      name="CROSS_SELL_MAPPING",
	      joinColumns={@JoinColumn(name="SKU_ID", referencedColumnName="SKU_ID")},
	      inverseJoinColumns={@JoinColumn(name="CROSS_SELL_SKU", referencedColumnName="SKU_ID")})
	private Set<Sku> crossSellSkus;
	
}
