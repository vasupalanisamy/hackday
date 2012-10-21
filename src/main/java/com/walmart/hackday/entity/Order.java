package com.walmart.hackday.entity;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonManagedReference;

@Entity
@Table(name="ORDERS")
public class Order {

	public final static String ORDER_STATUS_READY = "Ready";
	public final static String ORDER_STATUS_CHECKED_IN = "Checked-In";
	public final static String ORDER_STATUS_IN_PROGRESS = "In-Progress";
	public final static String ORDER_STATUS_COMPLETED = "Completed";
	
	public Profile getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Profile createdBy) {
		this.createdBy = createdBy;
	}

	public Set<LineItem> getLineItems() {
		return lineItems;
	}

	public void setLineItems(Set<LineItem> lineItems) {
		this.lineItems = lineItems;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Date getSubmittedDate() {
		return submittedDate;
	}

	public void setSubmittedDate(Date submittedDate) {
		this.submittedDate = submittedDate;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
		if(status.equals(ORDER_STATUS_CHECKED_IN)) {
			setCheckinTime(Calendar.getInstance().getTime());
		}
	}

	public String getCheckInLatitude() {
		return checkInLatitude;
	}

	public void setCheckInLatitude(String checkInLatitude) {
		this.checkInLatitude = checkInLatitude;
	}

	public String getCheckInLongiude() {
		return checkInLongiude;
	}

	public void setCheckInLongiude(String checkInLongiude) {
		this.checkInLongiude = checkInLongiude;
	}

	public Date getCheckinTime() {
		return checkinTime;
	}

	public void setCheckinTime(Date checkinTime) {
		this.checkinTime = checkinTime;
	}

	public Integer getPickedBy() {
		return pickedBy;
	}

	public void setPickedBy(Integer pickedBy) {
		this.pickedBy = pickedBy;
	}

	@Id
	@GeneratedValue
	@Column(name="ORDER_ID")
	private Integer orderId;
	
	@Column(name="CREATION_DATE")
	private Date submittedDate;
	
	@Column(name="STORE_ID")
	private Integer storeId;
	
	@Column(name="STATUS")
	private String status;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PROFILE_ID")
	private Profile createdBy;
	
	@Column(name="CHECK_IN_LATITUDE")
	private String checkInLatitude;
	
	@Column(name="CHECK_IN_LONGITUDE")
	private String checkInLongiude;
	
	@Column(name="CHECK_IN_TIME")
	private Date checkinTime;
	
	@Column(name="PICKED_BY")
	private Integer pickedBy;
	
	@JsonManagedReference("order-lineitem")
	@OneToMany(mappedBy="order", fetch=FetchType.EAGER)
	private Set<LineItem> lineItems;
}
