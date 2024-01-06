package com.martscompany.restapi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="employee_worked_hours")
public class EmployeeWH {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer id;
	Integer employee_id;
	Integer worked_hours;
	String worked_date;
	
	public EmployeeWH() {
		super();
	}

	public EmployeeWH(Integer id, Integer employee_id, Integer worked_hours, String worked_date) {
		super();
		this.id = id;
		this.employee_id = employee_id;
		this.worked_hours = worked_hours;
		this.worked_date = worked_date;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(Integer employee_id) {
		this.employee_id = employee_id;
	}
	
	public Integer getWorked_hours() {
		return worked_hours;
	}

	public void setWorked_hours(Integer worked_hours) {
		this.worked_hours = worked_hours;
	}
	
	public String geWorked_date() {
		return worked_date;
	}

	public void setWorked_date(String worked_date) {
		this.worked_date = worked_date;
	}

}
