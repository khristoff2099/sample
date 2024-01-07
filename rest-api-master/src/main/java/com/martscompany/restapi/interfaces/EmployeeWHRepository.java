package com.martscompany.restapi.interfaces;

import javax.transaction.Transactional;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.martscompany.restapi.entity.EmployeeWH;

@Repository
public interface EmployeeWHRepository extends JpaRepository<EmployeeWH, Integer>, JpaSpecificationExecutor<EmployeeWH> {
	 
	 @Transactional
	    @Modifying
	 @Query(value = "SELECT w.employee_id, w.worked_hours, w.worked_date FROM employee_worked_hours w\r\n"
	 		+ "inner join employee e \r\n"
	 		+ "on e.id = w.employee_id\r\n"
	 		+ "WHERE w.employee_id=? and w.worked_hours = ? and w.worked_date = ?", nativeQuery = true)
	 public EmployeeWH findByWHE(@Param("employee_id") Integer employee_id,
								 @Param("worked_hours") Integer worked_hours,
								 @Param("worked_date") String worked_date);
	 
	 @Transactional
	    @Modifying
	 @Query(value = "SELECT e.name, e.last_name, w.worked_hours FROM employee_worked_hours w INNER JOIN  employee e ON w.employee_id = e.id "	 		
	 + "WHERE  e.id = ? and w.worked_date between ? and ? ", nativeQuery = true)
	 public EmployeeWH findByWD(@Param("id") Integer id, @Param("worked_date") String worked_date_start,@Param("worked_date") String worked_date_end);
	 
	 @Transactional
	    @Modifying
	 @Query(value = "SELECT e.name, e.last_name, j.salary from jobs j inner join employee e on e.job_id = j.id inner join employee_worked_hours w on w.employee_id = e.id "
			 + "WHERE e.id = ? and w.worked_date between ? and ? ", nativeQuery = true)
	 public EmployeeWH findByWS(@Param("id") Integer id, @Param("worked_date") String worked_date_start,@Param("worked_date") String worked_date_end);

}