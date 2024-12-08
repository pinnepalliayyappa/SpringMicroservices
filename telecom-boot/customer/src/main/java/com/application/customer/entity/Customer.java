package com.application.customer.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class Customer {

	@Id
	@Column(name = "phone_no", nullable = false)
	Long phoneNo;
	@Column(nullable = false, length = 50)
	String name;
	@Column(nullable = false)
	Integer age;
	@Column(nullable = false, length = 50)
	String address;
	@Column(nullable = false, length = 50)
	String password;
	@Column(nullable = false, length = 1)
	char gender;
	@Column(name="plan_id",nullable = false)
	Integer planId;


	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public Long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(Long phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getPlanId() {
		return planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Customer() {
		super();
	}
}
