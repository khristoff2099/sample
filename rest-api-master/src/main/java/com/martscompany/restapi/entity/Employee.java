package com.martscompany.restapi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer id;
	Integer gender_id;
	Integer job_id;
	String name;
	String last_name;
	String birthdate;

	
	public Employee() {
		super();
	}

	public Employee(Integer id, Integer gender_id, Integer job_id, String name, String last_name, String birthdate) {
		super();
		this.id = id;
		this.gender_id = gender_id;
		this.job_id = job_id;
		this.name = name;
		this.last_name = last_name;
		this.birthdate = birthdate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getGender_id() {
		return gender_id;
	}

	public void setGender_id(Integer gender_id) {
		this.gender_id = gender_id;
	}
	
	public Integer getJob_id() {
		return job_id;
	}

	public void setJob_id(Integer job_id) {
		this.job_id = job_id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

}
