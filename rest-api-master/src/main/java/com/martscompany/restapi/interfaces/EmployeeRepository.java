package com.martscompany.restapi.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import com.martscompany.restapi.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {	

}
