package com.walmart.hackday.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="STORE")
public class Store {
	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitute() {
		return longitute;
	}

	public void setLongitute(String longitute) {
		this.longitute = longitute;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Id
	@Column(name="STORE_ID")
	private Integer storeId;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="LATITUDE")
	private String latitude;
	
	@Column(name="LONGITUDE")
	private String longitute;
	
	@Column(name="ADDRESS")
	private String address;
}
