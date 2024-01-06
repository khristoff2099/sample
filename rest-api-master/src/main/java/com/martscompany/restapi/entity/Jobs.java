package com.martscompany.restapi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="jobs")
public class Jobs {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer id;
	String name;
	Integer salary;

	public Jobs() {
		super();
	}

	public Jobs(Integer id, String name, Integer salary) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

}
