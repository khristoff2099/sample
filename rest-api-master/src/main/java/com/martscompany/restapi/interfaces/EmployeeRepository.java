package com.martscompany.restapi.interfaces;

import javax.transaction.Transactional;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import com.martscompany.restapi.entity.Employee;
import com.martscompany.restapi.exceptions.ExceptionMessages;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {	
	 @Transactional
	    @Modifying
	 @Query(value = "SELECT * FROM employee e"
	 		+ "inner join genders g"
	 		+ "on g.id = e.gender_id"
	 		+ "inner join jobs j"
	 		+ "on j.id = e.job_id"
	 		+ "WHERE e.name = ? and e.last_name = ? and g.id = ? and  j.id = ? and birthdate = ?", nativeQuery = true)
	 public ResponseEntity<ExceptionMessages> findByEmp(@Param("name") String val,
			 											@Param("last_name") String val2,
	 													@Param("gender_id") Integer val3,
														@Param("job_id") Integer val4,
														@Param("birthdate") String val5);
	 
	 @Transactional
	    @Modifying
	 @Query(value = "SELECT e.name,e.last_name,j.name FROM jobs j"
	 		+ "inner join employee e"
	 		+ "on j.id = e.job_id"
	 		+ "where e.id = ? and e.job_id = ?", nativeQuery = true)
	 public ResponseEntity<ExceptionMessages> findByJobEmp(@Param("id") Integer id, @Param("job_id") Integer job_id);

}

