package com.martscompany.restapi.interfaces;


import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import com.martscompany.restapi.entity.EmployeeWH;
import com.martscompany.restapi.exceptions.ExceptionMessages;

@Repository
public interface EmployeeWHRepository extends JpaRepository<EmployeeWH, Integer>, JpaSpecificationExecutor<EmployeeWH> {
	
	 @Transactional
	    @Modifying
	 @Query(value = " SELECT w.worked_hours FROM employee_worked_hours w inner join employee e on e.id = w.employee_id "
	 		//+ "WHERE w.worked_hours < 20 and e.id=? ", nativeQuery = true)
	 + "WHERE w.worked_hours < 20 and e.id=? ")
     public Optional<EmployeeWH> findByWH(@Param("id") Integer id);
	 
	 @Transactional
	    @Modifying
	 @Query(value = "SELECT w.worked_hours FROM employee_worked_hours w INNER JOIN  employee e ON w.employee_id = e.id "
	 		//+ "WHERE w.worked_date between ? and ? ", nativeQuery = true)
	 + "WHERE w.worked_date between ? and ? ")
	 public ResponseEntity<ExceptionMessages> findByWD(@Param("worked_date") String val,@Param("worked_date") String val2);

}