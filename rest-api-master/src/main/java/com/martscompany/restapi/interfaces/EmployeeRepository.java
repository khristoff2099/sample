package com.martscompany.restapi.interfaces;

import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.martscompany.restapi.entity.Employee;
import com.martscompany.restapi.entity.EmployeeWH;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>, JpaSpecificationExecutor<EmployeeWH> {	
	
	 @Transactional
	    @Modifying
	 @Query(value = "SELECT * FROM employee e\r\n"
	 		+ "inner join genders g\r\n"
	 		+ "on g.id = e.gender_id\r\n"
	 		+ "inner join jobs j\r\n"
	 		+ "on j.id = e.job_id\r\n"
	 		+ "WHERE e.name = ? and e.last_name = ? and g.id = ? and  j.id = ? and birthdate = ?", nativeQuery = true)
	 public Employee findByEmp(@Param("name") String name,
			 											@Param("last_name") String last_name,
	 													@Param("gender_id") Integer gender_id,
														@Param("job_id") Integer job_id,
														@Param("birthdate") String birthdate);
	 
	 @Transactional
	    @Modifying
	 @Query(value = "SELECT e.id, e.name,e.last_name,e.birthdate,j.name, g.name \r\n"
	 		+ "FROM jobs j\r\n"
	 		+ "inner join employee e\r\n"
	 		+ "on j.id = e.job_id\r\n"
	 		+ "inner join genders g\r\n"
	 		+ "on e.gender_id = g.id\r\n"
	 		+ "where e.job_id = ?", nativeQuery = true)
	 public Optional<Employee> findByJobEmp(@Param("job_id") Integer job_id);
	 
	 @Transactional
	    @Modifying
	 @Query(value = "select * from jobs where id = ?", nativeQuery = true)
	 public Optional<Employee> findByJob(@Param("id") Integer id);

}

